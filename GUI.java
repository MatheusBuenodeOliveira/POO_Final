import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class GUI extends JFrame implements ActionListener {
    private Eventos eventos;
    private JTextField campoCodigo, campoData, campoLatitude, campoLongitude;
    private JTextField campoVelocidade, campoPrecipitacao, campoEstiagem, campoMagnitude;
    private JTextArea areaMensagens;
    private JButton botaoCadastrarCiclone, botaoCadastrarSeca, botaoCadastrarTerremoto;
    private JButton botaoLimpar, botaoMostrar, botaoFechar, botaoLerArquivo;


    public GUI(Eventos event) {
        super();
        this.eventos = event;

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
        botaoLerArquivo = new JButton("Ler Arquivo de Eventos");

        botaoFechar = new JButton("Voltar");

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
        painelBotoes.add(botaoLerArquivo);

        


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
        botaoLerArquivo.addActionListener(this);

        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
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
                GUI.this.dispose();
            }
            else if(eventos.containsCodigo(campoCodigo.getText())){
                areaMensagens.append("Erro: Código já cadastrado.\n");
            }
            else if (source == botaoLerArquivo) {
              JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Escolha o arquivo de eventos");
                int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                lerArquivoEventos(selectedFile.getAbsolutePath());
             }
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


            private void lerArquivoEventos(String filePath) {
                Scanner entrada = null; 
               	try {
			    BufferedReader streamEntrada = new BufferedReader(new FileReader(filePath));
			    entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
			   // PrintStream streamSaida = new PrintStream(new File("dadosout.txt"), Charset.forName("UTF-8"));
			    //System.setOut(streamSaida);             // Usa como saida um arquivo
		        } catch (Exception e) {
			    System.out.println(e);
		        }
		        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
		        entrada.useLocale(Locale.ENGLISH);    
               
            try {
                
                String linha;
                try {    
                    entrada.nextLine(); // Pula a primeira linha      
                    while (entrada.hasNextLine()) {
                        linha = entrada.nextLine();
                        String[] partes = linha.split(";");
            
                        String codigo = (partes[0]);
                        String data = partes[1];
                        double latitude = Double.parseDouble(partes[2]);
                        double longitude = Double.parseDouble(partes[3]);
                        int tipoEvento = Integer.parseInt(partes[4]);
            
                        switch (tipoEvento) {
                            case 1: // Ciclone
                                double velocidade = Double.parseDouble(partes[5]);
                                double precipitacao = Double.parseDouble(partes[6]);
                                eventos.addEvento(new Ciclone(codigo, data, latitude, longitude, velocidade, precipitacao));
                                break;
                            case 2: // Terremoto
                                double magnitude = Double.parseDouble(partes[5]);
                                eventos.addEvento(new Terremoto(codigo, data, latitude, longitude, magnitude));
                                break;
                            case 3: // Seca
                                int diasEstiagem = Integer.parseInt(partes[5]);
                                eventos.addEvento(new Seca(codigo, data, latitude, longitude, diasEstiagem));
                                
                                break;
                            default:
                                areaMensagens.append("Tipo de evento desconhecido");
                        }
                        areaMensagens.append("Eventos cadastrados com sucesso");
                    }
                } catch (Exception e) {
                     areaMensagens.append("Erro ao ler dados do arquivo: " + e.getMessage());
                }
            } catch (Exception e) {
                 areaMensagens.append("Erro ao abrir o arquivo: " + e.getMessage());
            }
            
            
             
            }
        }
