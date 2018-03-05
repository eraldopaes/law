package com.odlare.api.model;

import com.odlare.api.multitenant.TenantContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class SystemUser extends User {

    private static final long serialVersionUID = 8232147438276172895L;
    private com.odlare.api.model.User user;
    private String tenant;

    public SystemUser(com.odlare.api.model.User user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.tenant = TenantContext.getCurrentTenant();
    }

    public com.odlare.api.model.User getUser() {
        return user;
    }

    public void setUser(com.odlare.api.model.User user) {
        this.user = user;
    }

    public String getTenant() {
        return tenant;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }
}
