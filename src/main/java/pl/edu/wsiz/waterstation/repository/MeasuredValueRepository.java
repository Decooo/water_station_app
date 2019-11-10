package pl.edu.wsiz.waterstation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wsiz.waterstation.entity.MeasuredValue;

@Repository
public interface MeasuredValueRepository extends JpaRepository<MeasuredValue, Long> {
}
