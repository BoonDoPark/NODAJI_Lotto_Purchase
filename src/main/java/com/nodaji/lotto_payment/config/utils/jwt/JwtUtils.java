package com.nodaji.lotto_payment.config.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

    @Value("${token.secret}")
    String tokenSecret;

    public TokenInfo parseToken(String token){
        Claims payload = (Claims) Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(tokenSecret.getBytes()))
                .build()
                .parse(token)
                .getPayload();
        return TokenInfo.fromClaims(payload);
    }
}
