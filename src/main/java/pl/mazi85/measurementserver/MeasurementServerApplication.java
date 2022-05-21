package pl.mazi85.measurementserver;

import lombok.extern.slf4j.Slf4j;
import org.apache.plc4x.java.PlcDriverManager;
import org.apache.plc4x.java.api.PlcConnection;
import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.apache.plc4x.java.api.messages.PlcReadResponse;
import org.apache.plc4x.java.api.types.PlcResponseCode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@Slf4j
public class MeasurementServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MeasurementServerApplication.class, args);


		String connectionString = "modbus:tcp://127.0.0.1:502";
		try (PlcConnection plcConnection = new PlcDriverManager().getConnection(connectionString)) {


			// Check if this connection support reading of data.
			if (!plcConnection.getMetadata().canRead()) {
				log.error("This connection doesn't support reading.");
			}

			//creating read query
			PlcReadRequest.Builder builder = plcConnection.readRequestBuilder();
			builder.addItem("value1", "holding-register:20");
			PlcReadRequest readRequest = builder.build();

			//reading asc

			CompletableFuture<? extends PlcReadResponse> asyncResponse = readRequest.execute();
			asyncResponse.whenComplete((response, throwable) -> {
				try {
					for (String fieldName : response.getFieldNames()) {
						if(response.getResponseCode(fieldName) == PlcResponseCode.OK) {
							int numValues = response.getNumberOfValues(fieldName);
							// If it's just one element, output just one single line.
							if(numValues == 1) {
								log.info("Value[" + fieldName + "]: " + response.getObject(fieldName));
							}
							// If it's more than one element, output each in a single row.
							else {
								log.info("Value[" + fieldName + "]:");
								for(int i = 0; i < numValues; i++) {
									log.info(" - " + response.getObject(fieldName, i));
								}
							}
						}
						// Something went wrong, to output an error message instead.
						else {
							log.error("Error[" + fieldName + "]: " + response.getResponseCode(fieldName).name());
						}
					}
				} catch (Exception e) {
					log.error("Can't read values");
				}
			});


		} catch (Exception e) {
			log.error("Can't establish the connection");
		}




	}

}
