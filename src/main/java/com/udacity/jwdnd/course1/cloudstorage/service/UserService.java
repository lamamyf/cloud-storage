package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.core.exceptions.AuthException;
import com.udacity.jwdnd.course1.cloudstorage.core.exceptions.BusinessException;
import com.udacity.jwdnd.course1.cloudstorage.core.exceptions.errors.AuthError;
import com.udacity.jwdnd.course1.cloudstorage.core.exceptions.errors.RegistrationError;
import com.udacity.jwdnd.course1.cloudstorage.entity.User;
import com.udacity.jwdnd.course1.cloudstorage.entity.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class UserService implements AuthenticationProvider {

    private final UserMapper userMapper;
    private final HashService hashService;

    public void register(String username, String password, String firstName, String lastName){
        boolean usernameAlreadyUsed = userMapper.existsByUsername(username);
        if(usernameAlreadyUsed){
            throw new BusinessException(RegistrationError.USERNAME_ALREADY_USED.toServiceError(username));
        }

        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);

        String hashedPassword = hashService.getHashedValue(password, encodedSalt);

        userMapper.addUser(new User(username, encodedSalt, hashedPassword, firstName, lastName));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userMapper.getUser(username)
                               .orElseThrow(() -> new AuthException(AuthError.INVALID_CREDENTIALS.toServiceError()));

        String hashedPassword = hashService.getHashedValue(password, user.getSalt());
        System.out.println(user.getSalt());
        System.out.println(hashedPassword);
        if(!hashedPassword.equals(user.getPassword()))
            throw new AuthException(AuthError.INVALID_CREDENTIALS.toServiceError());

        return new UsernamePasswordAuthenticationToken(user.getId(), hashedPassword, new ArrayList<>());
    }

    public int getCurrentUserId(){
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
