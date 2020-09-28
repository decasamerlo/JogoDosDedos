package br.com.ufsc.cco.es.controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ConnectController {

	private static ConnectController connectController;
	private static final Logger logger = Logger.getLogger(getInstance().getClass().getName());

	public static ConnectController getInstance() {
		if (connectController == null) {
			connectController = new ConnectController();
		}
		return connectController;
	}

	public void createView() {
		// TODO Auto-generated method stub
		
	}

	public Component getPanel(JPanel cards) {
		JPanel panel = new JPanel(new BorderLayout());

		JButton button1 = new JButton("VOLTAR");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout)(cards.getLayout());
				cl.show(cards, "HOME");
			}
		});

		JPanel buttons = new JPanel();
		buttons.setBackground(Color.CYAN);
		buttons.add(button1);

		JLabel title = new JLabel("CONECTANDO", JLabel.CENTER);
		JLabel text = new JLabel("Solicitar informações do usuário, como servidor para acesso, nome/login, etc.", JLabel.CENTER);

		JPanel main = new JPanel(new BorderLayout());
		main.setBackground(Color.GREEN);
		main.add(BorderLayout.PAGE_START, title);
		main.add(BorderLayout.CENTER, text);

		panel.add(BorderLayout.PAGE_START, buttons);
		panel.add(BorderLayout.CENTER, main);

		return panel;
	}

}
