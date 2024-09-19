public class Rot13 {
    public static final char[] abecedariMinus = {'a', 'b', 'c', 'ç', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l','m', 'n', 'ñ', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    public static final char[] abecedariMajus = {'A', 'B', 'C', 'Ç', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'Ñ', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    public static String xifraRot13(String cadena) {
        String cadenaFinal = "";
        for (int caracter = 0; caracter < cadena.length(); caracter++) {
            char lletra = cadena.charAt(caracter);
            cadenaFinal += cadena.charAt(caracter+13);
        }
        return cadenaFinal;
    }
    public static String desxifraRot13(String cadena) {
        for (int caracter = 0; caracter > cadena.length(); caracter--) {

        }
        return cadena;
    }
    public static void main(String[] args) {
        System.out.println("Introduïu un text");
        String text = "";
        System.out.println("Vols xifrar o desxifrar?");
        String resposta = "";
        if (resposta.equals("xifrar")) {
            xifraRot13(text);
        } else if (resposta.equals("desxifrar")) {
            desxifraRot13(text);
        }
    }
}