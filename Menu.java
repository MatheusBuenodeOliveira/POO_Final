import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {

    public Menu() {
        // Configuração inicial da janela
        setTitle("Menu Principal");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Layout para os botões
        setLayout(new FlowLayout());

        // Botão para a interface de cadastro de eventos
        JButton btnEventos = new JButton("Cadastrar Eventos");
        btnEventos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUI(); // Abre a interface de cadastro de eventos
            }
        });

        // Botão para a interface de cadastro de equipes
        JButton btnEquipes = new JButton("Cadastrar Equipes");
        btnEquipes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Interface(); // Abre a interface de cadastro de equipes
            }
        });

        // Botão para a interface de cadastro de equipamentos
        JButton btnEquipamento = new JButton("Cadastrar Equipamentos");
        btnEquipes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EquipamentoInterface();
            }
        });

        // Adicionando botões ao JFrame
        add(btnEventos);
        add(btnEquipes);
        add(btnEquipamento);
    }

    
    }

