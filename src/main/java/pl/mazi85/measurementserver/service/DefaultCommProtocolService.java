package pl.mazi85.measurementserver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.mazi85.measurementserver.model.CommProtocol;
import pl.mazi85.measurementserver.repository.CommProtocolRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCommProtocolService implements CommProtocolService{

    private final CommProtocolRepository commProtocolRepository;
    @Override
    public List<CommProtocol> readAll() {
        return commProtocolRepository.findAll();
    }
}
