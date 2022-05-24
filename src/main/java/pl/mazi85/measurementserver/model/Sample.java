package pl.mazi85.measurementserver.model;

import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "samples")
@Setter
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double rawValue;
    private Double engValue;
    @ManyToOne
    @JoinColumn(name="meas_source_id")
    private MeasSource measSource;
    @ManyToOne
    @JoinColumn(name="sample_def_id")
    private SampleDef sampleDef;
}
