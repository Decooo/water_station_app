package pl.edu.wsiz.waterstation.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
	public ResponseEntity<ValueDTO> getLastValue() {
		try {
			return new ResponseEntity<>(measuredValueService.getLastValue(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/intervalValues")
	public ResponseEntity<List<ValueDTO>> getIntervalValues(String dateFrom, String dateTo) {
		return new ResponseEntity<>(measuredValueService.getIntervalValues(dateFrom, dateTo), HttpStatus.OK);
	}

	@GetMapping("/last500")
	public ResponseEntity<List<ValueDTO>> getLast500() {
		return new ResponseEntity<>(measuredValueService.getLast100Values(), HttpStatus.OK);
	}
}
