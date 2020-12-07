package br.com.ufsc.cco.es.model;

import br.ufsc.inf.leobr.cliente.Jogada;

public class Move implements Jogada {

	private static final long serialVersionUID = -2712218242550605299L;
	private Mao maoOrigem;
	private Mao maoDestino;
	
	public Move(Mao maoOrigem, Mao maoDestino) {
		this.maoOrigem = maoOrigem;
		this.maoDestino = maoDestino;
	}

	public Mao getMaoOrigem() {
		return maoOrigem;
	}

	public void setMaoOrigem(Mao maoOrigem) {
		this.maoOrigem = maoOrigem;
	}

	public Mao getMaoDestino() {
		return maoDestino;
	}

	public void setMaoDestino(Mao maoDestino) {
		this.maoDestino = maoDestino;
	}

}
