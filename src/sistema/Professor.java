package sistema;

public class Professor extends Usuario {
    
    public Professor(String nome, String email) {
        super(nome, email);
    }
    
    public void criarCurso(Curso curso) {
        System.out.println("Professor " + nome + " criou o curso: " + curso.getNome());
    }

    public void gerarRelatorioProgresso(Aluno aluno) {
        System.out.println("Relatório gerado para o aluno: " + aluno.getNome());
    }
}
