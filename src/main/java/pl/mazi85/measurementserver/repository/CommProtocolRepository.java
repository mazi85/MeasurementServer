package pl.mazi85.measurementserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mazi85.measurementserver.model.CommProtocol;

public interface CommProtocolRepository extends JpaRepository<CommProtocol,Long> {
}
