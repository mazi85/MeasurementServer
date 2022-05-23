package pl.mazi85.measurementserver.controller;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.plc4x.java.PlcDriverManager;
import org.apache.plc4x.java.api.PlcConnection;
import org.apache.plc4x.java.api.messages.PlcReadRequest;
import org.apache.plc4x.java.api.messages.PlcReadResponse;
import org.apache.plc4x.java.api.types.PlcResponseCode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.mazi85.measurementserver.service.PlcReadDataService;
import pl.mazi85.measurementserver.utils.PlcUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final PlcReadDataService plcReadDataService;

    @GetMapping("/read")
    public String readPlcData(Model model) {
        String connectionString = "modbus:tcp://192.168.100.200:502";
        PlcConnection plcConnection = PlcUtils.getPlcConnection(connectionString);
        List<Integer> registers = List.of(1,2,3,4,5,6,7,8,9);
        PlcReadRequest plcReadRequest = plcReadDataService.preparePlcReadQuery(registers, plcConnection);
        PlcReadResponse plcReadResponse = plcReadDataService.readPlcData(plcReadRequest);
        Map<String, Integer> valuesMap = PlcUtils.printResponse(plcReadResponse);
        model.addAttribute("valuesMap",valuesMap);
        return "testPlc";
    }


}
