package TP2;

import com.example.tp_jwt.CryptoUtils;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;

import java.security.PrivateKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTRSAGenToken {
    public static String createJwtToken(PrivateKey privateKey){
        String token =null;
        Instant now=Instant.now();
        Map<String,String> claims=new HashMap<>();
        claims.put("Name : ","Kawtar");
        claims.put("Age : ","20 ans");
        claims.put("email : ","k.benyahya02@gmail.com");

        JwtBuilder jwtBuilder= Jwts.builder().setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(2, ChronoUnit.DAYS)))
                .setClaims(claims)
                .signWith(privateKey)
                ;
        token=jwtBuilder.compact();
        return token;
    }
    public static void main(String[] args) throws Exception {

        PrivateKey privateKey = CryptoUtils.getPrivateKeyFromKeyStore("kawtar.jks","123321","kawtar");
        String token=createJwtToken(privateKey);
        System.out.println(token);
    }
}
