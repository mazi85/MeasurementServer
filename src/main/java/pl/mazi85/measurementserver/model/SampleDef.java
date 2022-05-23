package pl.mazi85.measurementserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Setter @Getter @Slf4j
@Entity(name = "sample_defs")
public class SampleDef {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String unit;
    private Double lowRange;
    private Double highRange;
    private Integer register;

}
