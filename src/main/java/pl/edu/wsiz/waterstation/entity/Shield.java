package pl.edu.wsiz.waterstation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Shield")
@Getter
@Setter
@AllArgsConstructor
public class Shield {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ShieldId")
	private Long shieldId;

	@Column(name = "PhysicalShieldId")
	private String physicalShieldId;
	@Column(name = "Name")
	private String name;
	@Column(name = "MAC")
	private String mac;
	@Column(name = "IP")
	private String ip;
	@Column(name = "Location")
	private String location;
}
