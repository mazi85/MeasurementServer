package pl.mazi85.measurementserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mazi85.measurementserver.model.SampleDef;

public interface SampleDefRepository extends JpaRepository<SampleDef,Long> {
}
