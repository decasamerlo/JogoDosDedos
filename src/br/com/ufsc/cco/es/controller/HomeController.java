package br.com.ufsc.cco.es.controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.ufsc.cco.es.model.Arena;
import br.com.ufsc.cco.es.model.Jogador;
import br.com.ufsc.cco.es.rede.AtorNetgames;

public class HomeController {

	private static HomeController homeController;
	private static final Logger logger = Logger.getLogger(getInstance().getClass().getName());

	public static HomeController getInstance() {
		if (homeController == null) {
			homeController = new HomeController();
		}
		return homeController;
	}

	public Component getPanel(JPanel cards, JFrame frame, Arena arena) {
		JPanel panel = new JPanel();

		JButton buttonConectar = new JButton("CONECTAR");
		buttonConectar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nome = JOptionPane.showInputDialog(null, "Digite seu nome", "Nome", JOptionPane.QUESTION_MESSAGE);
				if (nome != null) {
					Jogador jogador = new Jogador(nome);
					String servidor = JOptionPane.showInputDialog(null, "Digite o servidor", "Servidor", JOptionPane.QUESTION_MESSAGE);
					MainController.getInstance().conectarJogador(servidor, jogador);
				}
			}
		});

		JButton buttonDesconectar = new JButton("DESCONECTAR");
		buttonDesconectar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MainController.getInstance().desconectar();
			}
		});

		JButton buttonJogar = new JButton("JOGAR");
		buttonJogar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String jogadores = JOptionPane.showInputDialog(null, "Digite o número de jogadores", "Jogadores", JOptionPane.QUESTION_MESSAGE);
				JOptionPane.showMessageDialog(null, AtorNetgames.getInstance().iniciarPartida(new Integer(jogadores)));
//				CardLayout cl = (CardLayout)(cards.getLayout());
//				cl.show(cards, "GAME");
			}
		});

		JButton buttonSair = new JButton("SAIR");
		buttonSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.log(Level.INFO, "Saindo...");
				frame.dispose();
			}
		});
		
		panel.setBackground(Color.CYAN);
		if (MainController.getInstance().isConectado()) {
			panel.add(buttonJogar);
			panel.add(buttonDesconectar);
		} else {
			panel.add(buttonConectar);
		}
		panel.add(buttonSair);

		return panel;
	}

}
