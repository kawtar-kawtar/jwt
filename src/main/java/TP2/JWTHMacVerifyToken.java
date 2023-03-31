package TP2;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class JWTHMacVerifyToken {
    public  static void ParseValidateTocken(String secret,String tocken){
        SecretKey secretKey=new SecretKeySpec(secret.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(tocken);
        System.out.println(claimsJws.toString());
    }
    public static void main(String[] args) {
        String secret="MTIzNjU0Nzg5OTU2NDc4MjEyMzUyMTQ3ODU2MzI1MTQ=";//256 BITS
        String jwtToken="eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoia2F3dGFyIiwiZW1haWwiOiJrLmJlbnlhaHlhQGdtYWlsLmNvbSIsInN1YiI6Imp3dCBhdXRoZW50aWZpY2F0aW9uIn0.LRN2NxpjldmbbuULyzYejOPh71Hhk3LTfCdKFh-7WGs";
        ParseValidateTocken(secret,jwtToken);
    }
}
