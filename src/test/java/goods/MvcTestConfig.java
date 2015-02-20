package goods;

import goods.service.GoodsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.persistence.EntityManagerFactory;

import static org.mockito.Mockito.mock;

@Configuration
@PropertySource(value = "classpath:/test.properties", ignoreResourceNotFound = true)
@EnableWebMvc
@Import(CommonConfig.class)
@ComponentScan(basePackages = {
        "goods.controller"
})
public class MvcTestConfig extends WebConfig {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return mock(EntityManagerFactory.class);
    }

    @Bean
    public GoodsService goodsService() {
        return mock(GoodsService.class);
    }

}
