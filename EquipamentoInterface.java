import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EquipamentoInterface extends JFrame {
    private JTextField campoId, campoNome, campoCustoDia, campoCodinome;
    private JTextArea areaMensagem;
    private Equipamentos equipamentos;
    private JButton botaoAdicionar, botaoOrdenar, botaoListar;

    public EquipamentoInterface() {
        equipamentos = new Equipamentos();

        setTitle("Cadastro de Equipamentos");
        setSize(400, 300);
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
        botaoOrdenar = new JButton("Ordenar por ID");
        botaoListar = new JButton("Listar Equipamentos");

        botaoAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarEquipamento();
            }
        });

        botaoOrdenar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ordenarEquipamentos();
            }
        });

        botaoListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarEquipamentos();
            }
        });

        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoOrdenar);
        painelBotoes.add(botaoListar);

        add(painelBotoes, BorderLayout.CENTER);

        areaMensagem = new JTextArea(10, 30);
        areaMensagem.setEditable(false);
        JScrollPane painelRolagem = new JScrollPane(areaMensagem);
        add(painelRolagem, BorderLayout.SOUTH);

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
}
