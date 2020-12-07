package br.com.ufsc.cco.es.model;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Mao implements Jogada {

	private static final long serialVersionUID = 89708452763618073L;
	private int dedos;
	private boolean viva;
	private Jogador jogador;

	public Mao(Jogador jogador) {
		this.dedos = 1;
		this.viva = true;
		this.jogador = jogador;
	}

	public void receberDedos(int nDedos) {
		System.out.println("Recebendo dedos: " + nDedos);
	}

	public void mandarDedos(int nDedos) {
		System.out.println("Mandando dedos: " + nDedos);
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

}
