package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;

public class XifradorMonoAlfabetic implements Xifrador {
    public static final char[] abecedari = "AÀÁÄBCÇDEÈÉËFGHIÍÏJKLMNOÒÓÖPQRSTUÚÜVWXYZÑ".toCharArray();
    public static final char[] alfabetPermutat;

    public XifradorMonoAlfabetic() {
        alfabetPermutat = permutaAlfabet(abecedari);  // Inicializamos el alfabeto permutado
    }

    public char[] permutaAlfabet(char[] alfabet) {
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

    public String xifraMonoAlfa(String cadena) {
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

    public String desxifraMonoAlfa(String cadena) {
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
}
