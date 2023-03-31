package TP2;

import com.example.tp_jwt.CryptoUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JWTHRSAVerifyToken {
    public static Jws<Claims> validateParseToken(String token) throws Exception {
        PublicKey publicKey= CryptoUtils.getPublicKeyFromCertificate("publickey.cert");
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(publicKey)
                .build().parseClaimsJws(token);

        return claimsJws;
    }
    public static void main(String[] args) throws Exception {
        PrivateKey privateKey = CryptoUtils.getPrivateKeyFromKeyStore("kawtar.jks","123321","kawtar");
        String token="eyJhbGciOiJSUzI1NiJ9.eyJOYW1lIDogIjoiS2F3dGFyIiwiQWdlIDogIjoiMjAgYW5zIiwiZW1haWwgOiAiOiJrLmJlbnlhaHlhMDJAZ21haWwuY29tIn0.DIrP3Oio_nr-ynLYkA5fz9vcgjzTIakMgiFJtRkBzUtM8XuhKzRDSXZBWXU2PCOJP0KPBHCoRtGYcHIEe-cKbqJ7QKqflBKgLS6_pnP1t71NwxJ6Fx9-aJ4jQbBYrc7KmXU0x6eLHlZJx4BMM8iHpzOIWQHM_8pUzH3jyd9ooEbpYlP4vR9yhZCbGr29SxaPnvL0-aZYLWi3rsIh_EJzNgeKvzYxJKpqTZWxTrS_Q8llQL28hTKpVkXCmb3_u7CRBSH5hr5EdTgRzGR_AZ0S9uvxse3-235GJi3g43IUZNKRLBDM5Oj7bhHoYZgiYEgDWClaQ7hLPl6frg64BymFQQ";
        Jws<Claims> claimsJws=validateParseToken(token);
        System.out.println("---------------- Header --------------------");
        System.out.println(claimsJws.getHeader());
        System.out.println("---------------- Body --------------------");
        System.out.println(claimsJws.getBody());
        System.out.println("---------------- Signature --------------------");
        System.out.println(claimsJws.getSignature());
    }
}
