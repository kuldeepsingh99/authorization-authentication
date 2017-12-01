package com.portal.auto.security;

import java.io.IOException;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

@Component
public class JwtService {

    private SecretKeyProvider secretKeyProvider;

    public JwtService() {
        this(null);
    }

    @Autowired
    public JwtService(SecretKeyProvider secretKeyProvider) {
        this.secretKeyProvider = secretKeyProvider;
    }

    public Jws<Claims> verify(String token) throws IOException, URISyntaxException {
        byte[] secretKey = secretKeyProvider.getKey();
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
    }
}
