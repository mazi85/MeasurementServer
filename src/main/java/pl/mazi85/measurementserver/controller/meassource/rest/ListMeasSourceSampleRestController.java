package pl.mazi85.measurementserver.controller.meassource.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.mazi85.measurementserver.model.Sample;
import pl.mazi85.measurementserver.service.meassource.MeasSourceService;
import pl.mazi85.measurementserver.service.sample.SampleService;

import java.util.List;

@RestController
@RequestMapping("/meas-source/{measSourceId}/rest/sample")
@RequiredArgsConstructor
public class ListMeasSourceSampleRestController {

    private final SampleService sampleService;

    @GetMapping("/actual")
    public List<Sample> prepareView(Model model, @PathVariable Long measSourceId){
        List<Sample> samples = sampleService.readActualValues(measSourceId);
        return samples;
    }

}
