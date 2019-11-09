package pl.edu.wsiz.waterstation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Calendar;

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
	@NotNull
	private float value;

	@Column(name = "MeasuredTime")
	private Date measuredTime = new Date(Calendar.getInstance().getTime().getTime());

}
