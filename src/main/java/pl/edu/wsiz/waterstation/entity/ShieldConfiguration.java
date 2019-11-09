package pl.edu.wsiz.waterstation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ShieldConfiguration")
@Getter
@Setter
@AllArgsConstructor
public class ShieldConfiguration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ShieldConfigurationId")
	private Long shieldConfigurationId;

	@JoinColumn(name = "ShieldId", referencedColumnName = "ShieldId", unique = true)
	@OneToOne(cascade = CascadeType.ALL)
	private Shield shieldId;

	@Column(name = "DeepSleep")
	private long deepSleep;
}
