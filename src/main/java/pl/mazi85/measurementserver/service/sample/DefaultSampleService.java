package pl.mazi85.measurementserver.service.sample;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mazi85.measurementserver.model.MeasSource;
import pl.mazi85.measurementserver.model.Sample;
import pl.mazi85.measurementserver.model.SampleDef;
import pl.mazi85.measurementserver.repository.MeasSourceRepository;
import pl.mazi85.measurementserver.repository.SampleDefRepository;
import pl.mazi85.measurementserver.repository.SampleRepository;
import pl.mazi85.measurementserver.service.CalculateEngDataService;
import pl.mazi85.measurementserver.service.meassource.MeasSourceService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DefaultSampleService implements SampleService{

    private final SampleRepository sampleRepository;
    private final MeasSourceRepository measSourceRepository;
    private final SampleDefRepository sampleDefRepository;
    private final CalculateEngDataService calculateEngDataService;
    private final MeasSourceService measSourceService;

    public void saveData (Map<String, Integer> valuesMap,Long measSourceId){
        MeasSource measSource = measSourceRepository.getReferenceById(measSourceId);
        LocalDateTime createdOn = LocalDateTime.now();
        for (Map.Entry<String, Integer> sampleDefIdValueEntry : valuesMap.entrySet()) {
            Sample sample = new Sample();
            SampleDef sampleDef = sampleDefRepository.getReferenceById(Long.valueOf(sampleDefIdValueEntry.getKey()));
            sample.setSampleDef(sampleDef);
            Integer rawValue = sampleDefIdValueEntry.getValue();
            sample.setRawValue(rawValue);
            //sample.setMeasSource(measSource);
            sample.setCreatedOn(createdOn);
            sample.setEngValue(calculateEngDataService.getEngValue(sampleDef,rawValue));
            sampleRepository.save(sample);
        } {

        }
    }

    @Override
    public List<Sample> readActualValues(Long measuringSourceId) {
        int size = measSourceService.getMeasSourceRegisters(measuringSourceId).values().size();
        return sampleRepository.findAllByMeasSourceOrderByCreatedOn(size,measuringSourceId);
    }


}
