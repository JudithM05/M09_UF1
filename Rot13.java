import java.util.Scanner;
public class Rot13 {
    public static final char[] abecedariMinus = {'a', 'à', 'b', 'c', 'ç', 'd', 'e', 'è', 'f', 'g', 'h', 'i', 'í', 'j', 'k', 'l', 'm', 'n', 'ñ', 'o', 'ò', 'p', 'q', 'r', 's', 't', 'u', 'ú', 'v', 'w', 'x', 'y', 'z'};
    public static final char[] abecedariMajus = {'A', 'À', 'B', 'C', 'Ç', 'D', 'E', 'È', 'F', 'G', 'H', 'I', 'Í', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'Ò', 'P', 'Q', 'R', 'S', 'T', 'U', 'Ú', 'V', 'W', 'X', 'Y', 'Z'};

    public static String xifraRot13(String cadena) {
        String cadenaFinal = "";
        for (int caracter = 0; caracter < cadena.length(); caracter++) {
            char lletra = cadena.charAt(caracter);
            if (Character.isLowerCase(lletra)) {
                for (int i = 0; i < abecedariMinus.length; i++) {
                    if (abecedariMinus[i] == lletra) {
                        cadenaFinal += abecedariMinus[(i+13)% abecedariMinus.length];
                        break;
                    }
                }
            } else if (Character.isUpperCase(lletra)) {
                //lo mismo que el anterior pero en majus y el de descifrar es lo mismo pero con el minus
                for (int j = 0; j < abecedariMajus.length; j++) {
                    if (abecedariMajus[j] == lletra) {
                        cadenaFinal += abecedariMajus[(j+13)%abecedariMajus.length];
                        break;
                    }
                }
            } else {
                cadenaFinal += lletra;
            }
        }
        return cadenaFinal;
    }
    public static String desxifraRot13(String cadena) {
        String cadenaFinal = "";
        for (int caracter = 0; caracter < cadena.length(); caracter++) {
            char lletra = cadena.charAt(caracter);
            if (Character.isLowerCase(lletra)) {
                for (int i = 0; i < abecedariMinus.length; i++) {
                    if (abecedariMinus[i] == lletra) {
                        cadenaFinal += abecedariMinus[(i-13 + abecedariMinus.length)% abecedariMinus.length];
                        break;
                    }
                }
            } else if (Character.isUpperCase(lletra)) {
                //lo mismo que el anterior pero en majus y el de descifrar es lo mismo pero con el minus
                for (int j = 0; j < abecedariMajus.length; j++) {
                    if (abecedariMajus[j] == lletra) {
                        cadenaFinal += abecedariMajus[(j-13 + abecedariMajus.length)%abecedariMajus.length];
                        break;
                    }
                }
            } else {
                cadenaFinal += lletra;
            }
        }
        return cadenaFinal;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduïu un text");
        String text = scanner.nextLine();
        System.out.println("Vols xifrar o desxifrar?");
        String resposta = scanner.nextLine();
        if (resposta.equals("xifrar") || resposta.equals("Xifrar") || resposta.equals("X") || resposta.equals("x")) {
            String textXifrat = xifraRot13(text);
            System.out.println(textXifrat);
        } else if (resposta.equals("desxifrar") || resposta.equals("Desxifrar") || resposta.equals("D") || resposta.equals("d")) {
            String textDesxifrat = desxifraRot13(text);
            System.out.println(textDesxifrat);
        }
        scanner.close();
    }
}