package pl.mazi85.measurementserver.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mazi85.measurementserver.service.CommProtocolService;
import pl.mazi85.measurementserver.service.MeasSourceService;

import javax.validation.Valid;

@Controller
@RequestMapping("/meas-source")
@RequiredArgsConstructor
public class EditMeasSourceController {

    private final MeasSourceService measSourceService;
    private final CommProtocolService commProtocolService;


    @GetMapping("/edit/{id}")
    public String prepareView(Model model, @PathVariable Long id){
        EditMeasSourceForm editMeasSourceForm = measSourceService.findById(id);
        model.addAttribute("editMeasSourceForm", editMeasSourceForm);
        model.addAttribute("protocols",commProtocolService.readAll());
        model.addAttribute("measSourceId",id);
        return "/meas-source/edit";
    }
    @PostMapping("/edit/{id}")
    public String edit(Model model, Long measSourceId, @Valid EditMeasSourceForm editMeasSourceForm,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("protocols",commProtocolService.readAll());
            return "/meas-source/edit";
        }
        measSourceService.editMeasSource(editMeasSourceForm,measSourceId);
        return "redirect:/meas-source/list";
    }


}
