package com.example.peregrine.controller;

import com.example.peregrine.models.GatewayResponse;
import com.example.peregrine.service.GatewayService;
import com.example.peregrine.models.GatewayRequest;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import static org.springframework.util.Assert.notNull;


@RestController
@RequestMapping(
        path = "/organizations/{orgId}/gateways",
        produces = MediaType.APPLICATION_JSON_VALUE)
public class GatewayController {
    /**
     * Logger to log stuffs.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GatewayController.class);
    /**
     * Service to operate with gateways.
     */
    private final GatewayService gatewayService;
    /**
     * Creates a new {@link GatewayController}.
     *
     * @param gatewayService The service to manipulate the agents.
     */
    @Autowired
    public GatewayController(GatewayService gatewayService){
        this.gatewayService = gatewayService;
    }

    /**
     * add a new gateway.
     *
     * @param orgId               The organization id of the registering agent.
     * @requestBody  gatewayRequest The {@link GatewayRequest}.
     * @return gateway Response.
     */
    @PostMapping("")
    public ResponseEntity<GatewayResponse> addgateway(
            @PathVariable("orgId") @Parameter(description = "Organization Id where the gateway will be registered") String orgId,
            @RequestBody GatewayRequest gatewayRequest) {
        notNull(gatewayRequest, "Registration request is null");
        String gatewayname = gatewayRequest.getName();
        String gatewaytype = gatewayRequest.getType();
        String gatewayVersion = gatewayRequest.getVersion();
        String Gatewayid = RandomStringUtils.randomAlphanumeric(3);
//        String Gatewayid = UUID.randomUUID().toString();
        GatewayResponse response = new GatewayResponse(
                orgId,
                Gatewayid,
                gatewaytype,
                gatewayname,
                gatewayVersion
        );
        LOGGER.info("Registered Gateway: {}", response);
        gatewayService.save(response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    //@RequestMapping(path = "/{Id}",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    /**
     * modify an existing gateway.
     *
     * @param orgId               The organization id of the registering agent.
     * @param Id                  Gateway Id
     * @requestBody NewgatewayRequest The {@link GatewayRequest}.
     * @return gateway Request.
     */
    @PutMapping("/{Id}")
    public ResponseEntity<GatewayRequest> replacegateway(
            @Parameter(description = "The id of the organization.", required = true) @PathVariable String orgId,
            @Parameter(description = "The id of the gateway.", required = true) @PathVariable String Id,
            @RequestBody GatewayRequest NewgatewayRequest) {
//            List<GatewayResponse> gatewayList = gatewayService.findAll();
        LOGGER.info("Search targets by organizationId: '{}'. GatewayId: '{}' ",
                orgId, Id);
            String gatewayname = NewgatewayRequest.getName();
            String gatewaytype = NewgatewayRequest.getType();
            String gatewayVersion = NewgatewayRequest.getVersion();

            gatewayService.findById(Id).map(
                    gatewayResponse -> {
                        gatewayResponse.setType(gatewaytype);
                        gatewayResponse.setName(gatewayname);
                        gatewayResponse.setVersion(gatewayVersion);
                        return gatewayService.save(gatewayResponse);
                    })
                    .orElseGet(() -> gatewayService.save(new GatewayResponse(orgId,Id,gatewaytype,gatewayname,gatewayVersion)));
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(NewgatewayRequest);

    }
    /**
     * get all gateways in a specific organization.
     * @param orgId               The organization id of the registering agent.
     * @return gateway Response.
     */
    @GetMapping("")
    public ResponseEntity<List<GatewayResponse>> getall(@Parameter(description = "The id of the organization.", required = true) @PathVariable String orgId
    ) {
//        List<GatewayResponse> newList = new ArrayList<>();
//        for (GatewayResponse response: gatewaylist) {
//            if(orgId.equals(response.getOrgId())) newList.add(response);
//        }
        List<GatewayResponse> gatewaylist = gatewayService.getByOrgId(orgId);
        LOGGER.info("Search targets by organizationId: '{}'. Returning {} targets",
                orgId, gatewaylist.size());

        return ResponseEntity.ok().body(gatewaylist);
        }
    /**
     * get all gateways in a specific organization.
     * @param Id               Gateway Id
     * @return gateway Response.
     */
    @GetMapping("/{Id}")
    public ResponseEntity<GatewayResponse> getone(@Parameter(description = "The id of the organization.", required = true) @PathVariable String orgId,
                                                                @Parameter(description = "The id of the gateway.", required = true) @PathVariable String Id) {


        LOGGER.info("Search gateways by organizationId: '{}'.gateway Id: {}",
                orgId, Id );

//        List<GatewayResponse> gatewayList = gatewayService.getByOrgId(orgId);
//        for (GatewayResponse response: gatewayList){
//            if(Id.equals(response.getId()))
//                return ResponseEntity.ok().body(response);
//        }
        GatewayResponse response = gatewayService.getByOrgIdAndId(orgId,Id).get(0);
        if(response != null) {
            gatewayService.delete(response);
            return ResponseEntity.ok().body(response);
        }
        else return ResponseEntity.notFound().build();
//        throw new ResponseStatusException(
//                HttpStatus.NOT_FOUND, "Id not found"
//        );
    }
    /**
     * add an existing gateway.
     *
     * @param orgId               The organization id of the registering agent.
     * @param Id                    Gateway Id
     * @return String.
     */
    @DeleteMapping("/{Id}")
    public ResponseEntity<String> DeleteGateway(@Parameter(description = "The id of the organization.", required = true) @PathVariable String orgId,
                                                          @Parameter(description = "The id of the gateway.", required = true) @PathVariable String Id) {
        LOGGER.info("Delete Gateways by organizationId: '{}'.gateway Id: {}",
                orgId, Id );
        List<GatewayResponse> responseList =gatewayService.getByOrgIdAndId(orgId,Id);
        if(responseList.size() > 0) {
            GatewayResponse response = responseList.get(0);
            gatewayService.delete(response);
            return ResponseEntity.status(200).body("Successfully delete!");
        }
        else return ResponseEntity.status(404).body("Id not found!");
    }
}
