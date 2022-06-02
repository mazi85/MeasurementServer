package pl.mazi85.measurementserver.controller.sampledef;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter @Builder
public class EditSampleDefForm {

    @NotBlank
    private String name;
    @NotBlank
    private String unit;
    @NotNull
    private Double lowRange;
    @NotNull
    private Double highRange;
    @NotNull
    @Min(1)
    private Integer register;

}
