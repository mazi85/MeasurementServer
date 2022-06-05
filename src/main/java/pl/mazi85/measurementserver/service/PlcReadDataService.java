package pl.mazi85.measurementserver.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.plc4x.java.PlcDriverManager;
import org.apache.plc4x.java.api.PlcConnection;
import org.apache.plc4x.java.api.exceptions.PlcConnectionException;
import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.apache.plc4x.java.api.messages.PlcReadResponse;
import org.apache.plc4x.java.api.types.PlcResponseCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlcReadDataService {

    private final PlcDriverManager plcDriverManager;

    private PlcConnection getPlcConnection(String connectionString)  {
        try {
            return plcDriverManager.getConnection(connectionString);
        } catch (PlcConnectionException e) {
            log.error("Can't establish the connection");
            throw new RuntimeException(e);
        }
    }

    public Map<String,Integer> getResponseValues(PlcReadResponse response) {

        Map<String,Integer> results = new TreeMap<>();

        for (String fieldName : response.getFieldNames()) {
            if (response.getResponseCode(fieldName) == PlcResponseCode.OK) {
                int numValues = response.getNumberOfValues(fieldName);
                // If it's just one element, output just one single line.
                if (numValues == 1) {
                    results.put(fieldName,Integer.parseInt(String.valueOf(response.getObject(fieldName))));
                    log.info("Value[{}]: {}", fieldName,response.getObject(fieldName));
                }
                // If it's more than one element, output each in a single row.
                else {
                    log.info("Value[{}]:", fieldName);
                    for (int i = 0; i < numValues; i++) {
                        log.info(" - {}", response.getObject(fieldName, i));
                    }
                }
            }
            // Something went wrong, to output an error message instead.
            else {
                log.error("Error[{}]: {}", fieldName, response.getResponseCode(fieldName).name());
            }
        }
        return results;
    }

    public PlcReadResponse readPlcData(Map<Long,Integer> registers, String connectionString) {
        //creating connection
        try (PlcConnection plcConnection = plcDriverManager.getConnection(connectionString)){
            // Check if this connection support reading of data.
            if (!plcConnection.getMetadata().canRead()) {
                log.error("This connection: " + plcConnection + " doesn't support reading.");
            }
            // prepare query
            PlcReadRequest.Builder builder = plcConnection.readRequestBuilder();
            for (Map.Entry<Long, Integer> registerEntry : registers.entrySet()) {
                builder.addItem(registerEntry.getKey().toString(), "holding-register:"+ registerEntry.getValue());
            }
            PlcReadRequest readRequest = builder.build();
            return readRequest.execute().get(5000, TimeUnit.MILLISECONDS);

        } catch (PlcConnectionException e) {
            log.error("Can't establish the connection");
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            log.error("Interrupt");
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("Execution");
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            log.error("Timeout");
            throw new RuntimeException(e);
        } catch (Exception e) {
            log.error("Undefined error");
            throw new RuntimeException(e);
        }
    }

}
