package filmnow;

/**
 * Sistema que mantém os seus filmes prediletos. Podem existir 100 filmes.
 *
 * @author Bruna de Medeiros Rocha
 *
 */
public class FilmNow {

    private static final int TAMANHO = 100;
    private static final int TAMANHOHOT = 10;
    private Filme[] filmes;
    private Filme[] hotList;

    public FilmNow() {
        this.filmes = new Filme[TAMANHO];
        this.hotList = new Filme[TAMANHOHOT];
    }

    public Filme[] getFilmes() {

        return this.filmes.clone();
    }

    public Filme getFilme(int posicao) {

        return this.filmes[posicao - 1];
    }

    public String cadastraFilme(int posicao, String nome, String ano, String local) {
        if (posicao < 1 || posicao > this.filmes.length) {
            return "POSIÇÃO INVÁLIDA";
        }

        if (nome == null || nome.trim().isEmpty() || local == null || local.trim().isEmpty()) {
            return "FILME INVÁLIDO";
        }

        Filme novoFilme = new Filme(nome, ano, local);

        for (int i = 0; i < this.filmes.length; i++) {
            if (this.filmes[i] != null && this.filmes[i].equals(novoFilme)) {
                return "FILME JÁ ADICIONADO";
            }
        }

        this.filmes[posicao - 1] = novoFilme;
        return "FILME ADICIONADO";
    }

    public String mostrarTodos() {
        String resultado = "";

        for (int i = 0; i < this.filmes.length; i++) {
            if (this.filmes[i] != null) {
                resultado += (i + 1) + " - " + this.filmes[i].toString() + "\n";
            }
        }
        return resultado;
    }

    public String detalhaFilme(int posicao) {
        if (posicao < 1 || posicao > 100) {
            return "POSIÇÃO INVÁLIDA!";
        }

        Filme filme = this.filmes[posicao - 1];
        if (filme == null) {
            return "POSIÇÃO INVÁLIDA!";
        }

        boolean isHot = false;
        for (int i = 0; i < this.hotList.length; i++) {
            if (this.hotList[i] != null && this.hotList[i].equals(filme)) {
                isHot = true;
                break;
            }
        }

        if (filme.getAno() != "") {
            if (isHot == true) {
                return "\uD83D\uDD25" + " " + filme.getNome() + ", " + filme.getAno() + "\n" + filme.getLocal();
            }

            return filme.getNome() + ", " + filme.getAno() + "\n" + filme.getLocal();
        } else {

            if (isHot == true) {
                return "\uD83D\uDD25" + " " + filme.getNome() + "\n" + filme.getLocal();
            }

            return filme.getNome() + "\n" + filme.getLocal();
        }
    }

    public String adicionaHot(int posicaoFilme, int posicaoHot) {
        if (posicaoFilme < 1 || posicaoFilme >= filmes.length || this.filmes[posicaoFilme - 1] == null) {
            return "POSIÇÃO DE FILME INVÁLIDA!";
        }

        if (posicaoHot < 1 || posicaoHot > this.hotList.length) {
            return "POSIÇÃO DA HOTLIST INVÁLIDA!";
        }

        Filme filmeAAdicionar = this.filmes[posicaoFilme - 1];

        for (int i = 0; i < this.hotList.length; i++) {
            if (this.hotList[i] != null && this.hotList[i].equals(filmeAAdicionar)) {
                return "FILME JÁ ESTÁ NA HOTLIST!";
            }
        }

        this.hotList[posicaoHot - 1] = filmeAAdicionar;
        return "ADICIONADO À HOTLIST NA POSIÇÃO " + (posicaoHot);
    }

    public void removeHot(int posicaoHot) {

        this.hotList[posicaoHot - 1] = null;
    }

    public String exibeHotList() {
        String resultadoHot = "";

        for (int i = 0; i < this.hotList.length; i++) {
            if (hotList[i] != null) {
                resultadoHot += (i + 1) + " - " + hotList[i].getNome() + ", " + hotList[i].getAno() + "\n";
            }
        }

        return resultadoHot;
    }
}