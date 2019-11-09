package pl.edu.wsiz.waterstation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sensor")
@Getter
@Setter
@AllArgsConstructor
public class Sensor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SensorId")
	private Long sensorId;

	@ManyToOne
	@JoinColumn(name = "ShieldId", referencedColumnName = "ShieldId")
	private Shield shield;

	@Column(name = "PhysicalSensorId")
	@NotNull
	private String physicalSensorId;
	@Column(name = "Name")
	private String name;
	@Column(name = "MeasuredValue")
	private String measuredValue;

}
