package sistema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PlataformaEnsino extends JFrame {
    private HashMap<Integer, String> cursos = new HashMap<>();
    private HashMap<Integer, ArrayList<String>> cursosComAlunos = new HashMap<>();
    private HashMap<String, String> certificados = new HashMap<>();
    private HashMap<Integer, String> relatorios = new HashMap<>();
    private int idCursoAtual = 1;
    private int idRelatorioAtual = 1;

    public PlataformaEnsino() {
        setTitle("Login - Plataforma de Ensino");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 1));

        JLabel labelLogin = new JLabel("Login:");
        JTextField campoLogin = new JTextField();
        JLabel labelSenha = new JLabel("Senha:");
        JPasswordField campoSenha = new JPasswordField();
        JButton botaoLogin = new JButton("Entrar");

        add(labelLogin);
        add(campoLogin);
        add(labelSenha);
        add(campoSenha);
        add(botaoLogin);

        botaoLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String login = campoLogin.getText();
                String senha = new String(campoSenha.getPassword());

                if (login.equals("professor") && senha.equals("1234")) {
                    abrirTelaProfessor();
                } else if (login.equals("aluno") && senha.equals("abcd")) {
                    abrirTelaAluno();
                } else {
                    JOptionPane.showMessageDialog(null, "Credenciais inválidas.");
                }
            }
        });
    }

    private void abrirTelaProfessor() {
        JFrame telaProfessor = new JFrame("Professor - Plataforma de Ensino");
        telaProfessor.setSize(400, 400);
        telaProfessor.setLayout(new BorderLayout());

        JPanel painelCentral = new JPanel(new GridLayout(11, 1));
        JTextField campoNomeCurso = new JTextField("Nome do Curso");
        JButton botaoCriarCurso = new JButton("Criar Curso");
        JTextField campoIdCurso = new JTextField("ID do Curso para Listagem");
        JButton botaoListarAlunos = new JButton("Listar Alunos");
        JButton botaoEmitirCertificados = new JButton("Emitir Certificados");
        JTextField campoNomeEstudante = new JTextField("Nome do Estudante para Relatório");
        JButton botaoGerarRelatorio = new JButton("Gerar Relatório");
        JButton botaoListarCursos = new JButton("Listar Cursos");
        JTextField campoIdCursoExcluir = new JTextField("Digite o ID do Curso para Excluir");
        JButton botaoExcluirCurso = new JButton("Excluir Curso");
        JButton botaoLogout = new JButton("Logout");

        painelCentral.add(campoNomeCurso);
        painelCentral.add(botaoCriarCurso);
        painelCentral.add(campoIdCurso);
        painelCentral.add(botaoListarAlunos);
        painelCentral.add(botaoEmitirCertificados);
        painelCentral.add(campoNomeEstudante);
        painelCentral.add(botaoGerarRelatorio);
        painelCentral.add(botaoListarCursos);
        painelCentral.add(campoIdCursoExcluir);
        painelCentral.add(botaoExcluirCurso);
        painelCentral.add(botaoLogout);

        telaProfessor.add(painelCentral, BorderLayout.CENTER);

        JLabel labelStatus = new JLabel("Logado como: professor");
        labelStatus.setHorizontalAlignment(SwingConstants.CENTER);
        telaProfessor.add(labelStatus, BorderLayout.SOUTH);

        botaoCriarCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeCurso = campoNomeCurso.getText();
                cursos.put(idCursoAtual, nomeCurso);
                cursosComAlunos.put(idCursoAtual, new ArrayList<>());
                JOptionPane.showMessageDialog(null, "Curso criado: " + nomeCurso + " (ID: " + idCursoAtual + ")");
                idCursoAtual++;
            }
        });

        botaoListarAlunos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idCurso = Integer.parseInt(campoIdCurso.getText());
                    if (cursos.containsKey(idCurso)) {
                        ArrayList<String> alunos = cursosComAlunos.get(idCurso);
                        StringBuilder sb = new StringBuilder("Alunos do curso " + cursos.get(idCurso) + ":\n");
                        for (String aluno : alunos) {
                            sb.append("- ").append(aluno).append("\n");
                        }
                        JOptionPane.showMessageDialog(null, sb.toString());
                    } else {
                        JOptionPane.showMessageDialog(null, "Curso não encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido.");
                }
            }
        });

        botaoEmitirCertificados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idCurso = Integer.parseInt(campoIdCurso.getText());
                    if (cursos.containsKey(idCurso)) {
                        ArrayList<String> alunos = cursosComAlunos.get(idCurso);
                        for (String aluno : alunos) {
                            certificados.put(aluno, "Certificado: " + cursos.get(idCurso));
                        }
                        JOptionPane.showMessageDialog(null, "Certificados emitidos para o curso: " + cursos.get(idCurso));
                    } else {
                        JOptionPane.showMessageDialog(null, "Curso não encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido.");
                }
            }
        });

        botaoGerarRelatorio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idCurso = Integer.parseInt(campoIdCurso.getText());
                    String nomeEstudante = campoNomeEstudante.getText().trim();
                    if (cursos.containsKey(idCurso)) {
                        ArrayList<String> alunos = cursosComAlunos.get(idCurso);
                        if (alunos.contains(nomeEstudante)) {
                            String progresso = "Progresso: Concluído";
                            String relatorio = "Relatório do Estudante " + nomeEstudante + " no Curso " + cursos.get(idCurso) + ":\n" + progresso;
                            relatorios.put(idRelatorioAtual, relatorio);
                            JOptionPane.showMessageDialog(null, "Relatório gerado para o estudante: " + nomeEstudante + " (ID: " + idRelatorioAtual + ")");
                            idRelatorioAtual++;
                        } else {
                            JOptionPane.showMessageDialog(null, "Estudante não encontrado no curso.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Curso não encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido.");
                }
            }
        });

        botaoListarCursos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StringBuilder sb = new StringBuilder("Cursos Disponíveis:\n");
                for (Integer idCurso : cursos.keySet()) {
                    sb.append("ID: ").append(idCurso).append(" - ").append(cursos.get(idCurso)).append("\n");
                }
                JOptionPane.showMessageDialog(null, sb.toString());
            }
        });

        botaoExcluirCurso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int idCurso = Integer.parseInt(campoIdCursoExcluir.getText());
                    if (cursos.containsKey(idCurso)) {
                        cursos.remove(idCurso);
                        cursosComAlunos.remove(idCurso);
                        JOptionPane.showMessageDialog(null, "Curso com ID " + idCurso + " excluído.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Curso não encontrado.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "ID inválido.");
                }
            }
        });

        botaoLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaProfessor.dispose();
                abrirTelaLogin();
            }
        });

        telaProfessor.setVisible(true);
    }

    private void abrirTelaAluno() {
        JFrame telaAluno = new JFrame("Aluno - Plataforma de Ensino");
        telaAluno.setSize(300, 250); // Aumentei o tamanho para acomodar o JComboBox
        telaAluno.setLayout(new BorderLayout());

        JPanel painelCentral = new JPanel(new GridLayout(6, 1)); // Ajustado para 6 itens
        JTextField campoNomeAluno = new JTextField("Nome do Aluno");
        JButton botaoInscrever = new JButton("Inscrever no Curso");
        JComboBox<String> comboCursos = new JComboBox<>();
        JButton botaoVerCertificado = new JButton("Ver Certificado");
        JButton botaoFazerAvaliacao = new JButton("Fazer Avaliação");
        JButton botaoLogout = new JButton("Logout");

        painelCentral.add(campoNomeAluno);
        painelCentral.add(comboCursos);
        painelCentral.add(botaoInscrever);
        painelCentral.add(botaoVerCertificado);
        painelCentral.add(botaoFazerAvaliacao);
        painelCentral.add(botaoLogout);

        telaAluno.add(painelCentral, BorderLayout.CENTER);

        JLabel labelStatus = new JLabel("Logado como: aluno");
        labelStatus.setHorizontalAlignment(SwingConstants.CENTER);
        telaAluno.add(labelStatus, BorderLayout.SOUTH);

        // Adicionar cursos ao JComboBox
        for (Integer idCurso : cursos.keySet()) {
            comboCursos.addItem(cursos.get(idCurso));
        }

        botaoInscrever.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeAluno = campoNomeAluno.getText();
                String cursoSelecionado = (String) comboCursos.getSelectedItem();
                int idCursoSelecionado = -1;

                // Encontrar o ID do curso selecionado
                for (Integer idCurso : cursos.keySet()) {
                    if (cursos.get(idCurso).equals(cursoSelecionado)) {
                        idCursoSelecionado = idCurso;
                        break;
                    }
                }

                if (idCursoSelecionado != -1) {
                    cursosComAlunos.get(idCursoSelecionado).add(nomeAluno);
                    JOptionPane.showMessageDialog(null, "Aluno " + nomeAluno + " inscrito no curso: " + cursoSelecionado);
                } else {
                    JOptionPane.showMessageDialog(null, "Curso não encontrado.");
                }
            }
        });

        botaoVerCertificado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeAluno = campoNomeAluno.getText();
                if (certificados.containsKey(nomeAluno)) {
                    JOptionPane.showMessageDialog(null, certificados.get(nomeAluno));
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum certificado encontrado para " + nomeAluno);
                }
            }
        });

        botaoFazerAvaliacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Avaliação realizada.");
            }
        });

        botaoLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                telaAluno.dispose();
                abrirTelaLogin();
            }
        });

        telaAluno.setVisible(true);
    }

    private void abrirTelaLogin() {
        PlataformaEnsino plataforma = new PlataformaEnsino();
        plataforma.setVisible(true);
    }

    public static void main(String[] args) {
        PlataformaEnsino plataforma = new PlataformaEnsino();
        plataforma.setVisible(true);
    }
}
