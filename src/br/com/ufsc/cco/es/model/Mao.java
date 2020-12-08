package br.com.ufsc.cco.es.model;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Mao implements Jogada {

	private static final long serialVersionUID = 89708452763618073L;
	private int dedos;
	private boolean viva;
	private Jogador jogador;
	private boolean direita;

	public Mao(Jogador jogador, boolean direita) {
		this.dedos = 1;
		this.viva = true;
		this.jogador = jogador;
		this.direita = direita;
	}

	public void receberDedos(int nDedos) {
	}

	public void mandarDedos(int nDedos) {
	}

	public int getDedos() {
		return dedos;
	}

	public void setDedos(int dedos) {
		this.dedos = dedos;
	}

	public boolean isViva() {
		return viva;
	}

	public void setViva(boolean viva) {
		this.viva = viva;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
	}

	public boolean isDireita() {
		return direita;
	}

	public void setDireita(boolean direita) {
		this.direita = direita;
	}

}
