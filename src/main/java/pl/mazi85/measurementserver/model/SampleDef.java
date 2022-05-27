package pl.mazi85.measurementserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;


@Setter @Getter @Slf4j
@Entity(name = "sample_defs")
public class SampleDef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String unit;
    private Double lowRange;
    private Double highRange;
    @NotBlank
    @Min(1)
    private Integer register;
    @ManyToMany(mappedBy = "sampleDefs")
    private List<MeasSource> measSources = new ArrayList<>();

}
