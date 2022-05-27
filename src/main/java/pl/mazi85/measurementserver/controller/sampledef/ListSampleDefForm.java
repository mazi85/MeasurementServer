package pl.mazi85.measurementserver.controller.sampledef;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Setter @Getter @Builder
public class ListSampleDefForm {


    private Long id;
    private String name;
    private String unit;
    private Double lowRange;
    private Double highRange;
    private Integer register;

}
