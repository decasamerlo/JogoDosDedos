package br.com.ufsc.cco.es.controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.ufsc.cco.es.model.Arena;
import br.com.ufsc.cco.es.model.Jogador;
import br.com.ufsc.cco.es.rede.AtorNetgames;

public class MainController {

	private static MainController mainController;
	private static final HomeController homeController = HomeController.getInstance();
	private static final GameController gameController = GameController.getInstance();
	private static final ConnectController connectController = ConnectController.getInstance();
	private static final Logger logger = Logger.getLogger(getInstance().getClass().getName());
	private Arena arena = new Arena();
	private JFrame frame = new JFrame("Jogo dos Dedos");
	
	public static MainController getInstance() {
		if (mainController == null) {
			mainController = new MainController();
		}
		return mainController;
	}

	public void start() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);

		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel cards = new JPanel(new CardLayout());
		cards.add(homeController.getPanel(cards, frame, arena), "HOME");
		cards.add(gameController.getPanel(cards), "GAME");
		cards.add(connectController.getPanel(cards), "CONNECT");
		
		mainPanel.add(cards, BorderLayout.CENTER);

		frame.setContentPane(mainPanel);
		frame.setVisible(true);

	}

	public void conectarJogador(String servidor, Jogador jogador) {
		JOptionPane.showMessageDialog(null, AtorNetgames.getInstance().conectar(servidor, jogador.getName()));
		arena.getMesa().add(jogador);
		start();
	}

	public void desconectar() {
		AtorNetgames.getInstance().desconectar();
		JOptionPane.showMessageDialog(null, "Desconectado com sucesso!");
		arena.setMesa(new ArrayList<>());
		start();
	}

	public boolean isConectado() {
		if (arena.getMesa() != null && arena.getMesa().size() != 0) {
			return true;
		}
		return false;
	}

}
