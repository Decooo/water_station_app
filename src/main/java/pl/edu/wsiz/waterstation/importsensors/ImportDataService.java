package pl.edu.wsiz.waterstation.importsensors;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wsiz.waterstation.config.ImportDataConfig;
import pl.edu.wsiz.waterstation.dto.ImportMeasuredValueDTO;
import pl.edu.wsiz.waterstation.dto.SaveMeasuredValueDTO;
import pl.edu.wsiz.waterstation.entity.MeasuredValue;
import pl.edu.wsiz.waterstation.entity.Sensor;
import pl.edu.wsiz.waterstation.entity.Shield;
import pl.edu.wsiz.waterstation.entity.ShieldConfiguration;
import pl.edu.wsiz.waterstation.repository.MeasuredValueRepository;
import pl.edu.wsiz.waterstation.repository.SensorRepository;
import pl.edu.wsiz.waterstation.repository.ShieldConfigurationRepository;
import pl.edu.wsiz.waterstation.repository.ShieldRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Optional;

@Service
public class ImportDataService {

	private final ImportDataConfig importDataConfig;
	@Autowired
	private ShieldRepository shieldRepository;
	@Autowired
	private SensorRepository sensorRepository;
	@Autowired
	private MeasuredValueRepository measuredValueRepository;
	@Autowired
	private ShieldConfigurationRepository shieldConfigurationRepository;

	@Autowired
	public ImportDataService() {
		this.importDataConfig = new ImportDataConfig();
	}

	public long importDataAndReturnDeepSleep(String inputData) {
		ImportMeasuredValueDTO importMeasuredValueDTO = convertInputJSON(inputData);
		Optional<Shield> shield = shieldRepository.findByPhysicalShieldId(importMeasuredValueDTO.getPhysicalShieldId());
		if (shield.isPresent()) {
			Optional<Sensor> sensor = sensorRepository.findByPhysicalSensorId(importMeasuredValueDTO.getPhysicalSensorId());
			if (sensor.isPresent()) {
				SaveMeasuredValueDTO saveMeasuredValueDTO = new SaveMeasuredValueDTO(importMeasuredValueDTO.getValue(),
						new Timestamp(Calendar.getInstance().getTimeInMillis()),
						sensor.get());

				measuredValueRepository.save(new ModelMapper().map(saveMeasuredValueDTO, MeasuredValue.class));

				Optional<ShieldConfiguration> shieldConfiguration = shieldConfigurationRepository.findById(shield.get().getShieldId());
				if (shieldConfiguration.isPresent() && shieldConfiguration.get().getDeepSleep() > 0) {
					return shieldConfiguration.get().getDeepSleep();
				}
			}
		}

		return importDataConfig.getDefaultDeepSleep();
	}

	private ImportMeasuredValueDTO convertInputJSON(String inputData) {
		return new Gson().fromJson(inputData, ImportMeasuredValueDTO.class);
	}
}
