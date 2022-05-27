package pl.mazi85.measurementserver.controller.meassource;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.mazi85.measurementserver.service.MeasSourceService;

@Controller
@RequestMapping("/meas-source")
@RequiredArgsConstructor
public class DeleteMeasSourceController {

    private final MeasSourceService measSourceService;


    @GetMapping("/delete/{id}")
    public String prepareView(Model model, @PathVariable Integer id) {
        model.addAttribute("measSourceId", id);
        return "/meas-source/del";
    }

    @PostMapping("/delete/{id}")
    public String del(Long measSourceId) {
        measSourceService.delete(measSourceId);
        return "redirect:/meas-source/list";

    }
}
