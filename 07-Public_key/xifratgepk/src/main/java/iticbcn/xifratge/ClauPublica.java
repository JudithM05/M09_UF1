package iticbcn.xifratge;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

public class ClauPublica {

    // Genera el parell de claus RSA
    public KeyPair generaParellClausRSA() throws Exception {
        try {
            // Obté la instància d'RSA
            KeyPairGenerator claus = KeyPairGenerator.getInstance("RSA");
            // Es posa un tamany determinat
            claus.initialize(2048);
            // Genera el parell de claus
            return claus.generateKeyPair();
        } catch (Exception e) {
            System.out.println("Error generant el parell de claus: " + e.getMessage());
            return null; // Retorna null en cas d'error
        }
    }

    // Xifra el missatge amb RSA
    public byte[] xifraRSA(String msg, PublicKey clauPublica) throws Exception {
        try {
            // Obté la instància del Cipher amb RSA
            Cipher cipher = Cipher.getInstance("RSA");
            // Configura el Cipher en mode ENCRYPT_MODE amb la clau pública
            cipher.init(Cipher.ENCRYPT_MODE, clauPublica);
            // Converteix el missatge a bytes UTF-8
            byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
            // Retorna el missatge xifrat
            return cipher.doFinal(bytes);
        } catch (Exception e) {
            System.out.println("Error en el xifrat: " + e.getMessage());
            return null;
        }
    }

    // Desxifra el missatge amb RSA
    public String desxifraRSA(byte[] msgXifrat, PrivateKey clauPrivada) {
        try {
            // Obté la instància del Cipher amb RSA
            Cipher cipher = Cipher.getInstance("RSA");
            // Configura el Cipher en mode DECRYPT_MODE amb la clau privada
            cipher.init(Cipher.DECRYPT_MODE, clauPrivada);
            // Desxifra el missatge xifrat
            byte[] decryptedBytes = cipher.doFinal(msgXifrat);
            // Converteix els bytes desxifrats a String UTF-8 i el retorna
            return new String(decryptedBytes, StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("Error en el desxifrat: " + e.getMessage());
            return null;
        }
    }
}
