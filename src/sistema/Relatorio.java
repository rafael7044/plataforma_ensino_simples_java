package sistema;

import javax.swing.JOptionPane;

public class Relatorio {
    private int idRelatorio;
    private String progressoEstudante;

    public Relatorio(int idRelatorio, String progressoEstudante) {
        this.idRelatorio = idRelatorio;
        this.progressoEstudante = progressoEstudante;
    }

    public int getIdRelatorio() {
        return idRelatorio;
    }

    public String getProgressoEstudante() {
        return progressoEstudante;
    }

    public void gerarRelatorio(Aluno aluno, Curso curso) {
        String mensagem = "Relatório de Progresso:\n" +
                          "Aluno: " + aluno.getNome() + "\n" +
                          "Curso: " + curso.getNome() + "\n" +
                          "Progresso: " + progressoEstudante + "\n" +
                          "ID Relatório: " + idRelatorio;
        JOptionPane.showMessageDialog(null, mensagem, "Relatório Gerado", JOptionPane.INFORMATION_MESSAGE);
    }
}
