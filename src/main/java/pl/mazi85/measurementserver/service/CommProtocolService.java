package pl.mazi85.measurementserver.service;

import pl.mazi85.measurementserver.model.CommProtocol;

import java.util.List;

public interface CommProtocolService {
    List<CommProtocol> readAll();
}
