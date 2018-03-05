package com.odlare.api.repository;

import com.odlare.api.model.UserTenantRelation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTenantRelationRepository extends JpaRepository<UserTenantRelation, Long> {
    UserTenantRelation findByEmail(String email);
}
