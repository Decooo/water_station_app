package pl.edu.wsiz.waterstation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.edu.wsiz.waterstation.importsensors.ImportDataFacade;

@SpringBootApplication
public class WaterstationApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaterstationApplication.class, args);
		ImportDataFacade socket = new ImportDataFacade();
		socket.startImport("http://localhost", 8081);
	}
}
