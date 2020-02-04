package pl.edu.wsiz.waterstation.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wsiz.waterstation.dto.ValueDTO;
import pl.edu.wsiz.waterstation.service.MeasuredValueService;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/value")
public class MeasuredValueController {

	private final MeasuredValueService measuredValueService;

	@Autowired
	public MeasuredValueController(MeasuredValueService measuredValueService) {
		this.measuredValueService = measuredValueService;
	}

	@GetMapping("/last")
	public ResponseEntity<ValueDTO> getLastValue(@RequestParam(name = "sensorId") String sensorId) {
		try {
			return new ResponseEntity<>(measuredValueService.getLastValue(sensorId), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/intervalValues")
	public ResponseEntity<List<ValueDTO>> getIntervalValues(@RequestParam(name = "dateFrom") String dateFrom, @RequestParam(name = "dateTo") String dateTo, @RequestParam(name = "sensorId") String sensorId) {
		return new ResponseEntity<>(measuredValueService.getIntervalValues(dateFrom, dateTo, sensorId), HttpStatus.OK);
	}

	@GetMapping("/last100")
	public ResponseEntity<List<ValueDTO>> getLast100(@RequestParam(name = "sensorId") String sensorId) {
		return new ResponseEntity<>(measuredValueService.getLast100Values(sensorId), HttpStatus.OK);
	}
}
