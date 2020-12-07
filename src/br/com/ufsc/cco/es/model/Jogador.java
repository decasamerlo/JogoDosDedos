package br.com.ufsc.cco.es.model;

import java.util.List;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Jogador implements Jogada {

	private static final long serialVersionUID = -5364121479634365045L;
	private String nome;
	private boolean turno;
	private Mao maoDireita;
	private Mao maoEsquerda;
	private boolean noJogo;
	private boolean host;
	private int ordem;

	public Jogador(String nome, int ordem) {
		this.nome = nome;
		this.ordem = ordem;
		this.turno = false;
		this.maoDireita = new Mao(this);
		this.maoEsquerda = new Mao(this);
	}

	public void matarMao(Mao maoAlvo) {
		System.out.println("Matando mão do jogador " + nome);
		if (this.maoDireita.equals(maoAlvo)) {
			maoDireita.setDedos(0);
			maoDireita.setViva(false);
		} else if (this.maoEsquerda.equals(maoAlvo)) {
			maoEsquerda.setDedos(0);
			maoEsquerda.setViva(false);
		}
	}

	public void reviverMao(Mao maoAlvo) {
		maoAlvo.setViva(true);
	}

	public void morrer() {
		System.out.println("Jogador " + this.getNome() + " morrendo");
	}

	public void iniciar() {
		System.out.println("Iniciando " + this.getNome());
	}

	public void definirNomes(List<String> adversarios) {
		System.out.println("Definindo Nomes");
	}

	public void definirComoPrimeiro() {
		System.out.println("Definindo como primeiro");
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isTurno() {
		return turno;
	}

	public void setTurno(boolean turno) {
		this.turno = turno;
	}

	public Mao getMaoDireita() {
		return maoDireita;
	}

	public void setMaoDireita(Mao maoDireita) {
		this.maoDireita = maoDireita;
	}

	public Mao getMaoEsquerda() {
		return maoEsquerda;
	}

	public void setMaoEsquerda(Mao maoEsquerda) {
		this.maoEsquerda = maoEsquerda;
	}

	public boolean isNoJogo() {
		return noJogo;
	}

	public void setNoJogo(boolean noJogo) {
		this.noJogo = noJogo;
	}

	public boolean isHost() {
		return host;
	}

	public void setHost(boolean host) {
		this.host = host;
	}

	public int getOrdem() {
		return ordem;
	}

	public void setOrdem(int ordem) {
		this.ordem = ordem;
	}

}
