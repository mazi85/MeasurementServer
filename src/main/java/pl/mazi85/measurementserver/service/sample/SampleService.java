package pl.mazi85.measurementserver.service.sample;

import pl.mazi85.measurementserver.model.Sample;

import java.util.List;
import java.util.Map;

public interface SampleService {
    void saveData(Map<String, Integer> valuesMap, Long measSourceId);

    List<Sample> readActualValues(Long measuringSourceId);
}
