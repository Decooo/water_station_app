package pl.edu.wsiz.waterstation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wsiz.waterstation.entity.MeasuredValue;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeasuredValueRepository extends JpaRepository<MeasuredValue, Long> {

	@Query(value = "SELECT * FROM measured_value ORDER BY measured_value_id DESC LIMIT 1", nativeQuery = true)
	Optional<MeasuredValue> getLastValue();

	@Query(value = "SELECT * FROM measured_value m where m.measured_time >= :dateFrom AND m.measured_time <= :dateTo ORDER BY measured_value_id DESC", nativeQuery = true)
	List<MeasuredValue> getIntervalValues(String dateFrom, String dateTo);

	@Query(value = "SELECT * FROM measured_value m where m.sensor_id = 3 ORDER BY measured_value_id DESC LIMIT 500", nativeQuery = true)
	List<MeasuredValue> getLast500Values();

	MeasuredValue findByMeasuredValueId(long id);

}
