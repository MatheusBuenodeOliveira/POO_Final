import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame {

    private JTextField campoNome, campoQuantidade, campoLatitude, campoLongitude;
    private JTextArea areaMensagem;
    private Equipes equipes;
    private JButton botaoConfirmar, botaoLimpar, botaoMostrarDados, botaoFechar;

    public Interface() {
        equipes = new Equipes();
        setTitle("Formulário de Cadastro de Equipes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new GridLayout(4, 2, 10, 10));

        painelCampos.add(new JLabel("Nome:"));
        campoNome = new JTextField();
        painelCampos.add(campoNome);

        painelCampos.add(new JLabel("Quantidade:"));
        campoQuantidade = new JTextField();
        painelCampos.add(campoQuantidade);

        painelCampos.add(new JLabel("Latitude:"));
        campoLatitude = new JTextField();
        painelCampos.add(campoLatitude);

        painelCampos.add(new JLabel("Longitude:"));
        campoLongitude = new JTextField();
        painelCampos.add(campoLongitude);

        add(painelCampos, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        botaoConfirmar = new JButton("Confirmar");
        botaoLimpar = new JButton("Limpar");
        botaoMostrarDados = new JButton("Mostrar Dados");
        botaoFechar = new JButton("Fechar");

        botaoConfirmar.addActionListener(criarActionListener());
        botaoLimpar.addActionListener(criarActionListener());
        botaoMostrarDados.addActionListener(criarActionListener());
        botaoFechar.addActionListener(criarActionListener());

        painelBotoes.add(botaoConfirmar);
        painelBotoes.add(botaoLimpar);
        painelBotoes.add(botaoMostrarDados);
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
                if (e.getSource() == botaoConfirmar) {
                    registrarDados();
                } else if (e.getSource() == botaoLimpar) {
                    limparCampos();
                } else if (e.getSource() == botaoMostrarDados) {
                    mostrarDados();
                } else if (e.getSource() == botaoFechar) {
                    System.exit(0);
                }
            }
        };
    }

    private void registrarDados() {
        try {
            String nome = campoNome.getText();
            int quantidade = Integer.parseInt(campoQuantidade.getText());
            double latitude = Double.parseDouble(campoLatitude.getText());
            double longitude = Double.parseDouble(campoLongitude.getText());
    
            Equipe aux = new Equipe(nome, quantidade, latitude, longitude);
            if (equipes.addEquipe(aux)) {
                areaMensagem.setText("Dados cadastrados com sucesso!\n");
            } else {
                areaMensagem.setText("Erro ao cadastrar dados!\n");
            }
        } catch (NumberFormatException e) {
            areaMensagem.setText("Erro: Verifique se os campos 'Quantidade', 'Latitude' e 'Longitude' estão preenchidos corretamente.\n");
        } catch (Exception e) {
            areaMensagem.setText("Erro inesperado: " + e.getMessage() + "\n");
        }
    }    

    private void limparCampos() {
        campoNome.setText("");
        campoQuantidade.setText("");
        campoLatitude.setText("");
        campoLongitude.setText("");
        areaMensagem.setText("");
    }

    private void mostrarDados() {
        areaMensagem.setText("Dados Cadastrados:\n");
        for (Equipe aux : equipes.getEquipes()){
            areaMensagem.append(aux.toString() + "\n");
        }
    }
}