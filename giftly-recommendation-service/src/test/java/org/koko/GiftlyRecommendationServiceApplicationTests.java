package org.koko;

import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = GiftlyRecommendationServiceApplication.class)
@WebAppConfiguration
public class GiftlyRecommendationServiceApplicationTests {

	@BeforeClass
	public static void setUpClass() throws Exception {
		EmbeddedCassandraServerHelper.startEmbeddedCassandra();
		Thread.sleep(2000);
	}

	@AfterClass
	public static void tearDownClass() throws Exception {
		EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
	}

	@Test
//	@Ignore("io.netty dependencies should be previously resolved") // TODO
	public void contextLoads() {
	}

}
