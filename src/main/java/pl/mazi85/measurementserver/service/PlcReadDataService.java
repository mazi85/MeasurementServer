package pl.mazi85.measurementserver.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.plc4x.java.api.PlcConnection;
import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.apache.plc4x.java.api.messages.PlcReadResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Component
public class PlcReadDataService {


    public PlcReadRequest preparePlcReadQuery(List<Integer> registers, PlcConnection plcConnection) {
        // Check if this connection support reading of data.
        if (!plcConnection.getMetadata().canRead()) {
            log.error("This connection: " + plcConnection + " doesn't support reading.");
        }

        PlcReadRequest.Builder builder = plcConnection.readRequestBuilder();
        for (int i = 0; i < registers.size(); i++) {
            builder.addItem(registers.get(i).toString(), "holding-register:"+ registers.get(i));
        }

        return builder.build();
    }

    public PlcReadResponse readPlcData(PlcReadRequest readRequest)  {
        try {
            return readRequest.execute().get(5000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            log.error("Interrupt");
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            log.error("Execution");
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            log.error("Timeout");
            throw new RuntimeException(e);
        }
    }

}
