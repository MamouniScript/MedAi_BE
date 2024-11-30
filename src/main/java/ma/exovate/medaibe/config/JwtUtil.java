package ma.exovate.medaibe.config;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    private final String secret = "DmBpzIhc1gDj05ZlarYM77MJhDvI78znZ39emiYO8M2n4xIDu1V3wxuDuNk6J4K6dyL0MfvCvp5NtnD4KLoSV7wwiBc1TAq0IZjA8yRpo0vc3tRcvPS7h5WJYizd57HevpjA2E7qoNOiEhCCcaXoe2JveKEB1PyHMmAb34QfibjDmtVEIzdJ2LsRIK2DSr8ga6gGOir7mUXzG0hwdD5AfOPsAtSMzxpT0R9IUymiUzp7iooRRKXNyl6RsfHA17L7";
    private final long expiration = 1000 * 60 * 60; // 1 hour
    // Generate token with email and role as claims
    public String generateToken(String email, String role) {
        return Jwts.builder()
                .setSubject(email) // Set email as the subject
                .setClaims(Map.of("email", email, "role", role)) // Add email and role as claims
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    // Extract email from token
    public String extractEmail(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .get("email", String.class);
    }

    // Extract role from token
    public String extractRole(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
}