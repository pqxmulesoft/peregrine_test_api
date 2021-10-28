package com.example.peregrine.service;
import com.example.peregrine.models.GatewayResponse;
import com.example.peregrine.service.GatewayService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@Repository
public class GatewayRepositoryImpl implements GatewayServiceCustom {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<GatewayResponse> getByOrgId(String orgId) {
        String hql = "SELECT G FROM GatewayResponse G WHERE G.orgId = :orgId";
        Query query = entityManager.createQuery(hql,GatewayResponse.class);
        query.setParameter("orgId", orgId);
        return query.getResultList();

//        List<GatewayResponse> newList = new ArrayList<>();
//        for (GatewayResponse response: gatewayResponseList) {
//            if(OrgId.equals(response.getOrgId())) newList.add(response);
//        }
//        return newList;
    }

    @Override
    public List<GatewayResponse> getByOrgIdAndId(String orgId, String Id) {
        String hql = "SELECT G FROM GatewayResponse G WHERE G.orgId = :orgId and G.id = :Id";
        Query query = entityManager.createQuery(hql,GatewayResponse.class);
        query.setParameter("orgId", orgId);
        query.setParameter("Id", Id);
        return query.getResultList();
    }

    @Override
    public String deleteByOrgIdAndId(String orgId, String Id){
        String hql = "DELETE FROM GatewayResponse G WHERE G.orgId = :orgId and G.id = :Id";
        Query query = entityManager.createQuery(hql,GatewayResponse.class);
        query.setParameter("orgId", orgId);
        query.setParameter("Id", Id);
        String res = format("Successfully delete gateway %s",Id);
        return res;
    }
}
