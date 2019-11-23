package pl.edu.wsiz.waterstation.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wsiz.waterstation.config.ImportDataConfig;
import pl.edu.wsiz.waterstation.importsensors.ImportDataFacade;
import pl.edu.wsiz.waterstation.importsensors.ImportDataService;

@RestController
@RequestMapping("/socket")
public class SocketController {

	private final ImportDataConfig importDataConfig;
	private ImportDataFacade importDataFacade;

	@Autowired
	public SocketController(ImportDataService importDataService, ImportDataConfig importDataConfig) {
		this.importDataFacade = new ImportDataFacade(importDataService);
		this.importDataConfig = importDataConfig;
	}

	@GetMapping("/status")
	public ResponseEntity getStatus() {
		return new ResponseEntity(importDataFacade.isRunning(), HttpStatus.OK);
	}

	@GetMapping("/start")
	public ResponseEntity startImport() {
		importDataFacade.startImport(importDataConfig.getUrl(), importDataConfig.getPort());
		return new ResponseEntity(HttpStatus.OK);
	}

	@GetMapping("/stop")
	public ResponseEntity stopImport() {
		importDataFacade.stopImport();
		return new ResponseEntity(HttpStatus.OK);
	}
}
