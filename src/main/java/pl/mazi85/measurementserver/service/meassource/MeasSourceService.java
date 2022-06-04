package pl.mazi85.measurementserver.service.meassource;

import pl.mazi85.measurementserver.controller.meassource.CreateMeasSourceForm;
import pl.mazi85.measurementserver.controller.meassource.EditMeasSourceForm;
import pl.mazi85.measurementserver.controller.meassource.ListMeasSourceForm;

import java.util.List;
import java.util.Map;

public interface MeasSourceService {
    void createMeasSource(CreateMeasSourceForm createMeasSourceForm);

    List<ListMeasSourceForm> listAll();

    void delete(Long measSourceId);

    EditMeasSourceForm findById(Long id);

    void editMeasSource(EditMeasSourceForm editMeasSourceForm, Long measSourceId);

    String getMeasSourceConnectionString(Long measSourceId);

    Map<Long,Integer> getMeasSourceRegisters(Long measSourceId);
}
