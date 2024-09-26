import java.util.Scanner;

public class RotX {
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

    public static String xifraRotX(String cadena, int desplacament) {
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

    public static String desxifraRotX(String cadena, int desplacament) {
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

    public static String forcaBrutaX(String cadenaXifrada) {
        for (int i = 0; i < ABECEDARIMAJUS.length; i++) {
            cadenaXifrada = desxifraRotX(cadenaXifrada, i);
        }
        return cadenaXifrada;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduïu un text");
        String text = scanner.nextLine();
        System.out.println("Vols xifrar o desxifrar?");
        String resposta = scanner.nextLine();
        System.out.println("Quantes posicions?");
        int desplacament = scanner.nextInt();
        desplacament = desplacament % ABECEDARIMINUS.length;
        if (resposta.equalsIgnoreCase("xifrar") || resposta.equalsIgnoreCase("x")) {
            String textXifrat = xifraRotX(text, desplacament);
            System.out.println(textXifrat);
            System.out.println("Ara el programa mostrarà el missatge desxifrat amb desplaçaments fins a la longitud de l'abecedari:");
            System.out.println(forcaBrutaX(textXifrat));
            
        } else if (resposta.equalsIgnoreCase("desxifrar") || resposta.equalsIgnoreCase("d")) {
            String textDesxifrat = desxifraRotX(text, desplacament);
            System.out.println(textDesxifrat);
            System.out.println("Ara el programa mostrarà el missatge desxifrat amb desplaçaments fins a la longitud de l'abecedari:");
            System.out.println(forcaBrutaX(textDesxifrat));
        }
        scanner.close();
    }
}
