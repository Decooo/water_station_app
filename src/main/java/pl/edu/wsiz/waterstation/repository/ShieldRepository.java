package pl.edu.wsiz.waterstation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wsiz.waterstation.entity.Shield;

@Repository
public interface ShieldRepository extends JpaRepository<Shield, Long> {
}
