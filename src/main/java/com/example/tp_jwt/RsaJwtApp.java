package com.example.tp_jwt;

import io.jsonwebtoken.*;

import java.security.*;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RsaJwtApp {
    public static void main(String[] args) throws Exception {
        PrivateKey privateKey = CryptoUtils.getPrivateKeyFromKeyStore("kawtar.jks","123321","kawtar");
        String token=createJwtToken(privateKey);
        System.out.println(token);
        System.out.println("*********************************************************");
        Jws<Claims> claimsJws=validateParseToken(token);
        System.out.println("---------------- Header --------------------");
        System.out.println(claimsJws.getHeader());

        System.out.println("---------------- Body --------------------");
        System.out.println(claimsJws.getBody());

        System.out.println("---------------- Signature --------------------");
        System.out.println(claimsJws.getSignature());

    }


    public static String createJwtToken(PrivateKey privateKey){
        String token =null;
        Instant now=Instant.now();
        Map<String,String> claims=new HashMap<>();
        claims.put("Name : ","Kawtar");
        claims.put("Age : ","20 ans");
        claims.put("email : ","k.benyahya02@gmail.com");

        JwtBuilder jwtBuilder=Jwts.builder()
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(2, ChronoUnit.DAYS)))
                .setClaims(claims)
                .signWith(privateKey)
        ;
        token=jwtBuilder.compact();
        return token;
    }



    //************clé publique

    public static Jws<Claims> validateParseToken(String token) throws Exception {
        PublicKey publicKey=CryptoUtils.getPublicKeyFromCertificate("publickey.cert");
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build().parseClaimsJws(token);

        return claimsJws;
    }
}
