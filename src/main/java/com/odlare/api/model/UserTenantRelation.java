package com.odlare.api.model;

import javax.persistence.*;

@Entity
@Table(name = "tenants")
public class UserTenantRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String tenant;

    public UserTenantRelation() {
    }

    public UserTenantRelation(String username, String tenant) {
        this.username = username;
        this.tenant = tenant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    @Override
    public String toString() {
        return "UserTenantRelation{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", tenant='" + tenant + '\'' +
                '}';
    }
}
