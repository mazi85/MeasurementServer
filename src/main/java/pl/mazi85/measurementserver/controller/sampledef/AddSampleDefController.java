package pl.mazi85.measurementserver.controller.sampledef;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mazi85.measurementserver.service.sampledef.SampleDefService;
import pl.mazi85.measurementserver.service.meassource.MeasSourceService;

import javax.validation.Valid;

@Controller
@RequestMapping("/meas-source/{measSourceId}/sample-def/add")
@RequiredArgsConstructor
public class AddSampleDefController {

    private final SampleDefService sampleDefService;
    private final MeasSourceService measSourceService;

    @GetMapping
    public String prepareView(Model model){
        model.addAttribute("addSampleDefForm", new AddSampleDefForm());
        return "/sample-def/add";
    }
    @PostMapping
    public String add(Model model,@PathVariable Long measSourceId, @Valid AddSampleDefForm addSampleDefForm,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "/sample-def/add";
        }
        sampleDefService.createSampleDef(addSampleDefForm,measSourceId);
        return "redirect:list";
    }


}
