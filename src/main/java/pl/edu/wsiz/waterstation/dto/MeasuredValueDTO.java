package pl.edu.wsiz.waterstation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MeasuredValueDTO {
	
	private String physicalShieldId;
	private String physicalSensorId;
	private Float value;

}
