package org.koko;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@TestPropertySource(locations = "classpath:bootstrap-test.properties")
@IntegrationTest({"server.port=0"})
public class AppTests {

	@Test
	public void shouldLoadApplicationContext() {
	}

}
