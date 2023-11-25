import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {
    private Eventos eventos;
    private JTextField campoCodigo, campoData, campoLatitude, campoLongitude;
    private JTextField campoVelocidade, campoPrecipitacao, campoEstiagem, campoMagnitude;
    private JTextArea areaMensagens;
    private JButton botaoCadastrarCiclone, botaoCadastrarSeca, botaoCadastrarTerremoto;
    private JButton botaoLimpar, botaoMostrar, botaoFechar;

    public GUI() {
        super();
        eventos = new Eventos();

        campoCodigo = new JTextField(20);
        campoData = new JTextField(20);
        campoLatitude = new JTextField(20);
        campoLongitude = new JTextField(20);
        campoVelocidade = new JTextField(20);
        campoPrecipitacao = new JTextField(20);
        campoEstiagem = new JTextField(20);
        campoMagnitude = new JTextField(20);
        areaMensagens = new JTextArea(10, 30);

        botaoCadastrarCiclone = new JButton("Cadastrar Ciclone");
        botaoCadastrarSeca = new JButton("Cadastrar Seca");
        botaoCadastrarTerremoto = new JButton("Cadastrar Terremoto");
        botaoLimpar = new JButton("Limpar");
        botaoMostrar = new JButton("Mostrar Eventos");
        botaoFechar = new JButton("Fechar");

        JPanel painel = new JPanel(new GridLayout(0, 2));
        this.setTitle("Cadastra Eventos");
        painel.add(new JLabel("Código:"));
        painel.add(campoCodigo);
        painel.add(new JLabel("Data:"));
        painel.add(campoData);
        painel.add(new JLabel("Latitude:"));
        painel.add(campoLatitude);
        painel.add(new JLabel("Longitude:"));
        painel.add(campoLongitude);
        painel.add(new JLabel("Velocidade (Ciclone):"));
        painel.add(campoVelocidade);
        painel.add(new JLabel("Precipitação (Ciclone):"));
        painel.add(campoPrecipitacao);
        painel.add(new JLabel("Estiagem (Seca):"));
        painel.add(campoEstiagem);
        painel.add(new JLabel("Magnitude (Terremoto):"));
        painel.add(campoMagnitude);

        JPanel painelBotoes = new JPanel(new FlowLayout());
        painelBotoes.add(botaoCadastrarCiclone);
        painelBotoes.add(botaoCadastrarSeca);
        painelBotoes.add(botaoCadastrarTerremoto);
        painelBotoes.add(botaoLimpar);
        painelBotoes.add(botaoMostrar);
        painelBotoes.add(botaoFechar);

        setLayout(new BorderLayout());
        add(painel, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
        add(new JScrollPane(areaMensagens), BorderLayout.EAST);

        botaoCadastrarCiclone.addActionListener(this);
        botaoCadastrarSeca.addActionListener(this);
        botaoCadastrarTerremoto.addActionListener(this);
        botaoLimpar.addActionListener(this);
        botaoMostrar.addActionListener(this);
        botaoFechar.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        try {
           

            if (source == botaoCadastrarCiclone && !eventos.containsCodigo(campoCodigo.getText())) {
                testaCampos();
                Ciclone ciclone = new Ciclone(
                    campoCodigo.getText(),
                    campoData.getText(),
                    Double.parseDouble(campoLatitude.getText()),
                    Double.parseDouble(campoLongitude.getText()),
                    Double.parseDouble(campoVelocidade.getText()),
                    Double.parseDouble(campoPrecipitacao.getText())
                );
                eventos.addEvento(ciclone);
                campoCodigo.setText("");
                campoData.setText("");
                campoLatitude.setText("");
                campoLongitude.setText("");
                campoVelocidade.setText("");
                campoPrecipitacao.setText("");
                campoEstiagem.setText("");
                campoMagnitude.setText("");
                areaMensagens.append("Ciclone cadastrado: " + ciclone + "\n");
            } else if (source == botaoCadastrarSeca && !eventos.containsCodigo(campoCodigo.getText())) {
                testaCampos();
                Seca seca = new Seca(
                campoCodigo.getText(),
                campoData.getText(),
                Double.parseDouble(campoLatitude.getText()),
                Double.parseDouble(campoLongitude.getText()),
                 Integer.parseInt(campoEstiagem.getText())

            );
            eventos.addEvento(seca);
             campoCodigo.setText("");
                campoData.setText("");
                campoLatitude.setText("");
                campoLongitude.setText("");
                campoVelocidade.setText("");
                campoPrecipitacao.setText("");
                campoEstiagem.setText("");
                campoMagnitude.setText("");
            areaMensagens.append("Seca cadastrada: " + seca + "\n");
            } else if (source == botaoCadastrarTerremoto && !eventos.containsCodigo(campoCodigo.getText())) {
                testaCampos();
                Terremoto terremoto = new Terremoto(
                campoCodigo.getText(),
                campoData.getText(),
                Double.parseDouble(campoLatitude.getText()),
                Double.parseDouble(campoLongitude.getText()),
                Double.parseDouble(campoMagnitude.getText())
            );
            eventos.addEvento(terremoto);
             campoCodigo.setText("");
                campoData.setText("");
                campoLatitude.setText("");
                campoLongitude.setText("");
                campoVelocidade.setText("");
                campoPrecipitacao.setText("");
                campoEstiagem.setText("");
                campoMagnitude.setText("");
            areaMensagens.append("Terremoto cadastrado: " + terremoto + "\n");
            } else if (source == botaoLimpar) {
                limparCampos();
            } else if (source == botaoMostrar) {
                mostrarEventos();
            } else if (source == botaoFechar) {
                System.exit(0);
            }
            else if(eventos.containsCodigo(campoCodigo.getText())){
                areaMensagens.append("Erro: Código já cadastrado.\n");
            }
            } catch (NumberFormatException ex) {
                areaMensagens.append("Erro: Formato de número inválido.\n");

            } catch (Exception ex) {
            areaMensagens.append("Erro: " + ex.getMessage() + "\n");

            }
            }

            private void limparCampos() {
                campoCodigo.setText("");
                campoData.setText("");
                campoLatitude.setText("");
                campoLongitude.setText("");
                campoVelocidade.setText("");
                campoPrecipitacao.setText("");
                campoEstiagem.setText("");
                campoMagnitude.setText("");
                areaMensagens.setText("");
            }

            private void testaCampos(){
                 if (campoCodigo.getText().trim().isEmpty() ||
                    campoData.getText().trim().isEmpty() ||
                    campoLatitude.getText().trim().isEmpty() ||
                    campoLongitude.getText().trim().isEmpty()) {
                    throw new IllegalArgumentException("Todos os campos devem ser preenchidos.");
                }
            }
            
            private void mostrarEventos() {
                if (eventos.getEventos().isEmpty()) {
                    areaMensagens.append("Não há eventos cadastrados.\n");
                    return;
                }
                areaMensagens.setText("");
                for (Evento evento : eventos.getEventos()) {
                    areaMensagens.append(evento.toString() + "\n");
                }
            }
        }
