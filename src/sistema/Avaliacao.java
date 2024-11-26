package sistema;

public class Avaliacao {
    private String nome;
    private int notaMinima;

    public Avaliacao(String nome, int notaMinima) {
        this.nome = nome;
        this.notaMinima = notaMinima;
    }

    public String getNome() {
        return nome;
    }
}
