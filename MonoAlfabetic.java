import java.util.ArrayList;
import java.util.Collections;

public class MonoAlfabetic {
    public static final char[] abecedari = "AÀÁÄBCÇDEÈÉËFGHIÍÏJKLMNOÒÓÖPQRSTUÚÜVWXYZÑ".toCharArray();
    public static final char[] alfabetPermutat;

    static {
        alfabetPermutat = permutaAlfabet(abecedari);
    }

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
    public static String xifraMonoAlfa(String cadena) {
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
    public static String desxifraMonoAlfa(String cadena) {
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
        String frase1 = "M'agrada la Gwenpool i el Fortnite";
        String frase2 = "El més bonic del món és el cànon de la música que sona com un riu.";

        // Cifrado
        String frase1Xifrada = xifraMonoAlfa(frase1);
        String frase2Xifrada = xifraMonoAlfa(frase2);

        // Descifrado
        String frase1Desxifrada = desxifraMonoAlfa(frase1Xifrada);
        String frase2Desxifrada = desxifraMonoAlfa(frase2Xifrada);

        // Resultados
        System.out.println("La primera frase original és: " + frase1);
        System.out.println("La primera frase xifrada és: " + frase1Xifrada);
        System.out.println("La primera frase desxifrada és: " + frase1Desxifrada);
        System.out.println();
        System.out.println("La segona frase original és: " + frase2);
        System.out.println("La segona frase xifrada és: " + frase2Xifrada);
        System.out.println("La segona frase desxifrada és: " + frase2Desxifrada);
    }
}
