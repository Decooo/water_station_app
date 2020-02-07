package pl.edu.wsiz.waterstation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ValueDTO {
	private Long valueId;
	private Date measuredTime;
	private float value;
	private SensorDTO sensor;
}
