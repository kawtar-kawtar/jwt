package com.example.tp_jwt;

import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class App2JWT {
    private static String createJwtToken(String secret){
        SecretKey secretKey=new SecretKeySpec(secret.getBytes(),0,secret.length(), SignatureAlgorithm.HS256.getJcaName());

        Map<String,String> claims=new HashMap<>();
        claims.put("name","kawtar");
        claims.put("email","k.benyahya@gmail.com");

        Instant now=Instant.now();
        System.out.println(now.toString());
        JwtBuilder jwtBuilder= Jwts.builder().setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(10l, ChronoUnit.MINUTES)))
                .setClaims(claims)
                .setSubject("jwt authentification")
                .signWith(secretKey);
        String jwtTocken=jwtBuilder.compact();
        return jwtTocken;

    }
    private static void parseValideToken(String secret,String token){
        SecretKey secretKey=new SecretKeySpec(secret.getBytes(),0,secret.length(), SignatureAlgorithm.HS256.getJcaName());

        //-------------------Validate and parse the Jwt Tocken : ----------------------
        Jws<Claims> claimsJws= Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(createJwtToken(secret));
        System.out.println("-------------------Header --------------------");
        System.out.println(claimsJws.getHeader());

        System.out.println("-------------------payload --------------------");
        System.out.println(claimsJws.getBody());

        System.out.println("-------------------Signature --------------------");
        System.out.println(claimsJws.getSignature());
    }

    public static void main(String[] args) {
        String secret="MTIzNjU0Nzg5OTU2NDc4MjEyMzUyMTQ3ODU2MzI1MTQ=";
        String jwtToken=createJwtToken(secret);
        parseValideToken(secret,jwtToken);

    }
}
