package com.odlare.api.multitenant;

import java.util.concurrent.Callable;

import static com.odlare.api.multitenant.MultiTenantConstants.DEFAULT_TENANT_ID;

public abstract class UnboundTenantTask<T> implements Callable<T> {

    protected String email;

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public T call() throws Exception {
        TenantContext.setCurrentTenant(DEFAULT_TENANT_ID);
        return callInternal();
    }

    protected abstract T callInternal();
}
