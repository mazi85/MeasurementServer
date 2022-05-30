package pl.mazi85.measurementserver.controller.meassource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Setter
@Getter
public class CreateMeasSourceForm {

    @NotBlank
    private String name;
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\.\\d{3}")
    private String ip;
    @Pattern(regexp = "\\d+")
    private String port;

    private Long commProtocolId;
}
