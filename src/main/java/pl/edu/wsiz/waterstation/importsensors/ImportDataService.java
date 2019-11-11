package pl.edu.wsiz.waterstation.importsensors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wsiz.waterstation.config.ImportDataConfig;

@Service
public class ImportDataService {

	public long importDataAndReturnDeepSleep(String inputData){

		return 5000000L;
	}
}
