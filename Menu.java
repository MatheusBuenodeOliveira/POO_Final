import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.*;

public class Menu extends JFrame {
    Eventos eventos = new Eventos();
    Atendimentos atendimentos = new Atendimentos();
    Equipes equipes = new Equipes();
    Equipamentos equipamentos = new Equipamentos();
    JTextArea textArea = new JTextArea(20, 40);
    
    public Menu() {
        // Configuração inicial da janela
        configurarJanela();

        // Adicionando componentes à janela
        adicionarComponentes();

        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Menu Principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(1550, 800);
        setLocationRelativeTo(null);
    }

    private void adicionarComponentes() {
        // Painel para os botões
        JPanel painelBotoes = new JPanel(new FlowLayout());
        adicionarBotoes(painelBotoes);

        // Área de texto com rolagem
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        add(painelBotoes, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void adicionarBotoes(JPanel painel) {
        // Definindo um GridLayout para o painel de botões
        int numeroDeLinhas = 3;  // Número de linhas no GridLayout
        int numeroDeColunas = 4; // Número de colunas no GridLayout
        painel.setLayout(new GridLayout(numeroDeLinhas, numeroDeColunas, 10, 10));

        // Botões e suas ações
        JButton btnEventos = new JButton("Cadastrar Eventos");
        btnEventos.addActionListener(e -> new GUI(eventos));

        JButton btnEquipes = new JButton("Cadastrar Equipes");
        btnEquipes.addActionListener(e -> new Interface(equipes));

        JButton btnEquipamento = new JButton("Cadastrar Equipamentos");
        btnEquipamento.addActionListener(e -> new EquipamentoInterface(equipamentos));
    
        JButton btnAtendimentos = new JButton("Cadastrar Atendimentos");
        btnAtendimentos.addActionListener(e -> new AtendimentoInterface(atendimentos,eventos));

        JButton btnSaveAll = new JButton("Salvar CSV");
        btnSaveAll.addActionListener(e -> salvarDadosCSV());
        
        JButton btnRelatorioGeral = new JButton("Relatório geral");
        btnRelatorioGeral.addActionListener(e -> gerarRelatorioGeral());
        
        JButton btnVincularEquipamentos = new JButton("Vincular Equipamentos a Equipes");
        btnVincularEquipamentos.addActionListener(e -> new VincularEquipamentoEquipeInterface(equipamentos, equipes));

        JButton btnVincularEquipes = new JButton("Vincular Equipes a Atendimentos");
        btnVincularEquipes.addActionListener(e -> alocarAtendimentosAux());

        JButton btnConsultarAtendimentos = new JButton("Mostra todos os atendimentos");
        btnConsultarAtendimentos.addActionListener(e -> consultarAtendimentos());

        JButton btnAlterarSituacaoAtendimento = new JButton("Alterar Situação Atendimento");
        btnAlterarSituacaoAtendimento.addActionListener(e -> new SituacaoAtendimento(atendimentos));

        JButton btnFecharPrograma = new JButton("Fechar");
        btnFecharPrograma.addActionListener(e -> System.exit(0));

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.addActionListener(e -> textArea.setText(""));
        
        // Adicionando botões ao painel
        painel.add(btnEventos);
        painel.add(btnEquipes);
        painel.add(btnEquipamento);
        painel.add(btnAtendimentos);
        painel.add(btnSaveAll);
        painel.add(btnRelatorioGeral);
        painel.add(btnVincularEquipamentos);
        painel.add(btnVincularEquipes);
        painel.add(btnConsultarAtendimentos);
        painel.add(btnAlterarSituacaoAtendimento);
        painel.add(btnLimpar);
        painel.add(btnFecharPrograma);

        // Se houver menos botões do que espaços no grid, adicione placeholders vazios
        for (int i = painel.getComponentCount(); i < (numeroDeLinhas * numeroDeColunas); i++) {
            painel.add(new JLabel(""));
        }
    }

    private void salvarDadosCSV(){
        try{
            File file = new File("dadosout.txt");
            BufferedWriter br = new BufferedWriter(new FileWriter(file));
            for (Evento evento : eventos.getEventos()) {
                br.write(evento.toCSVString()+"\n");
            }
            for (Atendimento atendimento : atendimentos.getAtendimentos()) {
                br.write(atendimento.toCSVString()+"\n");
            }
            for (Equipamento equipamento : equipamentos.getEquipamentos()) {
                br.write(equipamento.toCSVString()+"\n");
            }
            for (Equipe equipe : equipes.getEquipes()) {
                br.write(equipe.toCSVString()+"\n");
            }
        
            br.close();

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }  
    }

    private void gerarRelatorioGeral(){
        StringBuilder sb = new StringBuilder();
        
        try {
            sb.append("Eventos: \n");
            for (Evento evento : eventos.getEventos()) {
                sb.append(evento.toCSVString()).append("\n");
            }
            sb.append("\nAtendimentos: \n");
            for (Atendimento atendimento : atendimentos.getAtendimentos()) {
                sb.append(atendimento.toCSVString()).append("\n");
            }
            sb.append("\nEquipamentos: \n");
            for (Equipamento equipamento : equipamentos.getEquipamentos()) {
                sb.append(equipamento.toCSVString()).append("\n");
            }
            sb.append("\nEquipes: \n");
            for (Equipe equipe : equipes.getEquipes()) {
                sb.append(equipe.toCSVString()).append("\n");
            }
    
            textArea.setText(sb.toString()); // Atualiza a JTextArea com os dados
    
        } catch (Exception ex) {
            ex.printStackTrace();
            textArea.setText("Erro ao gerar relatório: " + ex.getMessage());
        }
    }
    
    private boolean alocarAtendimentos() {
        ArrayList<Atendimento> atendimentosRemovidos = new ArrayList<>();
        boolean mudanca = false;
    
        for (Atendimento atendimento : atendimentos.getAtendimentosFila()) {
            if (atendimento.getEquipe() == null) {
                boolean equipeAlocada = false;
                boolean equipeProxima = false;
    
                for (Equipe aux : equipes.getEquipes()) {
                    if (aux.isDisponivel()) {
                        double dist = atendimento.calculaDistancia(aux, atendimento.getEvento());
                        if (dist <= 5000) {
                            equipeProxima = true;
                            atendimento.setEquipe(aux);
                            aux.addAtendimento(atendimento);
                            atendimento.setStatus("EXECUTANDO");
                            atendimento.setDeslocamento(dist);
                            atendimentosRemovidos.add(atendimento);
                            equipeAlocada = true;
                            mudanca = true;
                            break;
                        }
                    }
                }
    
                if (!equipeAlocada) {
                    if (equipeProxima) {
                        // Adiciona novamente ao final da fila
                        atendimentos.addAtendimentoFila(atendimento);
                    } else {
                        atendimento.setStatus("CANCELADO");
                    }
                    atendimentosRemovidos.add(atendimento);
                    mudanca = true;
                }
            }
        }
    
        // Remove os atendimentos processados da fila original
        atendimentos.getAtendimentosFila().removeAll(atendimentosRemovidos);
    
        if (!mudanca) {
            return false;
        }
    
        return true;
    }

    private void alocarAtendimentosAux(){
        if (alocarAtendimentos()){
            textArea.setText("Atendimentos alocados com sucesso!");
        } else {
            textArea.setText("Não foi possível alocar todos os atendimentos!");
        }
    }

    private void consultarAtendimentos(){
        StringBuilder sb = new StringBuilder();
        
        if(atendimentos.getAtendimentos().isEmpty()){
            textArea.setText("Atendimentos não encontrados");
        } else {
            try {
                sb.append("\nAtendimentos: \n");
                for (Atendimento atendimento : atendimentos.getAtendimentos()) {
                    sb.append(atendimento.toString2()).append("\n");
                }
                        
                textArea.setText(sb.toString()); // Atualiza a JTextArea com os dados
        
            } catch (Exception ex) {
                ex.printStackTrace();
                textArea.setText("Erro ao mostrar atendimentos: " + ex.getMessage());
            }
        }
    }
}

