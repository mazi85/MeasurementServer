package pl.mazi85.measurementserver.controller.meassource;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.mazi85.measurementserver.model.CommProtocol;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@Builder
public class EditMeasSourceForm {

    @NotBlank
    private String name;
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\.\\d{3}")
    private String ip;
    @Pattern(regexp = "\\d+")
    private String port;

    private CommProtocol commProtocol;
}
