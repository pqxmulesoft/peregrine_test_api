package com.example.peregrine.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.example.peregrine.models.GatewayRequestFixture.NAME;
import static com.example.peregrine.models.GatewayRequestFixture.TYPE;
import static com.example.peregrine.models.GatewayRequestFixture.VERSION;
import static org.junit.jupiter.api.Assertions.*;
public class GatewayRequestTest {
    @Test
    public void givenParameters_whenConstructor_objectShouldNotBeNull() {
        GatewayRequest gatewayResquest = GatewayRequestFixture.buildDefault();
        assertNotNull(gatewayResquest);
    }
    @Test
    public void givenObject_whenGetFields_shouldReturnTheSameFieldsSetInConstructor() {
        GatewayRequest gatewayResquest = GatewayRequestFixture.buildDefault();
        Assertions.assertEquals(GatewayRequestFixture.TYPE, gatewayResquest.getType());
        Assertions.assertEquals(GatewayRequestFixture.NAME, gatewayResquest.getName());
        Assertions.assertEquals(GatewayRequestFixture.VERSION, gatewayResquest.getVersion());
    }
    @Test
    public void givenObject_whenSetAndGetFields_shouldReturnTheSameFieldsSet() {
        GatewayRequest gatewayResquest = GatewayRequestFixture.buildDefault();
        gatewayResquest.setType("NewStand");
        gatewayResquest.setName("Peregrine1");
        gatewayResquest.setVersion("1.0.1");

        assertEquals("NewStand", gatewayResquest.getType());
        assertEquals("Peregrine1", gatewayResquest.getName());
        assertEquals("1.0.1", gatewayResquest.getVersion());
    }
}
