package iticbcn.xifratge;

public class AlgorismePoliAlfabetic extends AlgorismeFactory{
    @Override
    public Xifrador creaXifrador() {
        return new XifradorPoliAlfabetic();
    }
}