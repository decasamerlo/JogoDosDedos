package br.com.ufsc.cco.es.controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.ufsc.cco.es.model.Jogador;
import br.com.ufsc.cco.es.rede.AtorNetgames;

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

	public Component getPanel(JFrame frame) {
		JPanel panel = new JPanel();

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
				String jogadores = JOptionPane.showInputDialog(null, "Digite o número de jogadores", "Jogadores",
						JOptionPane.QUESTION_MESSAGE);
				Integer nJogadores = new Integer(jogadores);
				if (nJogadores > 1 && nJogadores <= 5) {
					JOptionPane.showMessageDialog(null, AtorNetgames.getInstance().iniciarPartida(nJogadores));
				} else {
					JOptionPane.showMessageDialog(null, "O número de jogadores deve ser de 2 a 5!");
				}
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
		panel.add(buttonJogar);
		panel.add(buttonDesconectar);
		panel.add(buttonSair);

		return panel;
	}

}
