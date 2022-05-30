package pl.mazi85.measurementserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.mazi85.measurementserver.model.MeasSource;
import pl.mazi85.measurementserver.model.SampleDef;

import java.util.List;

public interface SampleDefRepository extends JpaRepository<SampleDef,Long> {

    //@Query(value = "SELECT s FROM SampleDef s WHERE s.measSources =:measSource")
    List<SampleDef> findAllByMeasSources(MeasSource measSource);
}
