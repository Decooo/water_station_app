package pl.edu.wsiz.waterstation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ImportMeasuredValueDTO {

	private String physicalShieldId;
	private String physicalSensorId;
	private Float value;

}
