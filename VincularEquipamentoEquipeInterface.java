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
        setSize(400, 300);
        setLayout(new BorderLayout());

        inicializarComponentes();
        configurarEventos();
        setVisible(true);
    }

    private void inicializarComponentes() {
        comboEquipamentos = new JComboBox<>();
        for (Equipamento equipamento : equipamentos.getEquipamentos()) {
            comboEquipamentos.addItem(equipamento);
        }

        comboEquipes = new JComboBox<>();
        for (Equipe equipe : equipes.getEquipes()) {
            comboEquipes.addItem(equipe);
        }

        btnVincular = new JButton("Vincular");

        JPanel painel = new JPanel(new GridLayout(3, 1));
        painel.add(comboEquipamentos);
        painel.add(comboEquipes);
        painel.add(btnVincular);

        add(painel, BorderLayout.CENTER);

        areaMensagem = new JTextArea();
        areaMensagem.setEditable(false);
        add(new JScrollPane(areaMensagem), BorderLayout.SOUTH);
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

