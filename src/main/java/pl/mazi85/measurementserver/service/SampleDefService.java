package pl.mazi85.measurementserver.service;

import pl.mazi85.measurementserver.controller.sampledef.AddSampleDefForm;
import pl.mazi85.measurementserver.controller.sampledef.EditSampleDefForm;
import pl.mazi85.measurementserver.controller.sampledef.ListSampleDefForm;
import pl.mazi85.measurementserver.model.SampleDef;

import java.util.List;

public interface SampleDefService {
    List<ListSampleDefForm> listAllByMeasSource(Long measSourceId);

    SampleDef create(AddSampleDefForm addSampleDefForm, Long measSourceId);


    EditSampleDefForm findSampleDefById(Long sampleDefId);

    void editSampleDef(EditSampleDefForm editSampleDefForm, Long sampleDefId);

    void deleteSampleDef(Long sampleDefId, Long measSourceId);
}
