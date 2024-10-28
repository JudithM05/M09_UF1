package iticbcn.xifratge;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class XifradorAES implements Xifrador {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";
    
    private final int MIDA_IV = 16;
    private final String CLAU = "We like Fortnite! We like Fortnite!";

    //xifraAES
    public byte[] xifraAES(String msg, String clau) throws Exception {

        //Obtenir els bytes de l’String
        byte[] bytes = msg.getBytes("UTF-8");

        // Genera IvParameterSpec
        byte[] iv = new byte[MIDA_IV];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);  // Generación de IV aleatorio
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

        // Genera hash
        byte[] keyHash = MessageDigest.getInstance(ALGORISME_HASH).digest(clau.getBytes("UTF-8"));
        SecretKeySpec secretKey = new SecretKeySpec(keyHash, ALGORISME_XIFRAT);

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
        SecretKeySpec secretKey = new SecretKeySpec(keyHash, ALGORISME_XIFRAT);

        // Desxifrar.
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);
        byte[] desencriptat = cipher.doFinal(msgXifrat);

        // return String desxifrat
        return new String(desencriptat, "UTF-8");
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            byte[] msgXifrat = xifraAES(msg, clau);
            // Codifica el missatge xifrat en Base64 per emmagatzemar-lo en TextXifrat
            String msgXifratBase64 = Base64.getEncoder().encodeToString(msgXifrat);
            return new TextXifrat(msgXifratBase64.getBytes());
        } catch (Exception e) {
            throw new ClauNoSuportada("Error en el xifrat AES: " + e.getMessage());
        }
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            // Descodifica el text de TextXifrat des de Base64
            byte[] msgXifratBytes = Base64.getDecoder().decode(xifrat.getBytes());
            return desxifraAES(msgXifratBytes, clau);
        } catch (Exception e) {
            throw new ClauNoSuportada("Error en el desxifrat AES: " + e.getMessage());
        }
    }
}
