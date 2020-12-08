package br.com.ufsc.cco.es.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

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
	private JButton btnLeftHand;
	private JButton btnRightHand;
	private JLabel label;
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

		// abaixo esta implementado o diagrama de sequencia "conceder"
		JButton button1 = new JButton("DESISTIR");
		button1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (MainController.getInstance().getJogadorLocal().isTurno()) {
					try {
						AtorNetgames.getInstance().enviaJogada(new Move(null, null));
						MainController.getInstance().getArena()
								.efetuaConcessao(MainController.getInstance().getJogadorLocal());
						MainController.getInstance().getArena().passarTurno();
						refresh();
					} catch (NaoJogandoException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		JPanel buttons = new JPanel();
		buttons.setBackground(Color.CYAN);
		buttons.add(button1);

		label = new JLabel("SELECIONE A MÃO QUE DESEJA UTILIZAR E APÓS SELECIONE A MÃO QUE DESEJA ATACAR");
		if (!MainController.getInstance().getJogadorLocal().isTurno()) {
			label.setText(
					"AGUARDANDO JOGADA DO JOGADOR " + MainController.getInstance().getArena().getDaVez().getNome());
		}
		label.setHorizontalAlignment(JLabel.CENTER);

		try {
			// nos dois botoes abaixo esta implementado o diagrama de sequencia "divide dedos"
			BufferedImage imgLeftHand = ImageIO.read(ClassLoader.getSystemResource("left_1.jpg"));
			btnLeftHand = new JButton(new ImageIcon(imgLeftHand));
			btnLeftHand.setBackground(Color.GRAY);
			btnLeftHand.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (MainController.getInstance().getJogadorLocal().isTurno()) {
						if (MainController.getInstance().getMaoSelecionada() == null
								&& MainController.getInstance().getJogadorLocal().getMaoEsquerda().getDedos() != 0) {
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
						MainController.getInstance()
								.notificar(MainController.getInstance().getArena().notificarNaoHabilitado());
					}
					refresh();
				}
			});

			BufferedImage imgRightHand = ImageIO.read(ClassLoader.getSystemResource("right_1.jpg"));
			btnRightHand = new JButton(new ImageIcon(imgRightHand));
			btnRightHand.setBackground(Color.GRAY);
			btnRightHand.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (MainController.getInstance().getJogadorLocal().isTurno()) {
						if (MainController.getInstance().getMaoSelecionada() == null
								&& MainController.getInstance().getJogadorLocal().getMaoDireita().getDedos() != 0) {
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
						MainController.getInstance()
								.notificar(MainController.getInstance().getArena().notificarNaoHabilitado());
					}
					refresh();
				}
			});

			JLabel labelNome = new JLabel(MainController.getInstance().getJogadorLocal().getNome());
			labelNome.setHorizontalAlignment(JLabel.CENTER);

			JPanel northPanel = new JPanel();

			// Nos botoes abaixo esta implementado o diagrama de sequencia "manda dedos"
			for (Jogador jogador : MainController.getInstance().getArena().getMesa()) {
				if (jogador.getNome() != MainController.getInstance().getJogadorLocal().getNome()) {
					JPanel oponente = new JPanel();
					BufferedImage imgOpponentLeftHand = ImageIO.read(ClassLoader.getSystemResource("opp_left_1.jpg"));
					JButton btnOponentLeftHand = new JButton(new ImageIcon(imgOpponentLeftHand));
					btnOponentLeftHand.setBackground(Color.GRAY);
					btnOponentLeftHand.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							if (MainController.getInstance().getJogadorLocal().isTurno()) {
								if (MainController.getInstance().getMaoSelecionada() == null) {
									JOptionPane.showMessageDialog(null, "Selecione a sua mão primeiro");
								} else {
									try {
										Mao maoOrigem = MainController.getInstance().getMaoSelecionada();
										Mao maoDestino = jogador.getMaoEsquerda();
										int somaDedos = maoOrigem.getDedos() + maoDestino.getDedos();
										if (somaDedos <= 5 && maoDestino.getDedos() != 0) {
											AtorNetgames.getInstance().enviaJogada(new Move(maoOrigem, maoDestino));
											MainController.getInstance().getArena().efetuaAdicaoDedos(maoOrigem,
													maoDestino);
											MainController.getInstance().getArena().passarTurno();
										} else {
											JOptionPane.showMessageDialog(null, "Jogada inválida");
										}
										MainController.getInstance().limpaMaoSelecionada();
									} catch (NaoJogandoException e1) {
										e1.printStackTrace();
									}
									refresh();
								}
							} else {
								JOptionPane.showMessageDialog(null, "Aguarde sua vez para jogar");
							}
						}
					});
					listMaoEsquerdaOponentes.put(jogador.getOrdem(), btnOponentLeftHand);

					BufferedImage imgOpponentRightHand = ImageIO.read(ClassLoader.getSystemResource("opp_right_1.jpg"));
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
										Mao maoOrigem = MainController.getInstance().getMaoSelecionada();
										Mao maoDestino = jogador.getMaoDireita();
										int somaDedos = maoOrigem.getDedos() + maoDestino.getDedos();
										if (somaDedos <= 5 && maoDestino.getDedos() != 0) {
											AtorNetgames.getInstance().enviaJogada(new Move(maoOrigem, maoDestino));
											MainController.getInstance().getArena().efetuaAdicaoDedos(maoOrigem,
													maoDestino);
											MainController.getInstance().getArena().passarTurno();
										} else {
											JOptionPane.showMessageDialog(null, "Jogada inválida");
										}
										MainController.getInstance().limpaMaoSelecionada();
									} catch (NaoJogandoException e1) {
										e1.printStackTrace();
									}
									refresh();
								}
							} else {
								JOptionPane.showMessageDialog(null, "Aguarde sua vez para jogar");
							}
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
		} catch (Exception e) {
			e.printStackTrace();
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
			BufferedImage imgLeftHand = ImageIO.read(ClassLoader.getSystemResource("left_" + dedosEsquerda + ".jpg"));
			btnLeftHand.setIcon(new ImageIcon(imgLeftHand));

			int dedosDireita = MainController.getInstance().getJogadorLocal().getMaoDireita().getDedos();
			BufferedImage imgRightHand = ImageIO.read(ClassLoader.getSystemResource("right_" + dedosDireita + ".jpg"));
			btnRightHand.setIcon(new ImageIcon(imgRightHand));

			for (Entry<Integer, JButton> entry : listMaoEsquerdaOponentes.entrySet()) {
				Jogador j = MainController.getInstance().getArena().getJogador(entry.getKey());
				int dedosOppEsquerda = j.getMaoEsquerda().getDedos();
				BufferedImage imgOppLeftHand = ImageIO
						.read(ClassLoader.getSystemResource("opp_left_" + dedosOppEsquerda + ".jpg"));
				entry.getValue().setIcon(new ImageIcon(imgOppLeftHand));
			}

			for (Entry<Integer, JButton> entry : listMaoDireitaOponentes.entrySet()) {
				Jogador j = MainController.getInstance().getArena().getJogador(entry.getKey());
				int dedosOppDireita = j.getMaoDireita().getDedos();
				BufferedImage imgOppRightHand = ImageIO
						.read(ClassLoader.getSystemResource("opp_right_" + dedosOppDireita + ".jpg"));
				entry.getValue().setIcon(new ImageIcon(imgOppRightHand));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (MainController.getInstance().getArena().getVencedor() != null) {
			label.setText("O VENCEDOR DA PARTIDA É O JOGADOR "
					+ MainController.getInstance().getArena().getVencedor().getNome());
			MainController.getInstance()
					.notificar("JOGADOR VENCEDOR: " + MainController.getInstance().getArena().getVencedor().getNome());
		} else if (MainController.getInstance().getJogadorLocal().isTurno()) {
			label.setText("SELECIONE A MÃO QUE DESEJA UTILIZAR E APÓS SELECIONE A MÃO QUE DESEJA ATACAR OU DIVIDIR");
		} else {
			label.setText(
					"AGUARDANDO JOGADA DO JOGADOR " + MainController.getInstance().getArena().getDaVez().getNome());
		}
	}

}
