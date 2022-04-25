package com.project.ticketseller.helpers.testOnly;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {

    String username() default "MockCustomUser";

    String name() default "MockName";

    String firstName() default "MockFirstName";

    String role() default "ROLE_ADMIN";

}
