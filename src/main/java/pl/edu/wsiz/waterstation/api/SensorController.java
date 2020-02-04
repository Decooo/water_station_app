package pl.edu.wsiz.waterstation.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wsiz.waterstation.dto.SensorDTO;
import pl.edu.wsiz.waterstation.service.SensorsService;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final SensorsService sensorsService;

    public SensorController(SensorsService sensorsService) {
        this.sensorsService = sensorsService;
    }

    @GetMapping("/sensors-list")
    public ResponseEntity<List<SensorDTO>> getSensors() {
        return new ResponseEntity<>(sensorsService.getSensors(), HttpStatus.OK);
    }
}
