package pl.edu.wsiz.waterstation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "ShieldConfiguration")
@Getter
@Setter
@RequiredArgsConstructor
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
	private long deepSleep = 5000000L;
}
