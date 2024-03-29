package pl.mazi85.measurementserver.controller.meassource;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import pl.mazi85.measurementserver.model.CommProtocol;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Setter
@Getter
@Builder
public class EditMeasSourceForm {

    @NotBlank
    private String name;
    @Pattern(regexp = "^((\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])\\.){3}(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])$")
    @NotNull
    private String ip;
    @Pattern(regexp = "\\d+")
    @NotNull
    private String port;
    private boolean recording;
    private Long commProtocolId;
}
