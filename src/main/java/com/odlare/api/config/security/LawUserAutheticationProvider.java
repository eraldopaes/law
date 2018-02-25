package com.odlare.api.config.security;

import com.odlare.api.model.SystemUser;
import com.odlare.api.model.User;
import com.odlare.api.model.UserTenantRelation;
import com.odlare.api.multitenant.TenantContext;
import com.odlare.api.multitenant.TenantNameFetcher;
import com.odlare.api.repository.UserRepository;
import org.assertj.core.util.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class LawUserAutheticationProvider implements AuthenticationProvider {

    private final TenantNameFetcher tenantResolver;
    private final UserRepository userRepository;

    @Autowired
    public LawUserAutheticationProvider(TenantNameFetcher tenantResolver,
                                        UserRepository userRepository) {
        this.tenantResolver = tenantResolver;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        try {
            tenantResolver.setUsername(authentication.getName());
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

        Optional<User> user = userRepository.findByUsername(authentication.getName());

        if (user.isPresent()) {
            SystemUser systemUser = new SystemUser(user.get(), getDefaultAuthorities());
            return new UsernamePasswordAuthenticationToken(systemUser, authentication.getCredentials().toString(), getDefaultAuthorities());
        }

        throw new UsernameNotFoundException("Usuário inválido!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return Authentication.class.isAssignableFrom(authentication);
    }

    private List<GrantedAuthority> getDefaultAuthorities() {
        Set<String> privilegesFromRest = Sets.newHashSet();
        privilegesFromRest.add("ROLE");
        String[] authoritiesAsArray = privilegesFromRest.toArray(new String[privilegesFromRest.size()]);
        return AuthorityUtils.createAuthorityList(authoritiesAsArray);
    }
}
