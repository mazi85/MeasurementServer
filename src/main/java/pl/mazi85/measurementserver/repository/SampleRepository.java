package pl.mazi85.measurementserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.mazi85.measurementserver.model.Sample;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface SampleRepository extends JpaRepository<Sample,Long> {

    @Query(value = "SELECT * FROM samples JOIN meas_serv.sources_defs sd ON samples.sample_def_id = sd.sample_def_id WHERE sd.meas_source_id = :measuringId ORDER BY created_on DESC LIMIT :valuesQuantity",nativeQuery = true)
    List<Sample> findAllByMeasSourceOrderByCreatedOn(Integer valuesQuantity, Long measuringId);
}
