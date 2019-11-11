package pl.edu.wsiz.waterstation.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties("import.data")
@ConditionalOnMissingBean (name = "ImportDataConfig")
@Configuration
@Setter
@Getter
public class ImportDataConfig {

	private boolean enable = false;
	private String url;
	private int port;
	private Long defaultDeepSleep = 5000000L;
}
