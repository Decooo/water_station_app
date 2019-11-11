package pl.edu.wsiz.waterstation.importsensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wsiz.waterstation.config.ImportDataConfig;
import pl.edu.wsiz.waterstation.entity.Shield;
import pl.edu.wsiz.waterstation.repository.ShieldRepository;

import java.util.Optional;

@Service
public class ImportDataService {

	@Autowired
	private ShieldRepository shieldRepository;

	private final ImportDataConfig importDataConfig;

	@Autowired
	public ImportDataService() {
		this.importDataConfig = new ImportDataConfig();
	}

	public long importDataAndReturnDeepSleep(String inputData) {

		Optional<Shield> shieldId = shieldRepository.findById(1L);
		shieldId.ifPresent(shield -> System.out.println("shieldId.get().getShieldId() = " + shield.getShieldId()));

		return importDataConfig.getDefaultDeepSleep();
	}
}
