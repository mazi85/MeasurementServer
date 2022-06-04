package pl.mazi85.measurementserver.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.plc4x.java.api.messages.PlcReadResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.mazi85.measurementserver.model.MeasSource;
import pl.mazi85.measurementserver.model.Sample;
import pl.mazi85.measurementserver.model.SampleDef;
import pl.mazi85.measurementserver.repository.MeasSourceRepository;
import pl.mazi85.measurementserver.repository.SampleDefRepository;
import pl.mazi85.measurementserver.repository.SampleRepository;
import pl.mazi85.measurementserver.service.PlcReadDataService;
import pl.mazi85.measurementserver.service.meassource.MeasSourceService;
import pl.mazi85.measurementserver.service.sample.SampleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequiredArgsConstructor
public class TestController {

    private final PlcReadDataService plcReadDataService;
    private final MeasSourceRepository measSourceRepository;
    private final SampleRepository sampleRepository;
    private final SampleDefRepository sampleDefRepository;

    private final MeasSourceService measSourceService;
    private final SampleService sampleService;

    @GetMapping("/read/{measSourceId}")
    public String readPlcData(Model model, @PathVariable Long measSourceId) {
        String connectionString = measSourceService.getMeasSourceConnectionString(measSourceId);
        Map<Long,Integer> registers = measSourceService.getMeasSourceRegisters(measSourceId);

        PlcReadResponse plcReadResponse = plcReadDataService.readPlcData(registers, connectionString);
        Map<String, Integer> sampleDefIdValuesMap = plcReadDataService.getResponseValues(plcReadResponse);
        model.addAttribute("valuesMap",sampleDefIdValuesMap);
        sampleService.saveData(sampleDefIdValuesMap,measSourceId);

        return "testPlc";
    }


}
