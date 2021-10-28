package com.example.peregrine.service;

import com.example.peregrine.models.GatewayResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
/**
 * This interface is implemented to handle gateway management.
 */
//extends JpaRepository<GatewayResponse, String>
public interface GatewayService extends JpaRepository<GatewayResponse, String>, GatewayServiceCustom{

    /**
     * Retrieve a list of all gateways related to the specified organization id and environment id (optional).
     *
     * @param organizationId the organization id.
     * @param environmentId  the environment id.
     * @return a deployment list.
     */
    /**
     * @param orgId                     Organization Id.
     * @param id                        Gateway id,
     * @param type                      Gateway type.
     * @param name                      Gateway name.
     * @param version                   Gateway version.
     */
//    GatewayInfo registergateway(
//            String orgId,
//            String id,
//            String type,
//            String name,
//            String version
//    );
//    List<GatewayResponse> getAll(String organizationId);
//
//    /**
//     * Retrieve a specific gateway by id related to the specified organization id and environment id (optional).
//     *
//     * @param id the gateway id.
//     * @param organizationId the organization id.
//     * @return a deployment list.
//     *
//     GatewayResponse getById(String id, String organizationId,List gatewayList);
}

