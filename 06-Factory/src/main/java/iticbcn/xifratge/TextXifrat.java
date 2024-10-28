package iticbcn.xifratge;

public class TextXifrat {
    private byte[] text;

    public TextXifrat(byte[] text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return new String(text);  // Converteix l'array de bytes a String
    }

    public byte[] getBytes() {
        return text;
    }
}
