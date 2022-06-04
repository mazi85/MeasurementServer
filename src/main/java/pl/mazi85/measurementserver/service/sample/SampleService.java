package pl.mazi85.measurementserver.service.sample;

import java.util.Map;

public interface SampleService {
    void saveData(Map<String, Integer> valuesMap, Long measSourceId);
}
