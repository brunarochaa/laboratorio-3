package filmnow;

public class Filme {

    private String nome;
    private String ano;
    private String local;
    private int posicao;
    private boolean isHot;

    public Filme(int posicao, String nome, String ano, String local) {
        this.nome = nome;
        this.posicao = posicao;
        this.local = local;
        this.ano = ano;
        this.isHot = false;
    }

    public void setStatus(boolean isHot) {
        if (isHot == false) {
            isHot = true;
        } else {
            isHot = false;
        }
    }

    @Override
    public String toString() {
        return this.nome + ", " + this.ano + "\n" + this.local;
    }

   /* @Override
    public boolean equals(Object o) {

    }

    @Override
    public int hashCode() {

    }

   */
}

