package pl.mazi85.measurementserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mazi85.measurementserver.controller.CreateMeasSourceForm;
import pl.mazi85.measurementserver.controller.ListMeasSourceForm;
import pl.mazi85.measurementserver.model.CommProtocol;
import pl.mazi85.measurementserver.model.MeasSource;
import pl.mazi85.measurementserver.repository.CommProtocolRepository;
import pl.mazi85.measurementserver.repository.MeasSourceRepository;

import javax.transaction.Transactional;
import java.util.List;
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
                            .build();
                })
                .collect(Collectors.toList());

        return collect;
    }
}
