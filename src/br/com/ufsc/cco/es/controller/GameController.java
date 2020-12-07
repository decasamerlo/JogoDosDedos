package br.com.ufsc.cco.es.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.ufsc.cco.es.model.Jogador;
import br.com.ufsc.cco.es.model.Mao;
import br.com.ufsc.cco.es.model.Move;
import br.com.ufsc.cco.es.rede.AtorNetgames;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;

public class GameController {

	private static GameController gameController;
	private static final Logger logger = Logger.getLogger(getInstance().getClass().getName());
	private JButton btnLeftHand;
	private JButton btnRightHand;
	private HashMap<Integer, JButton> listMaoDireitaOponentes = new HashMap<>();
	private HashMap<Integer, JButton> listMaoEsquerdaOponentes = new HashMap<>();

	public static GameController getInstance() {
		if (gameController == null) {
			gameController = new GameController();
		}
		return gameController;
	}

	public Component getPanel() {
		JPanel panel = new JPanel(new BorderLayout());

		JButton button1 = new JButton("DESISTIR");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Concedendo");
			}
		});

		JPanel buttons = new JPanel();
		buttons.setBackground(Color.CYAN);
		buttons.add(button1);

		JLabel label = new JLabel("SELECIONE A MÃO QUE DESEJA UTILIZAR E APÓS SELECIONE A MÃO QUE DESEJA ATACAR");
		label.setHorizontalAlignment(JLabel.CENTER);

		try {
			BufferedImage imgLeftHand = ImageIO.read(new File("src/resources/images/left_1.jpg"));
			btnLeftHand = new JButton(new ImageIcon(imgLeftHand));
			btnLeftHand.setBackground(Color.GRAY);
			btnLeftHand.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (MainController.getInstance().getJogadorLocal().isTurno()) {
						if (MainController.getInstance().getMaoSelecionada() == null) {
							MainController.getInstance().selecionaMaoEsquerda();
						} else if (MainController.getInstance().getMaoSelecionada() == MainController.getInstance()
								.getJogadorLocal().getMaoDireita()) {
							Mao maoOrigem = MainController.getInstance().getMaoSelecionada();
							Mao maoDestino = MainController.getInstance().getJogadorLocal().getMaoEsquerda();
							try {
								AtorNetgames.getInstance().enviaJogada(new Move(maoOrigem, maoDestino));
							} catch (NaoJogandoException e1) {
								e1.printStackTrace();
							}
							MainController.getInstance().getArena().efetuaDivisaoDedos(maoOrigem, maoDestino);
							MainController.getInstance().getArena().passarTurno();
							MainController.getInstance().limpaMaoSelecionada();
						} else {
							MainController.getInstance().limpaMaoSelecionada();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Aguarde sua vez para jogar. Agora é a vez de "
								+ MainController.getInstance().getArena().getDaVez().getNome());
					}
					refresh();
				}
			});
			if (!MainController.getInstance().getJogadorLocal().getMaoEsquerda().isViva()) {
				btnLeftHand.setVisible(false);
			}

			BufferedImage imgRightHand = ImageIO.read(new File("src/resources/images/right_1.jpg"));
			btnRightHand = new JButton(new ImageIcon(imgRightHand));
			btnRightHand.setBackground(Color.GRAY);
			btnRightHand.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (MainController.getInstance().getJogadorLocal().isTurno()) {
						if (MainController.getInstance().getMaoSelecionada() == null) {
							MainController.getInstance().selecionaMaoDireita();
						} else if (MainController.getInstance().getMaoSelecionada() == MainController.getInstance()
								.getJogadorLocal().getMaoEsquerda()) {
							Mao maoOrigem = MainController.getInstance().getMaoSelecionada();
							Mao maoDestino = MainController.getInstance().getJogadorLocal().getMaoDireita();
							try {
								AtorNetgames.getInstance().enviaJogada(new Move(maoOrigem, maoDestino));
							} catch (NaoJogandoException e1) {
								e1.printStackTrace();
							}
							MainController.getInstance().getArena().efetuaDivisaoDedos(maoOrigem, maoDestino);
							MainController.getInstance().getArena().passarTurno();
							MainController.getInstance().limpaMaoSelecionada();
						} else {
							MainController.getInstance().limpaMaoSelecionada();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Aguarde sua vez para jogar. Agora é a vez de "
								+ MainController.getInstance().getArena().getDaVez().getNome());
					}
					refresh();
				}
			});
			if (!MainController.getInstance().getJogadorLocal().getMaoDireita().isViva()) {
				btnRightHand.setVisible(false);
			}

			JLabel labelNome = new JLabel(MainController.getInstance().getJogadorLocal().getNome());
			label.setHorizontalAlignment(JLabel.CENTER);

			JPanel northPanel = new JPanel();

			for (Jogador jogador : MainController.getInstance().getArena().getMesa()) {
				if (jogador.getNome() != MainController.getInstance().getJogadorLocal().getNome()) {
					JPanel oponente = new JPanel();
					BufferedImage imgOpponentLeftHand = ImageIO.read(new File("src/resources/images/opp_left_1.jpg"));
					JButton btnOponentLeftHand = new JButton(new ImageIcon(imgOpponentLeftHand));
					btnOponentLeftHand.setBackground(Color.GRAY);
					btnOponentLeftHand.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							logger.log(Level.SEVERE, "Mão esquerda do oponente selecionada.", e);
							JOptionPane.showMessageDialog(null, "Agora aguarde o oponente realizar sua jogada.",
									"Atacou!", JOptionPane.INFORMATION_MESSAGE);
						}
					});
					listMaoEsquerdaOponentes.put(jogador.getOrdem(), btnOponentLeftHand);

					BufferedImage imgOpponentRightHand = ImageIO
							.read(new File("src/resources/images/opp_right_1.jpg"));
					JButton btnOponentRightHand = new JButton(new ImageIcon(imgOpponentRightHand));
					btnOponentRightHand.setBackground(Color.GRAY);
					btnOponentRightHand.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (MainController.getInstance().getJogadorLocal().isTurno()) {
								if (MainController.getInstance().getMaoSelecionada() == null) {
									JOptionPane.showMessageDialog(null, "Selecione a sua mão primeiro");
								} else {
									try {
										AtorNetgames.getInstance().enviaJogada(null);
									} catch (NaoJogandoException e1) {
										e1.printStackTrace();
									}
									MainController.getInstance().getArena().passarTurno();
								}
							} else {
								JOptionPane.showMessageDialog(null, "Aguarde sua vez para jogar");
							}
							refresh();
						}
					});
					listMaoDireitaOponentes.put(jogador.getOrdem(), btnOponentRightHand);

					JLabel labelNomeAdversario = new JLabel(jogador.getNome());
					oponente.add(btnOponentRightHand);
					oponente.add(labelNomeAdversario);
					oponente.add(btnOponentLeftHand);
					northPanel.add(oponente);
				}
			}

			JPanel southPanel = new JPanel(new BorderLayout());
			JPanel wrapper = new JPanel();
			wrapper.add(btnLeftHand);
			wrapper.add(labelNome);
			wrapper.add(btnRightHand);
			southPanel.add(wrapper, BorderLayout.CENTER);

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

	public void refresh() {
		if (MainController.getInstance().getJogadorLocal().getMaoEsquerda()
				.equals(MainController.getInstance().getMaoSelecionada())) {
			btnLeftHand.setBackground(Color.GREEN);
		} else {
			btnLeftHand.setBackground(Color.GRAY);
		}

		if (MainController.getInstance().getJogadorLocal().getMaoDireita()
				.equals(MainController.getInstance().getMaoSelecionada())) {
			btnRightHand.setBackground(Color.GREEN);
		} else {
			btnRightHand.setBackground(Color.GRAY);
		}

		try {
			int dedosEsquerda = MainController.getInstance().getJogadorLocal().getMaoEsquerda().getDedos();
			BufferedImage imgLeftHand = ImageIO.read(new File("src/resources/images/left_"+dedosEsquerda+".jpg"));
			btnLeftHand.setIcon(new ImageIcon(imgLeftHand));
	
			int dedosDireita = MainController.getInstance().getJogadorLocal().getMaoDireita().getDedos();
			BufferedImage imgRightHand = ImageIO.read(new File("src/resources/images/right_"+dedosDireita+".jpg"));
			btnRightHand.setIcon(new ImageIcon(imgRightHand));
		
			for (Entry<Integer, JButton> entry : listMaoEsquerdaOponentes.entrySet()) {
				Jogador j = MainController.getInstance().getArena().getJogador(entry.getKey());
				int dedosOppEsquerda = j.getMaoEsquerda().getDedos();
				System.out.println("atualizando imagem com dedos da mao esquerda: " + dedosOppEsquerda + " do jogador " + entry.getKey() + " com nome " + j.getNome());
				BufferedImage imgOppLeftHand = ImageIO.read(new File("src/resources/images/opp_left_"+dedosOppEsquerda+".jpg"));
				entry.getValue().setIcon(new ImageIcon(imgOppLeftHand));
			}

			for (Entry<Integer, JButton> entry : listMaoDireitaOponentes.entrySet()) {
				Jogador j = MainController.getInstance().getArena().getJogador(entry.getKey());
				int dedosOppDireita = j.getMaoDireita().getDedos();
				System.out.println("atualizando imagem com dedos da mao direita: " + dedosOppDireita);
				BufferedImage imgOppRightHand = ImageIO.read(new File("src/resources/images/opp_right_"+dedosOppDireita+".jpg"));
				entry.getValue().setIcon(new ImageIcon(imgOppRightHand));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
