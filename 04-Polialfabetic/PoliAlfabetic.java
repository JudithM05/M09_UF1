import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PoliAlfabetic {
    public static final char[] abecedari = "AÀÁÄBCÇDEÈÉËFGHIÍÏJKLMNOÒÓÖPQRSTUÚÜVWXYZÑ".toCharArray();
    public static final char[] alfabetPermutat;

    static {
        alfabetPermutat = permutaAlfabet(abecedari);
    }

    public static final Random random = new Random();

    // Método para permutar el abecedario
    public static char[] permutaAlfabet(char[] alfabet) {
        ArrayList<Character> llista = new ArrayList<>();
        for (char c : alfabet) {
            llista.add(c);
        }
        Collections.shuffle(llista); // Barajar la lista
        char[] alfabetPermutat = new char[llista.size()];
        for (int i = 0; i < llista.size(); i++) {
            alfabetPermutat[i] = llista.get(i);
        }
        return alfabetPermutat;
    }

    // Método para cifrar con el cifrado monoalfabético
    public static String xifraPoliAlfa(String cadena) {
        StringBuilder cadenaFinal = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletra);  // Verifica si es minúscula
            char lletraAMaiuscula = Character.toUpperCase(lletra);  // Convierte a mayúscula para buscar en el abecedario
            int posicio = -1;

            // Buscar la letra en el abecedario original
            for (int j = 0; j < abecedari.length; j++) {
                if (abecedari[j] == lletraAMaiuscula) {
                    posicio = j;
                    break;
                }
            }

            // Si la letra se encuentra en el abecedario, cifrarla
            if (posicio != -1) {
                char lletraXifrada = alfabetPermutat[posicio];  // Letra permutada
                if (esMinuscula) {
                    lletraXifrada = Character.toLowerCase(lletraXifrada);  // Convertir a minúscula si era minúscula
                }
                cadenaFinal.append(lletraXifrada);
            } else {
                cadenaFinal.append(lletra);  // Si no está en el abecedario, no la cambia
            }
        }
        return cadenaFinal.toString();
    }

    // Método para descifrar con el cifrado monoalfabético
    public static String desxifraPoliAlfa(String cadena) {
        StringBuilder cadenaFinal = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletra);  // Verifica si es minúscula
            char lletraAMaiuscula = Character.toUpperCase(lletra);  // Convierte a mayúscula para buscar en el abecedario permutado
            int posicio = -1;

            // Buscar la letra en el alfabeto permutado
            for (int j = 0; j < alfabetPermutat.length; j++) {
                if (alfabetPermutat[j] == lletraAMaiuscula) {
                    posicio = j;
                    break;
                }
            }

            // Si la letra se encuentra en el alfabeto permutado, descifrarla
            if (posicio != -1) {
                char lletraDesxifrada = abecedari[posicio];  // Letra original
                if (esMinuscula) {
                    lletraDesxifrada = Character.toLowerCase(lletraDesxifrada);  // Convertir a minúscula si era minúscula
                }
                cadenaFinal.append(lletraDesxifrada);
            } else {
                cadenaFinal.append(lletra);  // Si no está en el alfabeto permutado, no la cambia
            }
        }
        return cadenaFinal.toString();
    }

    public static void main(String[] args) {
        String msgs[] = {"Test 01 àrbritre, coixí, Perímetre",
        "Test 02 Taüll, DÍA, año",
        "Test 03 Peça, Òrrius, Bòvila"};
        String msgsXifrats[] = new String[msgs.length];
        
        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
        initRandom(clauSecreta);
        msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
        System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }
        
        System.out.println("Desxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
        initRandom(clauSecreta);
        String msg = desxifraPoliAlfa(msgsXifrats[i]);
        System.out.printf("%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }
}
