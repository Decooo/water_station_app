package pl.edu.wsiz.waterstation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.wsiz.waterstation.entity.ShieldConfiguration;

@Repository
public interface ShieldConfigurationRepository extends JpaRepository<ShieldConfiguration, Long> {
}
