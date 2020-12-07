package br.com.ufsc.cco.es.controller;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import br.com.ufsc.cco.es.model.Arena;
import br.com.ufsc.cco.es.model.Jogador;
import br.com.ufsc.cco.es.model.Mao;
import br.com.ufsc.cco.es.rede.AtorNetgames;

public class MainController {

	private static MainController mainController;
	private static final HomeController homeController = HomeController.getInstance();
	private static final GameController gameController = GameController.getInstance();
	private static final ConnectController connectController = ConnectController.getInstance();
	private JPanel cards = new JPanel(new CardLayout());
	private Arena arena = new Arena();
	private Jogador jogadorLocal;
	private Mao maoSelecionada;
	private JFrame frame = new JFrame("Jogo dos Dedos");

	public static MainController getInstance() {
		if (mainController == null) {
			mainController = new MainController();
		}
		return mainController;
	}

	public void start() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(2000, 700);
		frame.setLocationRelativeTo(null);

		JPanel mainPanel = new JPanel(new BorderLayout());

		cards.add(homeController.getPanel(frame), "HOME");
		cards.add(connectController.getPanel(frame), "CONNECT");

		mainPanel.add(cards, BorderLayout.CENTER);

		frame.setContentPane(mainPanel);
		frame.setVisible(true);

	}

	public void conectarJogador(String servidor, Jogador jogador) {
		JOptionPane.showMessageDialog(null, AtorNetgames.getInstance().conectar(servidor, jogador.getNome()));
		this.jogadorLocal = jogador;
		showConnect();
	}

	public void desconectar() {
		AtorNetgames.getInstance().desconectar();
		JOptionPane.showMessageDialog(null, "Desconectado com sucesso!");
		arena.setMesa(new ArrayList<>());
		showHome();
	}

	public boolean isConectado() {
		if (arena.getMesa() != null && arena.getMesa().size() != 0) {
			return true;
		}
		return false;
	}

	public void showHome() {
		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, "HOME");
	}

	public void showConnect() {
		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, "CONNECT");
	}

	public void showGame() {
		cards.add(gameController.getPanel(), "GAME");
		CardLayout cl = (CardLayout) (cards.getLayout());
		cl.show(cards, "GAME");
	}
	
	public void selecionaMaoEsquerda() {
		this.maoSelecionada = jogadorLocal.getMaoEsquerda();
	}
	
	public void selecionaMaoDireita() {
		this.maoSelecionada = jogadorLocal.getMaoDireita();
	}
	
	public void limpaMaoSelecionada() {
		this.maoSelecionada = null;
	}

	public void refresh() {
		gameController.refresh();
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public Jogador getJogadorLocal() {
		return jogadorLocal;
	}

	public void setJogadorLocal(Jogador jogadorLocal) {
		this.jogadorLocal = jogadorLocal;
	}

	public Mao getMaoSelecionada() {
		return maoSelecionada;
	}

	public void setMaoSelecionada(Mao maoSelecionada) {
		this.maoSelecionada = maoSelecionada;
	}

}
