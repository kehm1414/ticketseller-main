package com.project.ticketseller.helpers.testOnly;

import com.project.ticketseller.entity.AuthenticationProvider;
import com.project.ticketseller.entity.CustomUserDetails;
import com.project.ticketseller.entity.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.sql.Date;
import java.time.LocalDate;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
    @Override
    public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        User user = new User();

    user.setUsername(customUser.username());
    user.setFirstName(customUser.firstName());
    user.setLastName("");
    user.setPassword("mock");
    user.setRole(customUser.role());
    user.setCreatedOn(Date.valueOf(LocalDate.now()));
    user.setId(1l);
    user.setAuthProvider(AuthenticationProvider.LOCAL);
    user.setEnabled(true);

        CustomUserDetails principal =
                new CustomUserDetails(user);
        Authentication auth =
                new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}
