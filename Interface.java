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

public class Interface extends JFrame {

    private JTextField campoNome, campoQuantidade, campoLatitude, campoLongitude;
    private JTextArea areaMensagem;
    private Equipes equipes;
    private JButton botaoConfirmar, botaoLimpar, botaoMostrarDados, botaoFechar, botaoLerEquipes;

    public Interface(Equipes equipess) {
        equipes = equipess;
        setTitle("Formulário de Cadastro de Equipes");
        setSize(600, 400);
        setLocationRelativeTo(null);
        //setDefaultCloseOperation(EXIT_ON_CLOSE);
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
        botaoLerEquipes = new JButton("Ler Equipes de Arquivo");

        botaoConfirmar.addActionListener(criarActionListener());
        botaoLimpar.addActionListener(criarActionListener());
        botaoMostrarDados.addActionListener(criarActionListener());
        botaoFechar.addActionListener(criarActionListener());
        botaoLerEquipes.addActionListener(criarActionListener());
        painelBotoes.add(botaoConfirmar);
        painelBotoes.add(botaoLimpar);
        painelBotoes.add(botaoMostrarDados);
        painelBotoes.add(botaoFechar);
        painelBotoes.add(botaoLerEquipes);
        

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
                }else if (e.getSource() == botaoLerEquipes) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Escolha o arquivo de equipes");
                    int result = fileChooser.showOpenDialog(Interface.this);

                    if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    lerArquivoEventos(selectedFile.getAbsolutePath());
             }
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
    
        //    Equipe aux = new Equipe(nome, quantidade, latitude, longitude, equipamentos); ver como fazer com equipamentos
          //  if (equipes.addEquipe(aux)) {
                areaMensagem.setText("Dados cadastrados com sucesso!\n");
      //      } else {
                areaMensagem.setText("Erro ao cadastrar dados!\n");
       //     }
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
        
                    String codinome = partes[0];
                    int quantidade = Integer.parseInt(partes[1]);
                    double latitude = Double.parseDouble(partes[2]);
                    double longitude = Double.parseDouble(partes[3]);
        
                    equipes.addEquipe(new Equipe(codinome, quantidade, latitude, longitude));
                }
            } catch (Exception e) {
                areaMensagem.setText("Erro ao ler dados do arquivo: " + e.getMessage());
            }
        } catch (Exception e) {
            areaMensagem.setText("Erro ao abrir o arquivo: " + e.getMessage());
        }
        
    
     
    }
}