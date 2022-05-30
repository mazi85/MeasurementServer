package pl.mazi85.measurementserver.controller.meassource;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.mazi85.measurementserver.service.meassource.MeasSourceService;

@Controller
@RequestMapping("/meas-source/list")
@RequiredArgsConstructor
public class ListMeasSourceController {

    private final MeasSourceService measSourceService;

    @GetMapping
    public String prepareView(Model model){

        model.addAttribute("listMeasSourceForm", measSourceService.listAll());
        return "/meas-source/list";
    }

}
