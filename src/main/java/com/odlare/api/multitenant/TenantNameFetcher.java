package com.odlare.api.multitenant;

import com.odlare.api.model.UserTenantRelation;
import com.odlare.api.repository.UserTenantRelationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TenantNameFetcher extends UnboundTenantTask<UserTenantRelation> {

    private final UserTenantRelationRepository userTenantRelationRepository;

    @Autowired
    public TenantNameFetcher(UserTenantRelationRepository userTenantRelationRepository) {
        this.userTenantRelationRepository = userTenantRelationRepository;
    }

    @Override
    protected UserTenantRelation callInternal() {
        return userTenantRelationRepository.findByEmail(this.email);
    }
}
