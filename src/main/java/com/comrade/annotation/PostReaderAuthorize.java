package com.comrade.annotation;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authorization.method.AuthorizeReturnObject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
//@PostAuthorize("returnObject.auther == authentication?.name or hasRole('ROLE_USER')")
@PostAuthorize("returnObject.auther == authentication?.name")
@AuthorizeReturnObject
public @interface PostReaderAuthorize {
}
