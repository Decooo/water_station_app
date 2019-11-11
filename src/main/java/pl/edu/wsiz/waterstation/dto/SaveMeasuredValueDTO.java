package pl.edu.wsiz.waterstation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import pl.edu.wsiz.waterstation.entity.Sensor;

import java.sql.Timestamp;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SaveMeasuredValueDTO {

	private float value;
	private Timestamp measuredTime;
	private Sensor sensor;

}
