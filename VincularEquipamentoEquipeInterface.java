import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VincularEquipamentoEquipeInterface extends JFrame {
    private JComboBox<Equipamento> comboEquipamentos;
    private JComboBox<Equipe> comboEquipes;
    private JButton btnVincular;
    private JTextArea areaMensagem;

    private Equipamentos equipamentos;
    private Equipes equipes;

    public VincularEquipamentoEquipeInterface(Equipamentos equipamentos, Equipes equipes) {
        this.equipamentos = equipamentos;
        this.equipes = equipes;

        setTitle("Vincular Equipamento a Equipe");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        inicializarComponentes();
        configurarEventos();
        pack(); // Ajusta o tamanho da janela aos componentes
        setLocationRelativeTo(null); // Centraliza a janela
        setVisible(true);
    }

    private void inicializarComponentes() {
        // Comboboxes e botão de vincular
        comboEquipamentos = new JComboBox<>();
        comboEquipes = new JComboBox<>();
        popularComboboxes();
        
        btnVincular = new JButton("Vincular");

        JPanel painelComboboxes = new JPanel(new GridLayout(3, 1));
        painelComboboxes.add(comboEquipamentos);
        painelComboboxes.add(comboEquipes);
        painelComboboxes.add(btnVincular);

        add(painelComboboxes, BorderLayout.CENTER);

        // Área de mensagem
        areaMensagem = new JTextArea(4, 20);
        areaMensagem.setEditable(false);
        add(new JScrollPane(areaMensagem), BorderLayout.SOUTH);

        // Botão de fechar
        JButton btnFechar = new JButton("Voltar");
        btnFechar.addActionListener(e -> dispose());
        add(btnFechar, BorderLayout.NORTH);
    }

    private void popularComboboxes() {
        for (Equipamento equipamento : equipamentos.getEquipamentos()) {
            comboEquipamentos.addItem(equipamento);
        }
        for (Equipe equipe : equipes.getEquipes()) {
            comboEquipes.addItem(equipe);
        }
    }

    private void configurarEventos() {
        btnVincular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Equipamento equipamentoSelecionado = (Equipamento) comboEquipamentos.getSelectedItem();
                Equipe equipeSelecionada = (Equipe) comboEquipes.getSelectedItem();

                if (equipamentoSelecionado != null && equipeSelecionada != null) {
                    if (!equipeSelecionada.possuiEquipamento(equipamentoSelecionado.getId()) && equipamentoSelecionado.getEquipe() == null) {
                        equipeSelecionada.addEquipamento(equipamentoSelecionado);
                        equipamentoSelecionado.setEquipe(equipeSelecionada);
                        areaMensagem.setText("Equipamento vinculado com sucesso à equipe " + equipeSelecionada.getCodinome());
                    } else if (equipamentoSelecionado.getEquipe() != null) {
                        areaMensagem.setText("Erro: Equipamento já vinculado a uma equipe.");
                    } else {
                        areaMensagem.setText("Erro: Equipamento já vinculado à equipe " + equipeSelecionada.getCodinome());
                    }
                }
            }
        });
    }
}

