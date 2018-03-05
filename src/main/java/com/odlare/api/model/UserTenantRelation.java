package com.odlare.api.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tenants")
public class UserTenantRelation implements Serializable {

    private static final long serialVersionUID = -598430872124617697L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String tenant;

    public UserTenantRelation() {
    }

    public UserTenantRelation(String email, String tenant) {
        this.email = email;
        this.tenant = tenant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
                ", email='" + email + '\'' +
                ", tenant='" + tenant + '\'' +
                '}';
    }
}
