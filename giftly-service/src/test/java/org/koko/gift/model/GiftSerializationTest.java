package org.koko.gift.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class GiftSerializationTest {

    @Test
    public void shouldSerializeGiftFixture_1() throws Exception {
        String fixture = IOUtils.toString(getClass().getResourceAsStream("/fixtures/gift_1.json"), "UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        assertNotNull(mapper.readValue(fixture.getBytes(), Gift.class));
    }

    @Test
    public void shouldSerializeGiftFixture_2() throws Exception {
        String fixture = IOUtils.toString(getClass().getResourceAsStream("/fixtures/gift_2.json"), "UTF-8");
        ObjectMapper mapper = new ObjectMapper();
        assertNotNull(mapper.readValue(fixture.getBytes(), Gift.class));
    }

}
