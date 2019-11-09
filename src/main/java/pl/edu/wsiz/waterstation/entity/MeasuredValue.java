package pl.edu.wsiz.waterstation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "MeasuredValue")
@Getter
@Setter
@AllArgsConstructor
public class MeasuredValue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MeasuredValueId")
	private Long measuredValuId;

	@ManyToOne
	@JoinColumn(name = "SensorId", referencedColumnName = "SensorId")
	private Sensor sensor;

	@Column(name = "Value")
	private float value;

	@Column(name = "MeasuredTime")
	private Date measuredTime;

}
