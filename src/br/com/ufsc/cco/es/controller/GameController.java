package br.com.ufsc.cco.es.controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameController {

	private static GameController gameController;
	private static final Logger logger = Logger.getLogger(getInstance().getClass().getName());

	public static GameController getInstance() {
		if (gameController == null) {
			gameController = new GameController();
		}
		return gameController;
	}

	public Component getPanel(JPanel cards) {
		JPanel panel = new JPanel(new BorderLayout());

		JButton button1 = new JButton("DESISTIR");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) (cards.getLayout());
				cl.show(cards, "HOME");
			}
		});

		JPanel buttons = new JPanel();
		buttons.setBackground(Color.CYAN);
		buttons.add(button1);

		JLabel label = new JLabel("SELECIONE A MÃO QUE DESEJA UTILIZAR E APÓS SELECIONE A MÃO QUE DESEJA ATACAR");
		label.setHorizontalAlignment(JLabel.CENTER);

		try {
			BufferedImage imgLeftHand = ImageIO.read(new File("src/resources/images/left_one.jpg"));
			JButton btnLeftHand = new JButton(new ImageIcon(imgLeftHand));
			btnLeftHand.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					logger.log(Level.SEVERE, "Mão esquerda do jogador selecionada.", e);
			    	JOptionPane.showMessageDialog(null, "Agora selecione a mão que deseja atacar.", "Atacar!", JOptionPane.INFORMATION_MESSAGE);
				}
			});

			BufferedImage imgRightHand = ImageIO.read(new File("src/resources/images/right_one.jpg"));
			JButton btnRightHand = new JButton(new ImageIcon(imgRightHand));
			btnRightHand.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					logger.log(Level.SEVERE, "Mão direita do jogador selecionada.", e);
					JOptionPane.showMessageDialog(null, "Agora selecione a mão que deseja atacar.", "Atacar!", JOptionPane.INFORMATION_MESSAGE);
				}
			});

			BufferedImage imgOpponentLeftHand = ImageIO.read(new File("src/resources/images/opp_left_one.jpg"));
			JButton btnOponentLeftHand = new JButton(new ImageIcon(imgOpponentLeftHand));
			btnOponentLeftHand.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					logger.log(Level.SEVERE, "Mão esquerda do oponente selecionada.", e);
					JOptionPane.showMessageDialog(null, "Agora aguarde o oponente realizar sua jogada.", "Atacou!", JOptionPane.INFORMATION_MESSAGE);
				}
			});

			BufferedImage imgOpponentRightHand = ImageIO.read(new File("src/resources/images/opp_right_one.jpg"));
			JButton btnOponentRightHand = new JButton(new ImageIcon(imgOpponentRightHand));
			btnOponentRightHand.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					logger.log(Level.SEVERE, "Mão direita do oponente selecionada.", e);
					JOptionPane.showMessageDialog(null, "Agora aguarde o oponente realizar sua jogada.", "Atacou!", JOptionPane.INFORMATION_MESSAGE);
				}
			});
			
			JPanel northPanel = new JPanel(new BorderLayout());
			northPanel.add(btnOponentLeftHand, BorderLayout.EAST);
			northPanel.add(btnOponentRightHand, BorderLayout.WEST);
			
			JPanel southPanel = new JPanel(new BorderLayout());
			southPanel.add(btnRightHand, BorderLayout.EAST);
			southPanel.add(btnLeftHand, BorderLayout.WEST);
			
			JPanel main = new JPanel(new BorderLayout());
			main.add(northPanel, BorderLayout.NORTH);
			main.add(southPanel, BorderLayout.SOUTH);
			main.setBackground(Color.GREEN);
			main.add(label);
			
			panel.add(BorderLayout.PAGE_START, buttons);
			panel.add(BorderLayout.CENTER, main);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		return panel;
	}

}
