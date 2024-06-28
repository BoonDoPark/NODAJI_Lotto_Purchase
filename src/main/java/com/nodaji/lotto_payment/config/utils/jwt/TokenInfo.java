package com.nodaji.lotto_payment.config.utils.jwt;

import io.jsonwebtoken.Claims;

public record TokenInfo(
        String id
) {
    public static TokenInfo fromClaims(Claims claims) {
        String id = claims.get("id", String.class);
        return new TokenInfo(id);
    }
}
