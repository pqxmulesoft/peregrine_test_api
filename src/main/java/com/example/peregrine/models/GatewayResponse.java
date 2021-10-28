/*
 * (c) 2003-2021 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */

package com.example.peregrine.models;
/*
 * (c) 2003-2021 MuleSoft, Inc. This software is protected under international copyright
 * law. All use of this software is subject to MuleSoft's Master Subscription Agreement
 * (or other master license agreement) separately entered into in writing between you and
 * MuleSoft. If such an agreement is not in place, you may not use the software.
 */

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 */
@Entity
public class GatewayResponse {

    /**
     * Target Id.
     */
    private String orgId;

    private @Id
    String id;

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



    public GatewayResponse() {
    }

    /**
     * Creates an instance of a Gateway.
     *
     * @param id                        Gateway Id.
     * @param orgId                     Organization Id.
     * @param type                      Gateway type.
     * @param name                      Gateway name.
     * @param version                   Gateway version.
     */
    @JsonCreator
    public GatewayResponse(@JsonProperty(value = "orgId", required = true) String orgId,
                           @JsonProperty(value = "id", required = true) String id,
                           @JsonProperty(value = "type", required = false) String type,
                           @JsonProperty(value = "name", required = false) String name,
                           @JsonProperty(value = "version", required = false) String version) {
        this.orgId = orgId;
        this.id = id;
        this.type = type;
        this.name = name;
        this.version = version;

    }
    public String getOrgId() {return this.orgId;}
    public String getId() {
        return this.id;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getType() {return type;}

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }

    public String getVersion() {
        return this.version;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        GatewayResponse that = (GatewayResponse) o;
        if (!this.orgId.equals(that.orgId)) {
            return false;
        }
        if (!this.id.equals(that.id)) {
            return false;
        }
        if (!this.type.equals(that.type)) {
            return false;
        }
        if (!this.name.equals(that.name)) {
            return false;
        }
        return this.version != null ? !this.version.equals(that.version) : that.version != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int result = this.id.hashCode();
        result = this.orgId.hashCode();
        result = 31 * result + this.type.hashCode();
        result = 31 * result + this.name.hashCode();
        result = 31 * result + (this.version != null ? this.version.hashCode() : 0);
        return result;
    }

    /**
     * {@inheritDoc}
     */
//    @Override
//    public String toString() {
//        return "GatewayRequest{"
//                + "id='" + this.id + '\''
//                + ", name='" + this.type + '\''
//                + ", type='" + this.name + '\''
//                + ", version='" + this.version + '\''
//                + '}';
//    }
}


