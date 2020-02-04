package pl.edu.wsiz.waterstation.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wsiz.waterstation.dto.SensorDTO;
import pl.edu.wsiz.waterstation.entity.Sensor;
import pl.edu.wsiz.waterstation.repository.SensorRepository;

import java.util.List;

@Service
public class SensorsService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorsService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<SensorDTO> getSensors() {
        List<Sensor> measuredValues = sensorRepository.getSensors();
        return new ModelMapper().map(measuredValues, new TypeToken<List<SensorDTO>>() {
        }.getType());
    }
}
