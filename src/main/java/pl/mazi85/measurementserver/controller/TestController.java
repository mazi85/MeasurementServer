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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.mazi85.measurementserver.model.MeasSource;
import pl.mazi85.measurementserver.model.Sample;
import pl.mazi85.measurementserver.model.SampleDef;
import pl.mazi85.measurementserver.repository.MeasSourceRepository;
import pl.mazi85.measurementserver.repository.SampleDefRepository;
import pl.mazi85.measurementserver.repository.SampleRepository;
import pl.mazi85.measurementserver.service.PlcReadDataService;
import pl.mazi85.measurementserver.utils.PlcUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final PlcReadDataService plcReadDataService;
    private final MeasSourceRepository measSourceRepository;
    private final SampleRepository sampleRepository;
    private final SampleDefRepository sampleDefRepository;

    @GetMapping("/read/{id}")
    public String readPlcData(Model model, @PathVariable Long id) {
        MeasSource measSource = measSourceRepository.getReferenceById(id);
        String connectionString = measSource.getConnectionString();

        PlcConnection plcConnection = PlcUtils.getPlcConnection(connectionString);
        List<Integer> registers =
                measSource.getSampleDefs().stream()
                        .map(SampleDef::getRegister)
                        .collect(Collectors.toList());
        PlcReadRequest plcReadRequest = plcReadDataService.preparePlcReadQuery(registers, plcConnection);
        PlcReadResponse plcReadResponse = plcReadDataService.readPlcData(plcReadRequest);
        Map<String, Integer> valuesMap = PlcUtils.printResponse(plcReadResponse);
        model.addAttribute("valuesMap",valuesMap);

        for (Integer value : valuesMap.values()) {
            Sample sample = new Sample();
            sample.setRawValue(Double.valueOf(value));
            sample.setMeasSource(measSource);
            sampleRepository.save(sample);
        }

        return "testPlc";
    }


}
