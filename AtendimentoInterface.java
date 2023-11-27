import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AtendimentoInterface extends JFrame {

    private JTextField campoCod, campoDataInicio, campoDuracao, campoStatus;
    private JTextArea areaMensagem;
    private Atendimentos atendimentos;
    private JButton botaoAdicionar, botaoRemover, botaoBuscar, botaoMostrar, botaoFechar;

    public AtendimentoInterface() {
        atendimentos = new Atendimentos();
        setTitle("Cadastro de Atendimentos");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new GridLayout(4, 2, 10, 10));

        painelCampos.add(new JLabel("Código:"));
        campoCod = new JTextField();
        painelCampos.add(campoCod);

        painelCampos.add(new JLabel("Data de Início:"));
        campoDataInicio = new JTextField();
        painelCampos.add(campoDataInicio);

        painelCampos.add(new JLabel("Duração:"));
        campoDuracao = new JTextField();
        painelCampos.add(campoDuracao);

        painelCampos.add(new JLabel("Status:"));
        campoStatus = new JTextField();
        painelCampos.add(campoStatus);

        add(painelCampos, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        botaoAdicionar = new JButton("Adicionar");
        botaoRemover = new JButton("Remover");
        botaoBuscar = new JButton("Buscar");
        botaoMostrar = new JButton("Mostrar Todos");
        botaoFechar = new JButton("Fechar");

        botaoAdicionar.addActionListener(criarActionListener());
        botaoRemover.addActionListener(criarActionListener());
        botaoBuscar.addActionListener(criarActionListener());
        botaoMostrar.addActionListener(criarActionListener());
        botaoFechar.addActionListener(criarActionListener());

        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoRemover);
        painelBotoes.add(botaoBuscar);
        painelBotoes.add(botaoMostrar);
        painelBotoes.add(botaoFechar);

        add(painelBotoes, BorderLayout.CENTER);

        areaMensagem = new JTextArea(10, 30);
        areaMensagem.setEditable(false);
        JScrollPane painelRolagem = new JScrollPane(areaMensagem);
        add(painelRolagem, BorderLayout.SOUTH);

        setVisible(true);
    }

    private ActionListener criarActionListener() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == botaoAdicionar) {
                    adicionarAtendimento();
                } else if (e.getSource() == botaoRemover) {
                    removerAtendimento();
                } else if (e.getSource() == botaoBuscar) {
                    buscarAtendimento();
                } else if (e.getSource() == botaoMostrar) {
                    mostrarAtendimentos();
                } else if (e.getSource() == botaoFechar) {
                    System.exit(0);
                }
            }
        };
    }

    private void adicionarAtendimento() {
        try {
            int cod = Integer.parseInt(campoCod.getText());
            String dataInicio = campoDataInicio.getText();
            int duracao = Integer.parseInt(campoDuracao.getText());
            String status = campoStatus.getText();

            Atendimento atendimento = new Atendimento(cod, dataInicio, duracao, status, null, null); // Evento e Equipe são nulos

            if (atendimentos.addAtendimento(atendimento)) {
                areaMensagem.setText("Atendimento adicionado com sucesso!\n");
            } else {
                areaMensagem.setText("Erro ao adicionar atendimento. O código já existe.\n");
            }
        } catch (NumberFormatException e) {
            areaMensagem.setText("Erro: Verifique se os campos estão preenchidos corretamente.\n");
        }
    }

    private void removerAtendimento() {
        try {
            int cod = Integer.parseInt(campoCod.getText());

            if (atendimentos.removeAtendimentoCod(cod)) {
                areaMensagem.setText("Atendimento removido com sucesso!\n");
            } else {
                areaMensagem.setText("Atendimento com código " + cod + " não encontrado.\n");
            }
        } catch (NumberFormatException e) {
            areaMensagem.setText("Erro: Digite um código válido para remover o atendimento.\n");
        }
    }

    private void buscarAtendimento() {
        try {
            int cod = Integer.parseInt(campoCod.getText());
            Atendimento atendimento = atendimentos.getAtendimento(cod);

            if (atendimento != null) {
                areaMensagem.setText("Atendimento encontrado:\n" + atendimento.toString() + "\n");
            } else {
                areaMensagem.setText("Atendimento com código " + cod + " não encontrado.\n");
            }
        } catch (NumberFormatException e) {
            areaMensagem.setText("Erro: Digite um código válido para buscar o atendimento.\n");
        }
    }

    private void mostrarAtendimentos() {
        ArrayList<Atendimento> listaAtendimentos = atendimentos.getAtendimentos();
        if (listaAtendimentos.isEmpty()) {
            areaMensagem.setText("Nenhum atendimento cadastrado.\n");
        } else {
            areaMensagem.setText("Lista de Atendimentos:\n");
            for (Atendimento atendimento : listaAtendimentos) {
                areaMensagem.append(atendimento.toString() + "\n");
            }
        }
    }
}
