package goods;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = MvcTestConfig.class)
public abstract class AbstractMvcTest {

    @Autowired
    protected WebApplicationContext wac;
    @Autowired
    protected ObjectMapper objectMapper;

    protected MockMvc mockMvc;

    @Before
    public void initMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
}
