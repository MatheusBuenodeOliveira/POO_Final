import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.*;

public class Menu extends JFrame {
    Eventos eventos = new Eventos();
    Atendimentos atendimentos = new Atendimentos();
    Equipes equipes = new Equipes();
    Equipamentos equipamentos = new Equipamentos();
    
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
                new GUI(eventos); // Abre a interface de cadastro de eventos
            }
        });

        // Botão para a interface de cadastro de equipes
        JButton btnEquipes = new JButton("Cadastrar Equipes");
        btnEquipes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Interface(equipes); // Abre a interface de cadastro de equipes
            }
        });

        // Botão para a interface de cadastro de equipamentos
        JButton btnEquipamento = new JButton("Cadastrar Equipamentos");
        btnEquipamento.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EquipamentoInterface(equipamentos);
            }
        });

       

         // Botão para a interface de cadastro de equipamentos
        JButton btnAtendimentos = new JButton("Cadastrar Atendimentos");
        btnAtendimentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AtendimentoInterface(atendimentos,eventos);
            }
        });

        JButton btnTeste = new JButton("testar");
        btnTeste.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Evento evento : eventos.getEventos()) {
                    System.out.println(evento.toString());
                }
            }
        });

        JButton btnSaveAll = new JButton("Salvar CSV");
        btnSaveAll.addActionListener(e -> {
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
            
           
            
        });

        // Adicionando botões ao JFrame
        add(btnEventos);
        add(btnEquipes);
        add(btnEquipamento);
        add(btnAtendimentos);
        add(btnTeste);
        add(btnSaveAll);
    
    
    }

    
    }

