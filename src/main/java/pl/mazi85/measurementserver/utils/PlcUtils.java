package pl.mazi85.measurementserver.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.plc4x.java.PlcDriverManager;
import org.apache.plc4x.java.api.PlcConnection;
import org.apache.plc4x.java.api.exceptions.PlcConnectionException;
import org.apache.plc4x.java.api.messages.PlcReadResponse;
import org.apache.plc4x.java.api.types.PlcResponseCode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


@Slf4j
public class PlcUtils {

    public static PlcConnection getPlcConnection(String connectionString)  {
        try {
            return new PlcDriverManager().getConnection(connectionString);
        } catch (PlcConnectionException e) {
            log.error("Can't establish the connection");
            throw new RuntimeException(e);
        }
    }

    public static Map<String,Integer> printResponse(PlcReadResponse response) {

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


}
