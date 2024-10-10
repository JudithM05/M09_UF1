import java.util.ArrayList;
import java.util.Collections;

public class MonoAlfabetic {
    public static final char[] abecedari = "AÀÁÄBCÇDEÈÉËFGHIÍÏJKLMNOÒÓÖPQRSTUÚÜVWXYZÑ".toCharArray();
    public static final char[] alfabetPermutat;

    static {
        alfabetPermutat = permutaAlfabet(abecedari);
    }

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

    public static String xifraMonoAlfa(String cadena) {
        StringBuilder cadenaFinal = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletra);  
            char lletraAMaiuscula = Character.toUpperCase(lletra);  
            int posicio = -1;

            for (int j = 0; j < abecedari.length; j++) {
                if (abecedari[j] == lletraAMaiuscula) {
                    posicio = j;
                    break;
                }
            }

            if (posicio != -1) {
                char lletraXifrada = alfabetPermutat[posicio];  
                if (esMinuscula) {
                    lletraXifrada = Character.toLowerCase(lletraXifrada);
                }
                cadenaFinal.append(lletraXifrada);
            } else {
                cadenaFinal.append(lletra); 
            }
        }
        return cadenaFinal.toString();
    }

    public static String desxifraMonoAlfa(String cadena) {
        StringBuilder cadenaFinal = new StringBuilder();

        for (int i = 0; i < cadena.length(); i++) {
            char lletra = cadena.charAt(i);
            boolean esMinuscula = Character.isLowerCase(lletra);
            char lletraAMaiuscula = Character.toUpperCase(lletra);
            int posicio = -1;

            for (int j = 0; j < alfabetPermutat.length; j++) {
                if (alfabetPermutat[j] == lletraAMaiuscula) {
                    posicio = j;
                    break;
                }
            }

            if (posicio != -1) {
                char lletraDesxifrada = abecedari[posicio]; 
                if (esMinuscula) {
                    lletraDesxifrada = Character.toLowerCase(lletraDesxifrada);
                }
                cadenaFinal.append(lletraDesxifrada);
            } else {
                cadenaFinal.append(lletra);
            }
        }
        return cadenaFinal.toString();
    }

    public static void main(String[] args) {
        String frase1 = "M'agrada la Gwenpool i el Fortnite";
        String frase2 = "El més bonic del món és el cànon de la música que sona com un riu.";

        String frase1Xifrada = xifraMonoAlfa(frase1);
        String frase2Xifrada = xifraMonoAlfa(frase2);

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
