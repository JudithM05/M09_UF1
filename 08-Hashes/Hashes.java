import java.security.MessageDigest;
import java.util.HexFormat;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;

public class Hashes {
    int npass = 0;

    //Obtenir SHA-512 amb Salt
    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes());
        byte[] bytes = md.digest(pw.getBytes());
        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes);
    }

    //Obtenir PBKDF2 amb Salt
    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        byte[] saltBytes = salt.getBytes();
        // Ajusta el número d'iteracions per ajustar el temps
        int iterations = 5;
        KeySpec spec = new PBEKeySpec(pw.toCharArray(), saltBytes, iterations, 512);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hashedBytes = factory.generateSecret(spec).getEncoded();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hashedBytes);
    }
    

    //Mètode de força bruta per codificar
    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        this.npass = 0; // Reiniciar el contador
        String charset = "abcdefABCDEF1234567890!";
        char[] password = new char[6];

        // Generar combinacions de longitud 1 a 6
        for (int length = 1; length <= 6; length++) {
            if (generatePasswords(password, length, 0, charset, alg, hash, salt)) {
                return new String(password, 0, length);
            }
        }
        return null; // Contrasenya no trobada
    }

    // Generar combinacions recursivament
    private boolean generatePasswords(char[] password, int length, int pos, String charset, String alg, String hash, String salt) throws Exception {
        if (pos == length) {
            return testPw(password, length, alg, hash, salt);
        }
        for (int i = 0; i < charset.length(); i++) {
            password[pos] = charset.charAt(i);
            npass++; // Incrementar el contador d'intents en cada combinació generada
            if (generatePasswords(password, length, pos + 1, charset, alg, hash, salt)) {
                return true;
            }
        }
        return false;
    }

    // Mètode auxiliar que genera el hash de la contrasenya actual i el compara amb el hash objetiu
    private boolean testPw(char[] password, int length, String alg, String hash, String salt) throws Exception {
        String attempt = new String(password, 0, length); // Crear la contrasenya de longitud `length`
        String attemptHash = (alg.equals("SHA-512")) ? getSHA512AmbSalt(attempt, salt) : getPBKDF2AmbSalt(attempt, salt);
        return attemptHash.equals(hash); // Retornar si el hash coincideix
    }

    //Obté l'intèrval entre un temps i un altre
    public String getInterval(long t1, long t2) {
        long interval = t2 - t1;
        long dies = interval / (1000 * 60 * 60 * 24);
        long hores = (interval / (1000 * 60 * 60)) % 24;
        long minuts = (interval / (1000 * 60)) % 60;
        long segons = (interval / 1000) % 60;
        long millis = interval % 1000;
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", dies, hores, minuts, segons, millis);
    }

    //Mètode main
    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
        h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = {"SHA-512", "PBKDF2"};
        for(int i=0; i< aHashes.length; i++){
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n",aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat != null ? pwTrobat : "No trobada");
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}
