package goods;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = "classpath:/test.properties", ignoreResourceNotFound = true)
@EnableTransactionManagement
@Import({CommonConfig.class, PersistenceConfig.class})
@ComponentScan(basePackages = {
        "goods.service"
})
public class ITConfig {
}
