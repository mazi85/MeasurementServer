package pl.mazi85.measurementserver.model;


import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Setter  @Getter  @Slf4j
@AllArgsConstructor @NoArgsConstructor @Builder

@Entity(name = "meas_sources")
public class MeasSource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String ip;
    private String port;
    @ManyToOne
    @JoinColumn(name="protocol_id")
    private CommProtocol commProtocol;
    @ManyToMany
    @JoinTable(name = "sources_defs",
            joinColumns = @JoinColumn(name = "meas_source_id"),
            inverseJoinColumns = @JoinColumn(name = "sample_def_id"))
    private List<SampleDef> sampleDefs = new ArrayList<>();
    @Transient
    private String connectionString;

    public String getConnectionString() {
        StringBuilder connectionBuilder = new StringBuilder();
        connectionBuilder
                .append(commProtocol.getName())
                .append(":tcp://")
                .append(ip)
                .append(":")
                .append(port);
        return connectionBuilder.toString();
    }
}
