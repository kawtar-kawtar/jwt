package TP2;


import io.jsonwebtoken.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;


public class JWTHMacGenToken {
    public static void main(String[] args) {
        String secret="MTIzNjU0Nzg5OTU2NDc4MjEyMzUyMTQ3ODU2MzI1MTQ=";
        SecretKey secretKey=new SecretKeySpec(secret.getBytes(),0,secret.length(), SignatureAlgorithm.HS256.getJcaName());
        Map <String,String> claims=new HashMap<>();
        claims.put("name","kawtar");
        claims.put("email","k.benyahya@gmail.com");
        JwtBuilder jwtBuilder= Jwts.builder().setIssuedAt(new Date()).setId("123321")
                .setExpiration(new Date(System.currentTimeMillis()+100000))
                .setClaims(claims)
                .setSubject("jwt authentification")
                .signWith(secretKey);
        String jwtTocken=jwtBuilder.compact();
        System.out.println(jwtTocken);
    }

}
