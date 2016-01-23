package org.koko;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@IntegrationTest({ "server.port=0", "spring.zipkin.enabled=false"})
public class AppTests {

	@Test
	@Ignore
	public void shouldLoadApplicationContext() {
	}

}
