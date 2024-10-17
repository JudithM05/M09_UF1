import javax.crypto.spec.IvParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    
    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "We like Fortnite! We like Fortnite!";

    //xifraAES
    public static byte[] xifraAES(String msg, String clau) throws Exception {

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

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
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

    //Main
    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet", 
        "Hola Andrés cómo está tu cuñado", 
        "Àgora ïlla Ôtto"};
        
        for (int i = 0; i < msgs.length; i++) {
            String msg = msgs[i]; 
            
            byte[] bXifrats = null;
            String desxifrat = "";
            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " 
                + e.getLocalizedMessage());
            }
            System.out.println("--------------------");
            System.out.println("Msg: " + msg);
            System.out.println("Enc: " + new String(bXifrats));
            System.out.println("DEC: " + desxifrat);
        }
    }    
}
