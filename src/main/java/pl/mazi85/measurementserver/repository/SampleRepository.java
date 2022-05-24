package pl.mazi85.measurementserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mazi85.measurementserver.model.Sample;

public interface SampleRepository extends JpaRepository<Sample,Long> {
}
