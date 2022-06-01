package pl.mazi85.measurementserver.controller.sampledef;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mazi85.measurementserver.service.sampledef.SampleDefService;

@Controller
@RequestMapping("/meas-source/{measSourceId}/sample-def")
@RequiredArgsConstructor

public class DeleteSampleController {

    private final SampleDefService sampleDefService;


    @GetMapping("/delete/{sampleDefId}")
    public String prepareView(Model model, @PathVariable Integer sampleDefId, @PathVariable String measSourceId) {
        model.addAttribute("sampleDefId", sampleDefId);
        model.addAttribute("measSourceId", measSourceId);
        return "/sample-def/del";
    }

    @PostMapping("/delete/{sampleDefId}")
    public String del(Long sampleDefId, @PathVariable Long measSourceId) {
        sampleDefService.deleteSampleDef(sampleDefId,measSourceId);
        return "redirect:/meas-source/{measSourceId}/sample-def/list";

    }


}
