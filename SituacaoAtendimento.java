import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SituacaoAtendimento extends JFrame {
    private JTextField codigoAtendimentoField;
    private JComboBox<String> novaSituacaoComboBox;
    private JButton alterarButton;
    private JTextArea resultadoTextArea;

    public SituacaoAtendimento(Atendimentos atendimentos) {
        setTitle("Alterar Situação de Atendimento");
        setSize(400, 300);
        setLayout(new BorderLayout());

        // Painel para entrada de dados
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Código do Atendimento:"));
        codigoAtendimentoField = new JTextField();
        inputPanel.add(codigoAtendimentoField);
        
        inputPanel.add(new JLabel("Nova Situação:"));
        novaSituacaoComboBox = new JComboBox<>(new String[]{"EXECUTANDO", "FINALIZADO", "CANCELADO", "PENDENTE" });
        inputPanel.add(novaSituacaoComboBox);

        alterarButton = new JButton("Alterar");
        inputPanel.add(alterarButton);

        JButton botaoFechar = new JButton("Voltar");
        inputPanel.add(botaoFechar);

        

        add(inputPanel, BorderLayout.NORTH);

        // Área de texto para mostrar resultados
        resultadoTextArea = new JTextArea();
        resultadoTextArea.setEditable(false);
        add(new JScrollPane(resultadoTextArea), BorderLayout.CENTER);

        // Adicionando ação ao botão
        alterarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alterarSituacao(atendimentos);
            }
        });

        botaoFechar.addActionListener(e -> dispose());
        setVisible(true);
    }

    private void alterarSituacao(Atendimentos atendimentos) {
        String codigo = codigoAtendimentoField.getText();
        String novaSituacao = (String) novaSituacaoComboBox.getSelectedItem();
        boolean achou = false;

        for (Atendimento atendimento : atendimentos.getAtendimentos()) {
            if(atendimento.getStatus().equalsIgnoreCase("finalizado")){
                resultadoTextArea.setText("Atendimento já finalizado");
                achou = true;
            }
            else if(atendimento.getCod() == Integer.parseInt(codigo) ){
                atendimento.toString2();
                atendimento.setStatus(novaSituacao);
                resultadoTextArea.setText("Situação alterada com sucesso");
                achou = true;

            }

        }
        
        if(!achou)
         resultadoTextArea.setText("Atendimento não encontrado");
        
    }

    
    }
