package iticbcn.xifratge;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class XifradorAES implements Xifrador {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    
    private final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];
    private final String CLAU = "We like Fortnite! We like Fortnite!";

    //xifraAES
    public byte[] xifraAES(String msg, String clau) throws Exception {

    //Obtenir els bytes de l’String
    byte[] bytes = msg.getBytes("UTF-8");
    
    // Genera IvParameterSpec
    IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

    // Genera hash
    byte[] keyHash = MessageDigest.getInstance(ALGORISME_HASH).digest(clau.getBytes("UTF-8"));
        SecretKey secretKey = new SecretKeySpec(keyHash, ALGORISME_XIFRAT);
    
    // Encrypt.
    Cipher cipher = Cipher.getInstance(FORMAT_AES);
    cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
    byte[] encriptat = cipher.doFinal(bytes);

    // Combinar IV i part xifrada.
    byte[] MsgIvIEncriptat = new byte[MIDA_IV + encriptat.length];
        System.arraycopy(iv, 0, MsgIvIEncriptat, 0, MIDA_IV);
        System.arraycopy(encriptat, 0, MsgIvIEncriptat, MIDA_IV, encriptat.length);

    // return iv+msgxifrat
    return MsgIvIEncriptat;
    }

    public String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
    // Extreure l'IV.
    byte[] iv = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
    IvParameterSpec ivSpec = new IvParameterSpec(iv);

    // Extreure la part xifrada.
    byte[] msgXifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);

    // Fer hash de la clau
    byte[] keyHash = MessageDigest.getInstance(ALGORISME_HASH).digest(clau.getBytes("UTF-8"));
        SecretKey secretKey = new SecretKeySpec(keyHash, ALGORISME_XIFRAT);

    // Desxifrar.
    Cipher cipher = Cipher.getInstance(FORMAT_AES);
    cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
    byte[] desencriptat = cipher.doFinal(msgXifrat);

    // return String desxifrat
    return new String(desencriptat);
    }
}
