package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.core.exceptions.AuthException;
import com.udacity.jwdnd.course1.cloudstorage.core.exceptions.errors.AuthError;
import com.udacity.jwdnd.course1.cloudstorage.entity.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements AuthenticationProvider {

    private final UserMapper userMapper;
    private final HashService hashService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        throw new AuthException(AuthError.INVALID_CREDENTIALS.toServiceError());
//        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
