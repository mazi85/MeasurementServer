package pl.mazi85.measurementserver.controller.meassource;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mazi85.measurementserver.service.CommProtocolService;
import pl.mazi85.measurementserver.service.meassource.MeasSourceService;

import javax.validation.Valid;

@Controller
@RequestMapping("/meas-source/add")
@RequiredArgsConstructor
public class CreateMeasSourceController {

    private final MeasSourceService measSourceService;
    private final CommProtocolService commProtocolService;

    @GetMapping
    public String prepareView(Model model){
        model.addAttribute("createMeasSourceForm", new CreateMeasSourceForm());
        model.addAttribute("protocols",commProtocolService.readAll());
        return "/meas-source/add";
    }
    @PostMapping
    public String create(Model model, @Valid CreateMeasSourceForm createMeasSourceForm,
                         BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            model.addAttribute("protocols",commProtocolService.readAll());
            return "/meas-source/add";
        }
        measSourceService.createMeasSource(createMeasSourceForm);
        return "redirect:/meas-source/list";
    }
}
