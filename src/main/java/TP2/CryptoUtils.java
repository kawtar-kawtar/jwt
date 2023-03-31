package TP2;

import java.io.FileInputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

public class CryptoUtils {

    public static PrivateKey getPrivateKeyFromKeyStore(String fileName, String password, String alias)throws Exception{
        FileInputStream fileInputStream1=new FileInputStream(fileName);
        KeyStore keyStore=KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(fileInputStream1,password.toCharArray());
        PrivateKey privateKey =(PrivateKey) keyStore.getKey(alias, password.toCharArray());
        return privateKey;

    }


    public static PublicKey getPublicKeyFromCertificate(String fileName)throws Exception{
        FileInputStream fileInputStream=new FileInputStream(fileName);
        CertificateFactory certificateFactory=CertificateFactory.getInstance("X.509");
        Certificate certificate = certificateFactory.generateCertificate(fileInputStream);
        //  System.out.println(certificate.toString());
        PublicKey publicKey= certificate.getPublicKey();
        return publicKey;
    }
}
