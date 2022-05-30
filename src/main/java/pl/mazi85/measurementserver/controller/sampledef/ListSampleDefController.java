package pl.mazi85.measurementserver.controller.sampledef;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mazi85.measurementserver.service.meassource.MeasSourceService;
import pl.mazi85.measurementserver.service.SampleDefService;

@Controller
@RequestMapping("/meas-source/{id}/sample-def")
@RequiredArgsConstructor
public class ListSampleDefController {

    private final SampleDefService sampleDefService;
    private final MeasSourceService measSourceService;

    @GetMapping("/list")
    public String prepareView(Model model, @PathVariable(name = "id") Long measSourceId){

        model.addAttribute("measSourceName",measSourceService.findById(measSourceId).getName());
        model.addAttribute("listSampleDefForm", sampleDefService.listAllByMeasSource(measSourceId));
        model.addAttribute("measSourceId", measSourceId);
        return "/sample-def/list";
    }

}
