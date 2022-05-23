package pl.mazi85.measurementserver.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "samples")
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double rawValue;
    private Double engValue;
    @ManyToOne
    @JoinColumn(name="meas_source_id")
    private MeasSource measSource;
}
