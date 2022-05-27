package pl.mazi85.measurementserver.service;

import pl.mazi85.measurementserver.controller.sampledef.ListSampleDefForm;

import java.util.List;

public interface SampleDefService {
    List<ListSampleDefForm> listAllByMeasSource(Long measSourceId);
}
