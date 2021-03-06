package pl.edu.wsiz.waterstation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wsiz.waterstation.entity.MeasuredValue;

import java.util.List;
import java.util.Optional;

@Repository
public interface MeasuredValueRepository extends JpaRepository<MeasuredValue, Long> {

	@Query(value = "SELECT * FROM measured_value m where m.sensor_id = :sensorId ORDER BY measured_value_id DESC LIMIT 1", nativeQuery = true)
	Optional<MeasuredValue> getLastValue(String sensorId);

	@Query(value = "SELECT * FROM measured_value m where m.measured_time >= :dateFrom AND m.measured_time <= :dateTo and m.sensor_id = :sensorId ORDER BY measured_value_id DESC", nativeQuery = true)
	List<MeasuredValue> getIntervalValues(String dateFrom, String dateTo, String sensorId);

	@Query(value = "SELECT * FROM measured_value m where m.sensor_id = :sensorId ORDER BY measured_value_id DESC LIMIT 100", nativeQuery = true)
	List<MeasuredValue> getLast100Values(String sensorId);

	MeasuredValue findByMeasuredValueId(long id);

}
