package br.com.ufsc.cco.es.model;

import java.util.ArrayList;
import java.util.List;

public class Arena {
	
	private List<Jogador> mesa;
	
	public Arena() {
		this.mesa = new ArrayList<Jogador>();
	}

	public List<Jogador> getMesa() {
		return mesa;
	}

	public void setMesa(List<Jogador> mesa) {
		this.mesa = mesa;
	}

}
