package br.com.ufsc.cco.es.controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainController {

	private static MainController mainController;
	private static final HomeController homeController = HomeController.getInstance();
	private static final GameController gameController = GameController.getInstance();
	private static final ConnectController connectController = ConnectController.getInstance();
	private static final Logger logger = Logger.getLogger(getInstance().getClass().getName());

	public static MainController getInstance() {
		if (mainController == null) {
			mainController = new MainController();
		}
		return mainController;
	}

	public void start() {
		logger.log(Level.INFO, "Iniciando jogo");
		JFrame frame = new JFrame("Jogo dos Dedos");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 700);
		frame.setLocationRelativeTo(null);

		JPanel mainPanel = new JPanel(new BorderLayout());
		
		JPanel cards = new JPanel(new CardLayout());
		cards.add(homeController.getPanel(cards, frame), "HOME");
		cards.add(gameController.getPanel(cards), "GAME");
		cards.add(connectController.getPanel(cards), "CONNECT");
		
		mainPanel.add(cards, BorderLayout.CENTER);

		frame.setContentPane(mainPanel);
		frame.setVisible(true);

	}

}
