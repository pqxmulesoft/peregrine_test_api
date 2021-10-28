package com.example.peregrine.models;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Request object to register a new gateway.
 */

public class GatewayRequest {

    /**
     * Target Id.
     */
//    private String id;

    /**
     * Target type.
     */
    private String type;

    /**
     * Target name.
     */
    private String name;

    /**
     * Target version.
     */
    private String version;


    /**
     * Create a new {@link GatewayRequest}.
//     * @param id    Registration id.
     * @param type       Registration type.
     * @param type      Registration agent type.
     * @param version Registration agent info.
     */
    @JsonCreator
    public GatewayRequest(
//            @JsonProperty("id") String id,
            @JsonProperty("type") String type,
            @JsonProperty("name") String name,
            @JsonProperty("version") String version) {
//        this.id = id;
        this.type = type;
        this.name = name;
        this.version = version;
    }

    /**
     * Retrieves the field id.
     * @return the value for the field id.
     */
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
    /**
     * Retrieves the field type.
     * @return the value for the field type.
     */
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    /**
     * Retrieves the field name.
     * @return the value for the field name.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the field version.
     * @return the value for the field version.
     */
    public String getVersion() {
        return version;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}


