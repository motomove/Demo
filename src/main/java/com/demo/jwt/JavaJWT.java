package com.demo.jwt;

import com.auth0.jwk.InvalidPublicKeyException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.security.util.DerInputStream;
import sun.security.util.DerValue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;
import java.util.Date;

public class JavaJWT {

//    static KeyPair keyPair = RSAUtils.generateRSAKeyPair(512);
//    static RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();//Get the key instance
//    static RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();//Get the key instance


    private static String getPrivateKeyString() throws IOException {
        //    生成方法：安装openssl,执行     openssl genrsa -out private.pem 2048
        return IOUtils.toString(new FileInputStream("/home/whaty/tmp/a/private.pem"));
    }

    private static String getPEMPublicKeyString() throws IOException {
        //    导出公钥方法：生成私钥(private.pem)后,执行    openssl rsa -in private.pem -outform PEM -pubout -out public.pem
        return IOUtils.toString(new FileInputStream("/home/whaty/tmp/a/public.pem"));
    }

    /**
     * 获取PublicKey对象
     * @param publicKeyBase64
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    private static PublicKey getPublicKey(String publicKeyBase64) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String pem = publicKeyBase64
                .replaceAll("\\-*BEGIN.*KEY\\-*", "")
                .replaceAll("\\-*END.*KEY\\-*", "");
        java.security.Security.addProvider(
                new BouncyCastleProvider()
        );
        X509EncodedKeySpec pubKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(pem));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        PublicKey publicKey = keyFactory.generatePublic(pubKeySpec);
        System.out.println(publicKey);
        return publicKey;
    }

    /**
     * 获取PrivateKey对象
     * @param privateKeyBase64
     * @return
     */
    private static PrivateKey getPrivateKey(String privateKeyBase64) {
        String privKeyPEM = privateKeyBase64
                .replaceAll("\\-*BEGIN.*KEY\\-*", "")
                .replaceAll("\\-*END.*KEY\\-*", "");

        // Base64 decode the data
        byte[] encoded = Base64.decodeBase64(privKeyPEM);

        try {
            DerInputStream derReader = new DerInputStream(encoded);
            DerValue[] seq = derReader.getSequence(0);

            if (seq.length < 9) {
                throw new GeneralSecurityException("Could not read private key");
            }

            // skip version seq[0];
            BigInteger modulus = seq[1].getBigInteger();
            BigInteger publicExp = seq[2].getBigInteger();
            BigInteger privateExp = seq[3].getBigInteger();
            BigInteger primeP = seq[4].getBigInteger();
            BigInteger primeQ = seq[5].getBigInteger();
            BigInteger expP = seq[6].getBigInteger();
            BigInteger expQ = seq[7].getBigInteger();
            BigInteger crtCoeff = seq[8].getBigInteger();

            RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(modulus, publicExp, privateExp,
                    primeP, primeQ, expP, expQ, crtCoeff);

            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePrivate(keySpec);
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws Exception {

        String publicKeyBase64 = getPEMPublicKeyString();
        String pem = publicKeyBase64
                .replaceAll("\\-*BEGIN.*KEY\\-*", "")
                .replaceAll("\\-*END.*KEY\\-*", "");
        System.out.println("pub = " + pem);

        String privateKeyBase64 = getPrivateKeyString();

        String privKeyPEM = privateKeyBase64
                .replaceAll("\\-*BEGIN.*KEY\\-*", "")
                .replaceAll("\\-*END.*KEY\\-*", "");

        System.out.println("privKeyPEM = " + privKeyPEM);

        RSAPublicKey publicKey = (RSAPublicKey) getPublicKey(pem);
        System.out.println("publicKey = " + publicKey);
        RSAPrivateKey privateKey = (RSAPrivateKey) getPrivateKey(privKeyPEM);

        System.out.println("privateKey = " + privateKey);

        //HS256算法:
//        HS256();


        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJhdXRoMCJ9.q1tM334CMVV5QvTO8d7fqMA2txNNU4D3J3ySDh3dQy3cRUIltyvETHDQTdCm6DUCAfDXP-dbBl2JdOcLJMy4Ip7SEgYYZG1V22zFcRmnpqp523Z8u_E9HVvtUo3P0ErVjkAuAbXI3GTEeCnRriGcB2F6p1Ac9Ynuc0GL6jROlyJlMytgATGpt7gIjoPshH4ac07Sb10NA_1kcOlE7uCX21VhAW8PP0zj7mdjXwFsRhKciKpo164l4MxzGlIEDHYfYlt62OKaMjViSY79p7gmi8mqg21QErkY3ohupJDdkEnOM9ZgLY4HU0gnEVgnIP9FwwwkCO76U4hfQMO1TlrRXA";
//        HS256Verifier(token);

//        try {
//            RS256(publicKey, privateKey);
//        } catch (InvalidPublicKeyException e) {
//            e.printStackTrace();
//        } catch (InvalidKeySpecException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJhdXRoMCJ9.X1nAN_eGPGpwtC3fbyvHnWXSITAu-j5xItK1vhKFcMHwnApBT5uMUSlk_eJUrlOr2BhotHZadAbqgIPx3hScAkZUWAfYJE3CE4fuZtNk4sruTpZ8Z30f0FB0p8dRS9Wlt555BhtMEhof2jzj2gw4f72OILDc5UBCBvA6hB-2aO8";
        verifier(publicKey, privateKey, token);

    }

    /**
     * RS256算法
     * public Jwk(
     * String id,
     * String type,
     * String algorithm,
     * String usage,
     * List<String> operations,
     * String certificateUrl,
     * List<String> certificateChain,
     * String certificateThumbprint,
     * Map<String, Object> additionalAttributes) {
     */
    public static void RS256(final RSAPublicKey publicKey, final RSAPrivateKey privateKey) throws InvalidPublicKeyException, InvalidKeySpecException, NoSuchAlgorithmException, IOException {
        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);

        try {

            Calendar now = Calendar.getInstance();
            Date iatDate = now.getTime();
            System.out.println("iatDate = " + iatDate);

            now.add(Calendar.MINUTE, 10);
            Date expireDate = now.getTime();
            System.out.println("expireDate = " + expireDate);

            String token = JWT.create()
                    .withIssuer("auth0")
//                    .withIssuedAt(iatDate)
//                    .withJWTId("whaty")
//                    .withKeyId("vtevj")
//                    .withExpiresAt(expireDate)
//                    .withClaim("iat", System.currentTimeMillis())
                    .sign(algorithm);
            System.out.println("token = " + token);


//            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
//            DecodedJWT jwt = verifier.verify(token);

//            System.out.println("jwt = " + jwt.getIssuer());

        } catch (JWTCreationException exception) {
            exception.printStackTrace();
            //Invalid Signing configuration / Couldn't convert Claims.
        }
    }

    private static void verifier(final RSAPublicKey publicKey, final RSAPrivateKey privateKey, String token) {
        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);

        DecodedJWT decodedJWT = JWT.decode(token);
        String algorithmStr = decodedJWT.getAlgorithm();
        System.out.println("decodedJWT = " + algorithmStr);

        try {

            JWTVerifier verifier = JWT.require(algorithm).build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            System.out.println(" = " + jwt.getIssuer());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("请求凭证失效");
        }

    }

    private static void HS256Verifier(String token){
        try {

            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            String user = jwt.getIssuer();
            System.out.println("user = " + user);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * HS256算法
     */
    public static void HS256() {
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            String token = JWT.create()
                    .withIssuer("auth0")
                    .sign(algorithm);
            System.out.println("token = " + token);
        } catch (UnsupportedEncodingException exception) {
            //UTF-8 encoding not supported
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }
    }


}
