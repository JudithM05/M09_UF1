package iticbcn.xifratge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class XifradorPoliAlfabetic implements Xifrador {
    public static final char[] abecedari = "AÀÁÄBCÇDEÈÉËFGHIÍÏJKLMNOÒÓÖPQRSTUÚÜVWXYZÑ".toCharArray();
    public static final char[] alfabetPermutat;
    public static long clauSecreta;
    public static final Random random = new Random();

    // Bloque static per inicialitzar el alfabetPermutat
    static {
        alfabetPermutat = permutaAlfabet(abecedari);  // Inicialitza el alfabet permutat
    }
    public void initRandom(long clauSecreta) {
        random.setSeed(clauSecreta); //setSeed inicialitza GNA (generador de números aleatoris)
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        long clauSecreta;

        try {
            // Converteix la clau a un número long
            clauSecreta = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            // Si la clave no es un número válido, lanza la excepción ClauNoSuportada
            throw new ClauNoSuportada("La clau " + clau + " no és vàlida per Xifrat Polialfabètic");
        }

        // Inicialitza el generador de números aleatoris amb la clau secreta
        initRandom(clauSecreta);

        // Xifra el missatge utilitzant el métode existent
        String msgXifrat = xifraPoliAlfa(msg);

        // Retorna el resultat com a un objecte TextXifrat
        return new TextXifrat(msgXifrat.getBytes());
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        long clauSecreta;

        try {
            // Convertir la clau a un número long
            clauSecreta = Long.parseLong(clau);
        } catch (NumberFormatException e) {
            // Si la clau no és un número vàlid, llença l'excepció ClauNoSuportada
            throw new ClauNoSuportada("La clau " + clau + " no és vàlida per Xifrat Polialfabètic");
        }

        // Inicialitza el generador de números aleatoris amb la clau secreta
        initRandom(clauSecreta);

        // Desxifra el missatge utilitzant el métode existent
        String msgDesxifrat = desxifraPoliAlfa(new String(xifrat.getBytes()));

        // Retorna el missatge desxifrat
        return msgDesxifrat;
    }

    public static char[] permutaAlfabet(char[] alfabet) {
        ArrayList<Character> llista = new ArrayList<>();
        for (char c : alfabet) {
            llista.add(c);
        }
        Collections.shuffle(llista, random);
        char[] alfabetPermutat = new char[llista.size()];
        for (int i = 0; i < llista.size(); i++) {
            alfabetPermutat[i] = llista.get(i);
        }
        return alfabetPermutat;
    }

    public String xifraPoliAlfa(String msg) {
        StringBuilder cadenaFinal = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            char lletra = msg.charAt(i);
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

    public String desxifraPoliAlfa(String msgXifrat) {
        StringBuilder cadenaFinal = new StringBuilder();

        for (int i = 0; i < msgXifrat.length(); i++) {
            char lletra = msgXifrat.charAt(i);
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
