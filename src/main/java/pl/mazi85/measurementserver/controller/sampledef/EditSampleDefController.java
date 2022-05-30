package pl.mazi85.measurementserver.controller.sampledef;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mazi85.measurementserver.controller.meassource.EditMeasSourceForm;
import pl.mazi85.measurementserver.service.SampleDefService;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/meas-source/{measSourceId}/sample-def")
public class EditSampleDefController {

    private final SampleDefService sampleDefService;

    @GetMapping("/edit/{sampleDefId}")
    public String prepareView(Model model, @PathVariable Long sampleDefId) {
        EditSampleDefForm editSampleDefForm = sampleDefService.findSampleDefById(sampleDefId);
        model.addAttribute("editSampleDefForm", editSampleDefForm);
        model.addAttribute("sampleDefId", sampleDefId);
        return "/sample-def/edit";
    }

    @PostMapping("/edit/{sampleDefId}")
    public String edit(Model model, Long sampleDefId, @Valid EditSampleDefForm editSampleDefForm,
                       BindingResult bindingResult, @PathVariable String measSourceId){
        if(bindingResult.hasErrors()){
            return "/sample-def/edit";
        }
        sampleDefService.editSampleDef(editSampleDefForm,sampleDefId);
        return "redirect:/meas-source/{measSourceId}/sample-def/list";
    }
}
