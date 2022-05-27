package pl.mazi85.measurementserver.service;

import pl.mazi85.measurementserver.controller.CreateMeasSourceForm;
import pl.mazi85.measurementserver.controller.ListMeasSourceForm;

import java.util.List;

public interface MeasSourceService {
    void createMeasSource(CreateMeasSourceForm createMeasSourceForm);

    List<ListMeasSourceForm> listAll();

    void delete(Long measSourceId);
}
