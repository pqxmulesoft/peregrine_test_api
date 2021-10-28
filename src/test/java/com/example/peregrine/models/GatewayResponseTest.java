package com.example.peregrine.models;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import static com.example.peregrine.models.GatewayResponseFixture.ORGID;
import static com.example.peregrine.models.GatewayResponseFixture.ID;
import static com.example.peregrine.models.GatewayResponseFixture.NAME;
import static com.example.peregrine.models.GatewayResponseFixture.TYPE;
import static com.example.peregrine.models.GatewayResponseFixture.VERSION;
import static org.junit.jupiter.api.Assertions.*;


public class GatewayResponseTest {
    @Test
    public void givenParameters_whenConstructor_objectShouldNotBeNull() {
        GatewayResponse gatewayResponse = GatewayResponseFixture.buildDefault();
        assertNotNull(gatewayResponse);
    }
    @Test
    public void givenObject_whenGetFields_shouldReturnTheSameFieldsSetInConstructor() {
        GatewayResponse gatewayResponse = GatewayResponseFixture.buildDefault();
        Assertions.assertEquals(GatewayResponseFixture.ORGID, gatewayResponse.getOrgId());
        Assertions.assertEquals(GatewayResponseFixture.ID, gatewayResponse.getId());
        Assertions.assertEquals(GatewayResponseFixture.TYPE, gatewayResponse.getType());
        Assertions.assertEquals(GatewayResponseFixture.NAME, gatewayResponse.getName());
        Assertions.assertEquals(GatewayResponseFixture.VERSION, gatewayResponse.getVersion());
    }
    @Test
    public void givenObject_whenSetAndGetFields_shouldReturnTheSameFieldsSet() {
        GatewayResponse gatewayResponse = GatewayResponseFixture.buildDefault();
        gatewayResponse.setOrgId("2");
        gatewayResponse.setId("qos");
        gatewayResponse.setType("New");
        gatewayResponse.setName("Peregrine1");
        gatewayResponse.setVersion("1.0.1");

        assertEquals("2",gatewayResponse.getOrgId());
        assertEquals("qos",gatewayResponse.getId());
        assertEquals("New", gatewayResponse.getType());
        assertEquals("Peregrine1", gatewayResponse.getName());
        assertEquals("1.0.1", gatewayResponse.getVersion());
    }
}
