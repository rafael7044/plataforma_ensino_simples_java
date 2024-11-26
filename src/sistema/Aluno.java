package sistema;

public class Aluno extends Usuario {
    private boolean certificadoEmitido = false;

    public Aluno(String nome, String email) {
        super(nome, email);
    }

    public void inscreverCurso(Curso curso) {
        System.out.println("Aluno " + nome + " inscrito no curso: " + curso.getNome());
    }

    public void realizarAvaliacao(Avaliacao avaliacao) {
        System.out.println("Aluno " + nome + " realizou a avaliação: " + avaliacao.getNome());
        emitirCertificado();
    }

    private void emitirCertificado() {
        certificadoEmitido = true;
        System.out.println("Certificado emitido para o aluno: " + nome);
    }
}
