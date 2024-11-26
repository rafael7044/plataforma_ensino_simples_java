package sistema;

import javax.swing.JOptionPane;

public class Certificado {
    private int idCertificado;
    private String conteudo;

    public Certificado(int idCertificado, String conteudo) {
        this.idCertificado = idCertificado;
        this.conteudo = conteudo;
    }

    public int getIdCertificado() {
        return idCertificado;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void emitirCertificado(Aluno aluno, Curso curso) {
        String mensagem = "Certificado emitido com sucesso!\n" +
                          "Aluno: " + aluno.getNome() + "\n" +
                          "Curso: " + curso.getNome() + "\n" +
                          "ID Certificado: " + idCertificado;
        JOptionPane.showMessageDialog(null, mensagem, "Certificado Emitido", JOptionPane.INFORMATION_MESSAGE);
    }
}
