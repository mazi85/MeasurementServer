package pl.mazi85.measurementserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mazi85.measurementserver.model.MeasSource;

import java.util.List;

public interface MeasSourceRepository extends JpaRepository<MeasSource,Long> {


    List<MeasSource> findAllByRecordingIsTrue();


}
