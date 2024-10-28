package iticbcn.xifratge;
import java.util.Scanner;

public class XifradorRotX implements Xifrador {
    public static final char[] ABECEDARIMINUS = {
        'a', 'à', 'b', 'c', 'ç', 'd', 'e', 'è', 'é', 'f', 'g', 'h', 'i', 'í', 'ï',
        'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 'ó', 'p', 'q', 'r', 's', 't', 'u', 
        'ú', 'ü', 'v', 'w', 'x', 'y', 'z'
    };

    public static final char[] ABECEDARIMAJUS = {
        'A', 'À', 'B', 'C', 'Ç', 'D', 'E', 'È', 'É', 'F', 'G', 'H', 'I', 'Í', 'Ï',
        'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 'Ó', 'P', 'Q', 'R', 'S', 'T', 'U', 
        'Ú', 'Ü', 'V', 'W', 'X', 'Y', 'Z'
    };

    public String xifraRotX(String cadena, int desplacament) {
        String cadenaFinal = "";
        int longitudMinus = ABECEDARIMINUS.length;
        int longitudMajus = ABECEDARIMAJUS.length;
        
        for (int caracter = 0; caracter < cadena.length(); caracter++) {
            char lletra = cadena.charAt(caracter);
            if (Character.isLowerCase(lletra)) {
                for (int i = 0; i < longitudMinus; i++) {
                    if (ABECEDARIMINUS[i] == lletra) {
                        // Aseguramos desplazamiento cíclico
                        cadenaFinal += ABECEDARIMINUS[(i + desplacament) % longitudMinus];
                        break;
                    }
                }
            } else if (Character.isUpperCase(lletra)) {
                for (int j = 0; j < longitudMajus; j++) {
                    if (ABECEDARIMAJUS[j] == lletra) {
                        // Aseguramos desplazamiento cíclico
                        cadenaFinal += ABECEDARIMAJUS[(j + desplacament) % longitudMajus];
                        break;
                    }
                }
            } else {
                cadenaFinal += lletra;
            }
        }
        return cadenaFinal;
    }

    public String desxifraRotX(String cadena, int desplacament) {
        String cadenaFinal = "";
        int longitudMinus = ABECEDARIMINUS.length;
        int longitudMajus = ABECEDARIMAJUS.length;

        for (int caracter = 0; caracter < cadena.length(); caracter++) {
            char lletra = cadena.charAt(caracter);
            if (Character.isLowerCase(lletra)) {
                for (int i = 0; i < longitudMinus; i++) {
                    if (ABECEDARIMINUS[i] == lletra) {
                        cadenaFinal += ABECEDARIMINUS[(i - desplacament + longitudMinus) % longitudMinus];
                        break;
                    }
                }
            } else if (Character.isUpperCase(lletra)) {
                for (int j = 0; j < longitudMajus; j++) {
                    if (ABECEDARIMAJUS[j] == lletra) {
                        cadenaFinal += ABECEDARIMAJUS[(j - desplacament + longitudMajus) % longitudMajus];
                        break;
                    }
                }
            } else {
                cadenaFinal += lletra;
            }
        }
        return cadenaFinal;
    }

    public void forcaBrutaX(String cadenaXifrada) {
        for (int i = 0; i < ABECEDARIMAJUS.length; i++) {
            String resultat = desxifraRotX(cadenaXifrada, i);
            System.out.println("Amb desplaçament de "+ i + " és: " + resultat);
        }
    }

    @Override
    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        int desplacament;
        try {
            desplacament = Integer.parseInt(clau);  // Convertim la clau a un int
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau " + clau + " no és vàlida per Xifrat RotX");
        }

        String msgXifrat = xifraRotX(msg, desplacament);
        return new TextXifrat(msgXifrat.getBytes());  // Retorna el resultat com a TextXifrat
    }

    @Override
    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        int desplacament;
        try {
            desplacament = Integer.parseInt(clau);  // Converteix la clau a un ints
        } catch (NumberFormatException e) {
            throw new ClauNoSuportada("La clau " + clau + " no és vàlida per Xifrat RotX");
        }

        String msgXifrat = new String(xifrat.getBytes());  // Obté el missatge xifrat des dels bytes
        return desxifraRotX(msgXifrat, desplacament);  // Desencripta i retorna el missatge com a String
    }
}
