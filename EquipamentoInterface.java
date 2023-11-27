import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

public class EquipamentoInterface extends JFrame {
    private JTextField campoId, campoNome, campoCustoDia, campoCodinome;
    private JTextArea areaMensagem;
    private Equipamentos equipamentos;
    private JButton botaoAdicionar, botaoListar, botaoLerArquivos;

    public EquipamentoInterface(Equipamentos equip) {
        super();
        equipamentos = equip;
        setTitle("Cadastro de Equipamentos");
        setSize(700, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel painelCampos = new JPanel();
        painelCampos.setLayout(new GridLayout(4, 2, 10, 10));

        campoId = new JTextField();
        campoNome = new JTextField();
        campoCustoDia = new JTextField();
        campoCodinome = new JTextField();

        painelCampos.add(new JLabel("ID:"));
        painelCampos.add(campoId);
        painelCampos.add(new JLabel("Nome:"));
        painelCampos.add(campoNome);
        painelCampos.add(new JLabel("Custo por Dia:"));
        painelCampos.add(campoCustoDia);
        painelCampos.add(new JLabel("Codinome:"));
        painelCampos.add(campoCodinome);

        add(painelCampos, BorderLayout.NORTH);

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        botaoAdicionar = new JButton("Adicionar Equipamento");
        botaoListar = new JButton("Listar Equipamentos");
        botaoLerArquivos = new JButton("Ler Arquivo de Equipamentos");

        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoListar);
        painelBotoes.add(botaoLerArquivos);

        add(painelBotoes, BorderLayout.CENTER);

        areaMensagem = new JTextArea(10, 30);
        areaMensagem.setEditable(false);
        JScrollPane painelRolagem = new JScrollPane(areaMensagem);
        add(painelRolagem, BorderLayout.SOUTH);

        // Adicione os ActionListener após criar os botões
        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarEquipamento();
            }
        });

        botaoListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarEquipamentos();
            }
        });

        botaoLerArquivos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Escolha o arquivo de eventos");
                    int result = fileChooser.showOpenDialog(EquipamentoInterface.this);
    
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    lerArquivoEquipamentos(selectedFile.getAbsolutePath());
                 } 
                } catch (Exception ex) {
                    areaMensagem.setText("Erro: Arquivo não encontrado.\n");
                }
            }
        });

        setVisible(true);
    }

    private void adicionarEquipamento() {
        try {
            int id = Integer.parseInt(campoId.getText());
            String nome = campoNome.getText();
            double custoDia = Double.parseDouble(campoCustoDia.getText());
            String codinome = campoCodinome.getText();

            Equipamento equipamento = new Equipamento(id, nome, custoDia, codinome);

            if (equipamentos.addEquipamento(equipamento)) {
                areaMensagem.setText("Equipamento adicionado com sucesso!\n");
                ordenarEquipamentos();
                limparCampos();
            } else {
                areaMensagem.setText("Erro: Equipamento com ID já existe!\n");
            }
        } catch (NumberFormatException e) {
            areaMensagem.setText("Erro: Verifique os campos de entrada.\n");
        }
    }

    private void ordenarEquipamentos() {
        equipamentos.ordenarEquipamentosPorIdCrescente();
        areaMensagem.setText("Equipamentos ordenados por ID.\n");
    }

    private void listarEquipamentos() {
        ArrayList<Equipamento> lista = equipamentos.getEquipamentos();
        areaMensagem.setText("Lista de Equipamentos:\n");
        for (Equipamento equipamento : lista) {
            areaMensagem.append("ID: " + equipamento.getId() + ", Nome: " + equipamento.getNome() + ", Custo por Dia: " + equipamento.getCustoDia() + ", Codinome: " + equipamento.getCodinome() + "\n");
        }
    }

    private void limparCampos() {
        campoId.setText("");
        campoNome.setText("");
        campoCustoDia.setText("");
        campoCodinome.setText("");
    }

    private void lerArquivoEquipamentos(String filePath) {
        try (BufferedReader streamEntrada = new BufferedReader(new FileReader(filePath))) {
            String linha;
            Scanner entrada = new Scanner(streamEntrada);
            entrada.nextLine(); // Pula a primeira linha
    
            while (entrada.hasNextLine()) {
                linha = entrada.nextLine().trim(); // Removendo espaços extras
                String[] partes = linha.split(";");
    
                int id = Integer.parseInt(partes[0].trim());
                String nome = partes[1].trim();
                double custoDiario = Double.parseDouble(partes[2].trim());
                String codinome = partes[3].trim();
                int tipo = Integer.parseInt(partes[4].trim());
    
                Equipamento equipamento = null;
    
                switch (tipo) {
                    case 1:
                        int capacidadeCombustivel = Integer.parseInt(partes[5].trim());
                        equipamento = new Barco(id, nome, custoDiario, codinome, capacidadeCombustivel);
                        break;
                    case 2:
                        double carga = Double.parseDouble(partes[5].trim());
                        equipamento = new CaminhaoTanque(id, nome, custoDiario, codinome, carga);
                        break;
                    case 3:
                        String tipoCombustivel = partes[5].trim();
                        double cargaEscavadeira = Double.parseDouble(partes[6].trim());
                        equipamento = new Escavadeira(id, nome, custoDiario, codinome, tipoCombustivel, cargaEscavadeira);
                        break;
                    default:
                        System.out.println("Tipo de equipamento desconhecido.");
                }
    
                if (equipamento != null) {
                    equipamentos.addEquipamento(equipamento);
                }
            }
            areaMensagem.setText("Equipamentos lidos do arquivo com sucesso!\n");
        } catch (IOException e) {
            areaMensagem.setText("Erro ao ler dados do arquivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            areaMensagem.setText("Erro ao converter dados: " + e.getMessage());
        }
    }
    
    }
    
