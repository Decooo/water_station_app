package pl.edu.wsiz.waterstation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.edu.wsiz.waterstation.entity.Sensor;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Long> {

	Optional<Sensor> findByPhysicalSensorId(String physicalSensorId);
	@Query(value = "SELECT * FROM sensor s ORDER BY s.sensor_id DESC", nativeQuery = true)
	List<Sensor> getSensors();
}
