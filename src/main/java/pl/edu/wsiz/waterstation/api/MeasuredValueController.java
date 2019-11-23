package pl.edu.wsiz.waterstation.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wsiz.waterstation.dto.LastValueDTO;
import pl.edu.wsiz.waterstation.service.MeasuredValueService;

@RestController
@RequestMapping("/value")
public class MeasuredValueController {

	private final MeasuredValueService measuredValueService;

	@Autowired
	public MeasuredValueController(MeasuredValueService measuredValueService) {
		this.measuredValueService = measuredValueService;
	}

	@GetMapping("/last")
	public ResponseEntity<LastValueDTO> getLastValue(){
		return new ResponseEntity(measuredValueService.getLastValue(), HttpStatus.OK);
	}
}
