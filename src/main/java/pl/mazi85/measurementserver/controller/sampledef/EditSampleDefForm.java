package pl.mazi85.measurementserver.controller.sampledef;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter @Setter @Builder
public class EditSampleDefForm {

    @NotBlank
    private String name;
    @NotBlank
    private String unit;
    private Double lowRange;
    private Double highRange;
    @Min(1)
    private Integer register;

}
