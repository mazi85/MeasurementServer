package pl.mazi85.measurementserver.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mazi85.measurementserver.model.SampleDef;

import java.util.List;

@Setter @Getter @Builder
public class ListMeasSourceForm {

    private Long id;
    private String name;
    private String connectionString;
    private List<SampleDef> sampleDefs;

}