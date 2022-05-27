package pl.mazi85.measurementserver.model;

import lombok.Setter;

import javax.persistence.*;
import java.time.Clock;
import java.time.LocalDateTime;
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

    @Column(name = "created_on",updatable = false)
    private LocalDateTime createdOn;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now(Clock.systemUTC());
    }
}
