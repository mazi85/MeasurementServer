package pl.mazi85.measurementserver.controller.meassource;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mazi85.measurementserver.model.Sample;
import pl.mazi85.measurementserver.service.meassource.MeasSourceService;
import pl.mazi85.measurementserver.service.sample.SampleService;

import java.util.List;

@Controller
@RequestMapping("/meas-source/{measSourceId}/sample/actual")
@RequiredArgsConstructor
public class ListMeasSourceSampleController {

    private final MeasSourceService measSourceService;
    private final SampleService sampleService;

    @GetMapping
    public String prepareView(Model model, @PathVariable Long measSourceId){
        List<Sample> samples = sampleService.readActualValues(measSourceId);
        model.addAttribute("samples", samples);
        return "/sample/all";
    }

}
