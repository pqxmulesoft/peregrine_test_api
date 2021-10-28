package com.example.peregrine.models;


public class GatewayRequestFixture {

    public static final String TYPE = "STANDALONE";
    public static final String NAME = "gateway-test-2";
    public static final String VERSION = "v1.0.1";


    public static GatewayRequest buildDefault() {
        return new GatewayRequest(TYPE, NAME, VERSION);
    }
}
