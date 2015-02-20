package goods;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@PropertySource(value = "classpath:/application.properties", ignoreResourceNotFound = true)
@EnableWebMvc
@EnableTransactionManagement
@Import({PersistenceConfig.class})
@ComponentScan(basePackages = {
        "goods.service",
        "goods.controller"
})
public class ServletConfig extends WebConfig {
}
