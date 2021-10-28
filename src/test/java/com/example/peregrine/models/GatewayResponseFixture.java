package com.example.peregrine.models;

public class GatewayResponseFixture {
    public static final String ORGID = "123";
    public static final String ID = "8bd";
    public static final String TYPE = "STANDALONE";
    public static final String NAME = "gateway-test-1";
    public static final String VERSION = "v1.0.0";




    public static GatewayResponse buildDefault(){
        return new GatewayResponse(ORGID, ID, TYPE, NAME, VERSION);
    }

}
