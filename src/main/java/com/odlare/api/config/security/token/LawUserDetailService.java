package com.odlare.api.config.security.token;

import com.odlare.api.model.SystemUser;
import com.odlare.api.model.User;
import com.odlare.api.model.UserTenantRelation;
import com.odlare.api.multitenant.TenantContext;
import com.odlare.api.multitenant.TenantNameFetcher;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class LawUserDetailService implements UserDetailsService {

    private final TenantNameFetcher tenantResolver;

    @Autowired
    public LawUserDetailService(TenantNameFetcher tenantResolver) {
        this.tenantResolver = tenantResolver;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        try {
            tenantResolver.setEmail(email);
            ExecutorService es = Executors.newSingleThreadExecutor();
            Future<UserTenantRelation> utrFuture = es.submit(tenantResolver);
            UserTenantRelation utr = utrFuture.get();
            es.shutdown();
            if (utr != null) {
                TenantContext.setCurrentTenant(utr.getTenant());
            } else {
                throw new UsernameNotFoundException("Usuário inválido!");
            }
        } catch (Exception e) {
            throw new UsernameNotFoundException("Ocorreu um erro!");
        }

        User user = new User();
        user.setEmail(email);
        user.setPassword("...");

        SystemUser systemUser = new SystemUser(user, getDefaultAuthorities());
        systemUser.setTenant(TenantContext.getCurrentTenant());

        return systemUser;
    }

    private List<GrantedAuthority> getDefaultAuthorities() {
        Set<String> privilegesFromRest = Sets.newHashSet();
        privilegesFromRest.add("ROLE");
        String[] authoritiesAsArray = privilegesFromRest.toArray(new String[privilegesFromRest.size()]);
        return AuthorityUtils.createAuthorityList(authoritiesAsArray);
    }
}
