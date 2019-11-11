package pl.edu.wsiz.waterstation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.wsiz.waterstation.config.ImportDataConfig;
import pl.edu.wsiz.waterstation.importsensors.ImportDataFacade;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class WaterstationApplication {

	@Autowired
	private ImportDataConfig importDataConfig;

	public static void main(String[] args) {
		SpringApplication.run(WaterstationApplication.class, args);
	}

	@PostConstruct
	public void importData() {
		if (importDataConfig.isEnable()) {
			ImportDataFacade socket = new ImportDataFacade();
			socket.startImport(importDataConfig.getUrl(), importDataConfig.getPort());
		}
	}
}
