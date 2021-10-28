package com.example.peregrine.service;

import com.example.peregrine.models.GatewayResponse;
import java.util.List;

public interface GatewayServiceCustom {
    List<GatewayResponse> getByOrgId(String orgId);
    List<GatewayResponse> getByOrgIdAndId(String orgId, String Id);
    String deleteByOrgIdAndId(String orgId, String Id);
}
