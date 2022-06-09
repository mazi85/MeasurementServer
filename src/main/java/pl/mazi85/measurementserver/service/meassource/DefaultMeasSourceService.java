package pl.mazi85.measurementserver.service.meassource;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mazi85.measurementserver.controller.meassource.CreateMeasSourceForm;
import pl.mazi85.measurementserver.controller.meassource.EditMeasSourceForm;
import pl.mazi85.measurementserver.controller.meassource.ListMeasSourceForm;
import pl.mazi85.measurementserver.model.CommProtocol;
import pl.mazi85.measurementserver.model.MeasSource;
import pl.mazi85.measurementserver.model.SampleDef;
import pl.mazi85.measurementserver.repository.CommProtocolRepository;
import pl.mazi85.measurementserver.repository.MeasSourceRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultMeasSourceService implements MeasSourceService {

    private final MeasSourceRepository measSourceRepository;
    private final CommProtocolRepository commProtocolRepository;

    @Override
    @Transactional
    public void createMeasSource(CreateMeasSourceForm createMeasSourceForm) {

        Long commProtocolId = createMeasSourceForm.getCommProtocolId();
        CommProtocol commProtocol = commProtocolRepository.getReferenceById(commProtocolId);

        measSourceRepository.save(MeasSource.builder()
                .name(createMeasSourceForm.getName())
                .ip(createMeasSourceForm.getIp())
                .port(createMeasSourceForm.getPort())
                .commProtocol(commProtocol)
                .recording(createMeasSourceForm.isRecording())
                .build());
    }

    @Override
    public List<ListMeasSourceForm> listAll() {

        List<MeasSource> result = measSourceRepository.findAll();

        List<ListMeasSourceForm> collect = result.stream()
                .map(r ->
                {
                    return ListMeasSourceForm.builder()
                            .id(r.getId())
                            .name(r.getName())
                            .connectionString(r.getConnectionString())
                            .sampleDefs(r.getSampleDefs())
                            .recording((r.isRecording()))
                            .build();
                })
                .collect(Collectors.toList());

        return collect;
    }

    @Override
    public void delete(Long measSourceId) {
        MeasSource entity = measSourceRepository.getReferenceById(measSourceId);
        measSourceRepository.delete(entity);
    }

    @Override
    public EditMeasSourceForm findById(Long id) {
        MeasSource measSource = measSourceRepository.getReferenceById(id);
        return EditMeasSourceForm.builder()
                .name(measSource.getName())
                .ip(measSource.getIp())
                .port(measSource.getPort())
                .commProtocolId(measSource.getCommProtocol().getId())
                .build();
    }

    @Override
    public void editMeasSource(EditMeasSourceForm editMeasSourceForm, Long measSourceId) {
        MeasSource measSource = measSourceRepository.getReferenceById(measSourceId);
        CommProtocol commProtocol = commProtocolRepository.getReferenceById(editMeasSourceForm.getCommProtocolId());

        measSource.setName(editMeasSourceForm.getName());
        measSource.setIp(editMeasSourceForm.getIp());
        measSource.setPort(editMeasSourceForm.getPort());
        measSource.setCommProtocol(commProtocol);
        measSource.setRecording(editMeasSourceForm.isRecording());
        measSourceRepository.save(measSource);
    }

    @Override
    public String getMeasSourceConnectionString(Long measSourceId) {
        MeasSource measSource = measSourceRepository.getReferenceById(measSourceId);
        return measSource.getConnectionString();
    }

    @Override
    public Map<Long,Integer> getMeasSourceRegisters(Long measSourceId) {
        MeasSource measSource = measSourceRepository.getReferenceById(measSourceId);
        return measSource.getSampleDefs().stream()
                //.map(SampleDef::getRegister)
                .collect(Collectors.toMap(
                        SampleDef::getId,SampleDef::getRegister
                ));
    }

    @Override
    public List<Long> getScheduleEnableMeasSources() {
        return measSourceRepository.findAllByRecordingIsTrue().stream()
                .map(MeasSource::getId)
                .collect(Collectors.toList());
    }
}
