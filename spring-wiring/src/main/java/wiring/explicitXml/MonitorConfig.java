package wiring.explicitXml;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@Configuration
@ImportResource("classpath:monitor-config.xml")
public class MonitorConfig {
}
