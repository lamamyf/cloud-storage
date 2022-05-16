package com.udacity.jwdnd.course1.cloudstorage.service;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.entity.dto.SimpleCredential;
import com.udacity.jwdnd.course1.cloudstorage.entity.mapper.CredentialMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CredentialService {

    private final CredentialMapper credentialMapper;
    private final EncryptionService encryptionService;

    public void addCredential(String url, String username, String password, int userId){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];

        random.nextBytes(key);

        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);

        credentialMapper.save(new Credential(url, username, encodedKey, encryptedPassword, userId));
    }

    public List<SimpleCredential> getCredentialsByUserId(int userId) {
        return credentialMapper.getCredentialsByUserId(userId).stream().map(c -> new SimpleCredential(c.getUrl(),
                                                                                                      c.getUsername(),
                                                                                                      c.getPassword(),
                                                                                                      encryptionService.decryptValue(c.getPassword(), c.getKey()))).toList();
    }
}
