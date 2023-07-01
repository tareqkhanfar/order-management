package com.khanfar.project2.Security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "TAREQ KHANFAR";

    public String extractUsername(String token) {
        return extractClaim(token , Claims::getSubject);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJwt(token)
                .getBody();
    }
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>() , userDetails) ;
    }
    public String generateToken (Map<String , Object> map , UserDetails userDetails ) {
         return Jwts.builder().setClaims(map)
                 .setSubject(userDetails.getUsername()  )
                 .setIssuedAt(new Date(System.currentTimeMillis()))
                 .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 24 ))
                 .signWith(getSignInKey() , SignatureAlgorithm.HS256)
                 .compact();

    }

    public boolean isTokenValid(String token , UserDetails userDetails ) {
        final String username = extractUsername(token) ;
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token) ;
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());

    }

    private Date extractExpiration(String token) {
        return extractClaim(token , Claims::getExpiration);
    }

    public <T> T extractClaim(String token , Function<Claims , T > claimsTFunction) {
              final Claims claims = extractAllClaims(token) ;
              return claimsTFunction.apply(claims) ;
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
