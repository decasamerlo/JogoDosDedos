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
import javax.swing.JPanel;

public class HomeController {

	private static HomeController homeController;
	private static final Logger logger = Logger.getLogger(getInstance().getClass().getName());

	public static HomeController getInstance() {
		if (homeController == null) {
			homeController = new HomeController();
		}
		return homeController;
	}

	public Component getPanel(JPanel cards, JFrame frame) {
		JPanel panel = new JPanel();

		JButton button1 = new JButton("CONECTAR");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, "CONNECT");
			}
		});

		JButton button2 = new JButton("JOGAR");
		button2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, "GAME");
			}
		});

		JButton button3 = new JButton("SAIR");
		button3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				logger.log(Level.INFO, "Saindo...");
				frame.dispose();
			}
		});

		panel.setBackground(Color.CYAN);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);

		return panel;
	}

}
