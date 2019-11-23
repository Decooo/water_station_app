package pl.edu.wsiz.waterstation.repository;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wsiz.waterstation.entity.MeasuredValue;

import java.util.List;

@Repository
public interface MeasuredValueRepository extends JpaRepository<MeasuredValue, Long> {

	@Query(value = "SELECT * FROM measured_value ORDER BY measured_value_id DESC LIMIT 1", nativeQuery = true)
	MeasuredValue getLastValue();

	MeasuredValue findByMeasuredValueId(long id);

}
