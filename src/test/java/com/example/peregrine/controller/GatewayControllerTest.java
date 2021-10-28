package com.example.peregrine.controller;
import  com.example.peregrine.models.GatewayRequest;
import com.example.peregrine.models.GatewayResponse;
import com.example.peregrine.models.GatewayResponseFixture;
import com.example.peregrine.models.GatewayRequestFixture;
import com.example.peregrine.service.GatewayService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Arrays;
import java.util.UUID;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import org.mockito.Mockito;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GatewayController.class)
public class GatewayControllerTest{
    private static String orgId = GatewayResponseFixture.ORGID;
    private static String Id =GatewayResponseFixture.ID;
    private static final ObjectMapper mapper = new ObjectMapper();
    private static String singleGateawyResponse;
    private static String singleGateawyRequest;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    GatewayService gatewayService;

    @BeforeAll
    static void initialize() throws JsonProcessingException {
        singleGateawyResponse = new ObjectMapper().writeValueAsString(GatewayResponseFixture.buildDefault());
        singleGateawyRequest = new ObjectMapper().writeValueAsString(GatewayRequestFixture.buildDefault());
    }


    @AfterEach
    void tearDown() {
        reset(gatewayService);
    }

    @Test
    public void givenAValidOrganization_whenGetAllGateways_thenReturnOk() throws Exception {

        String resourceTemplate = "/organizations/%s/gateways";
        String uri = format(resourceTemplate, orgId);
        //List<GatewayResponse> temp = Arrays.asList(GatewayResponseFixture.buildDefault());
        when(gatewayService.getByOrgId(orgId)).thenReturn(Arrays.asList(GatewayResponseFixture.buildDefault()));

        String response = format("[%s]", singleGateawyResponse);
        System.out.println(response);
        // Act & Assert
//        MvcResult result =
            mockMvc.perform(get(uri)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(response));
//            .andReturn();
//        String actualresponse = result.getResponse().getContentAsString();
//        assertEquals(response,actualresponse);

    }
    @Test
    public void givenAValidOrganization_whenGetGatewayById_thenReturnOk() throws Exception {
        String resourceTemplate = "/organizations/%s/gateways/%s";
        String uri = format(resourceTemplate, orgId,Id);
        when(gatewayService.getByOrgIdAndId(orgId,Id)).thenReturn(Arrays.asList(GatewayResponseFixture.buildDefault()));
//        String response = format("[%s]", singleGateawyResponse);
//        System.out.println(response);
        mockMvc.perform(get(uri)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(singleGateawyResponse));
    }
    @Test
    public void givenAValidOrganizationAndGatewayIdAndBody_WhenPost_thenReturnOk() throws Exception{
        String resourceTemplate = "/organizations/%s/gateways";
        String uri = format(resourceTemplate, orgId,Id);
        mockMvc.perform(post(uri)
                .content(singleGateawyRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    @Test
    public void givenAValidOrganizationAndGatewayIdAndBody_WhenModifyGateway_thenReturnOk() throws Exception{
        String resourceTemplate = "/organizations/%s/gateways/%s";
        String uri = format(resourceTemplate, orgId,Id);

        mockMvc.perform(put(uri)
                .content(singleGateawyRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }
    @Test
    public void givenAValidOrganizationAndGatewayId_WhenDeleteGateway_thenReturnOk() throws Exception{
        String resourceTemplate = "/organizations/%s/gateways/%s";

        String uri = format(resourceTemplate, orgId,Id);
//        Mockito.when(gatewayService.deleteById(Id)).then("")
        mockMvc.perform(delete(uri,orgId,Id))
                .andExpect(status().isNotFound());
    }
}
