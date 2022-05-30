package pl.mazi85.measurementserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mazi85.measurementserver.controller.sampledef.AddSampleDefForm;
import pl.mazi85.measurementserver.controller.sampledef.EditSampleDefForm;
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

    @Override
    public SampleDef create(AddSampleDefForm addSampleDefForm, Long measSourceId) {
        SampleDef sampleDef = sampleDefRepository.save(SampleDef.builder()
                .name(addSampleDefForm.getName())
                .register(addSampleDefForm.getRegister())
                .lowRange(addSampleDefForm.getLowRange())
                .highRange(addSampleDefForm.getHighRange())
                .unit(addSampleDefForm.getUnit())
                .build());

        MeasSource measSource = measSourceRepository.getReferenceById(measSourceId);
        measSource.getSampleDefs().add(sampleDef);
        measSourceRepository.save(measSource);
        return sampleDef;
    }

    @Override
    public EditSampleDefForm findSampleDefById(Long sampleDefId) {
        SampleDef sampleDef = sampleDefRepository.getReferenceById(sampleDefId);

        return EditSampleDefForm.builder()
                .name(sampleDef.getName())
                .register(sampleDef.getRegister())
                .lowRange(sampleDef.getLowRange())
                .highRange(sampleDef.getHighRange())
                .unit(sampleDef.getUnit())
                .build();
    }

    @Override
    public void editSampleDef(EditSampleDefForm editSampleDefForm, Long sampleDefId) {

        SampleDef sampleDef = sampleDefRepository.getReferenceById(sampleDefId);
        sampleDef.setName(editSampleDefForm.getName());
        sampleDef.setUnit(editSampleDefForm.getUnit());
        sampleDef.setRegister(editSampleDefForm.getRegister());
        sampleDef.setLowRange(editSampleDefForm.getLowRange());
        sampleDef.setHighRange(editSampleDefForm.getHighRange());
        sampleDefRepository.save(sampleDef);
    }
}
