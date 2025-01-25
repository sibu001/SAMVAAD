package com.divergent.meet.divergentmeet.security;

import io.jsonwebtoken.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;
    
    @Value("${app.jwtRefreshTokenExpirationInMs}")
    private int jwtRefreshTokenExpirationInMs;

    /**
     * This method is use to generate access Token
     * 
     * @param userPrincipal
     * @return
     */
    public String generateToken(UserPrincipal userPrincipal) {
    	
    	if (StringUtils.isEmpty(userPrincipal.getUsername())) 
            throw new IllegalArgumentException("Cannot create JWT Token without username");

        if (userPrincipal.getAuthorities() == null || userPrincipal.getAuthorities().isEmpty()) 
            throw new IllegalArgumentException("User doesn't have any privileges");
        
        Claims claims = Jwts.claims().setSubject(Long.toString(userPrincipal.getId()));
        claims.put("scopes", userPrincipal.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

        Date expiryDate = new Date(System.currentTimeMillis() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
    /**
     * This method is used to generate refresh Token
     * 
     * @param userPrincipal
     * @return
     */
    public String generateRefreshToken(UserPrincipal userPrincipal) {

        Claims claims = Jwts.claims().setSubject(Long.toString(userPrincipal.getId()));
        claims.put("scopes", Arrays.asList("ROLE_REFRESH_TOKEN"));
        
        Date expiryDate = new Date(System.currentTimeMillis() + jwtRefreshTokenExpirationInMs);
        
        return Jwts.builder()
          .setClaims(claims)
          .setIssuer("leadmanagement")
          .setId(UUID.randomUUID().toString())
          .setIssuedAt(new Date())
          .setExpiration(expiryDate)
          .signWith(SignatureAlgorithm.HS512, jwtSecret)
        .compact();
    }
    
    /**
     * Method returns user id from claims
     * 
     * @param token
     * @return 
     * 		- User Id
     */
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }
    /**
     * Method returns the subject(user email) from claims
     * 
     * @param token
     * @return
     */
    public String getUserFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    /**
     * Method to validate JWT token
     * 
     * @param authToken
     * @return
     */
    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}