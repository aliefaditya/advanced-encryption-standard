package kriptografi;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.security.NoSuchAlgorithmException;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
 
public class AES {
 
    private static SecretKeySpec secretKey;
    private static byte[] kunci;
 
    public static void setKey(String myKey) 
    {
        MessageDigest sha = null;
        try {
            //konversi string menjadi bytes UTF-8 untuk string to ascii
            kunci = myKey.getBytes("UTF-8");
            
            //MessageDigest API Kriptografi pada java dengan SHA-256
            sha = MessageDigest.getInstance("SHA-256");
            //SHA merupakan fungsi Hash pada kriptografi yang mengambil input sebanyak 20 bytes dan mengembalikan dalam bil.hexa sekitar 40 digit 
            kunci = sha.digest(kunci);
            //keygeneration 128 bit (32 bytes)
            kunci = Arrays.copyOf(kunci, 32); 
            //generate key
            secretKey = new SecretKeySpec(kunci, "AES");
        } 
        //eksepsi jika tidak ada algoritma (algoritma yg dimaksud disini adalah AES
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        //eksepsi jika tidak ada encoding UTF-8
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public static String encrypt(String dataToEncrypt, String secret) 
    {
        try
        {
            setKey(secret);
            /***
                - digunakan untuk mengembalikan object dari tipe MessageDigest yang mengimplementasikan algo MDigest
                - instansiasi object cipher berupa AES dengan mode operasi ECB dan PKC5 skema padding
            ***/
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(dataToEncrypt.getBytes("UTF-8")));
        } 
        catch (Exception e) 
        {
            System.out.println("Kesalahan saat proses enkripsi: " + e.toString());
        }
        return null;
    }
 
    public static String decrypt(String dataToDecrypt, String secret) 
    {
        try
        {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(dataToDecrypt)));
        } 
        catch (Exception e) 
        {
            System.out.println("Error saat proses dekripsi: " + e.toString());
        }
        return null;
    }
}