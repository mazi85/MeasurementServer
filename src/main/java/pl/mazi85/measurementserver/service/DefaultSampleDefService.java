package pl.mazi85.measurementserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mazi85.measurementserver.controller.sampledef.ListSampleDefForm;
import pl.mazi85.measurementserver.model.MeasSource;
import pl.mazi85.measurementserver.model.SampleDef;
import pl.mazi85.measurementserver.repository.MeasSourceRepository;
import pl.mazi85.measurementserver.repository.SampleDefRepository;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class DefaultSampleDefService implements SampleDefService {

    private final SampleDefRepository sampleDefRepository;
    private final MeasSourceRepository measSourceRepository;

    @Override
    public List<ListSampleDefForm> listAllByMeasSource(Long measSourceId) {
        MeasSource measSource = measSourceRepository.getReferenceById(measSourceId);
        List<SampleDef> sampleDefs = sampleDefRepository.findAllByMeasSources(measSource);
        List<ListSampleDefForm> listSampleDefForms = sampleDefs.stream()
                .map(def -> ListSampleDefForm.builder()
                        .id(def.getId())
                        .name(def.getName())
                        .unit(def.getUnit())
                        .lowRange(def.getLowRange())
                        .highRange(def.getHighRange())
                        .register(def.getRegister())
                        .build())
                        .collect(Collectors.toList());
        return listSampleDefForms;
    }
}
