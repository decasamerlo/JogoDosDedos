package br.com.ufsc.cco.es.model;

import java.util.ArrayList;
import java.util.List;

import br.com.ufsc.cco.es.controller.GameController;
import br.com.ufsc.cco.es.controller.MainController;
import br.com.ufsc.cco.es.rede.AtorNetgames;

public class Arena {

	private List<Jogador> mesa;
	private boolean partidaEmAndamento;
	private Jogador vencedor;
	private boolean empate;
	private Jogador daVez;

	public Arena() {
		this.mesa = new ArrayList<Jogador>();
	}

	public void efetuaAdicaoDedos(Mao maoOrigem, Mao maoDestino) {
		int somaDedos = maoOrigem.getDedos() + maoDestino.getDedos();
		Jogador jogador = MainController.getInstance().getArena().getJogador(maoDestino.getJogador().getOrdem());
		if (somaDedos == 5) {
			jogador.matarMao(maoDestino);
		} else {
			if (maoDestino.isDireita()) {
				jogador.getMaoDireita().setDedos(somaDedos);
			} else {
				jogador.getMaoEsquerda().setDedos(somaDedos);
			}
		}
	}

	public void atualizaJogadores() {
		List<Jogador> jogadoresAtivos = new ArrayList<>();
		for (Jogador jogador : mesa) {
			if (jogador.isNoJogo()) {
				jogadoresAtivos.add(jogador);
			}
		}
		if (jogadoresAtivos.size() == 1) {
			vencedor = jogadoresAtivos.get(0);
			partidaEmAndamento = false;
		}
		GameController.getInstance().refresh();
	}

	public void efetuaDivisaoDedos(Mao maoOrigem, Mao maoDestino) {
		Jogador jogador = getJogador(maoOrigem.getJogador().getOrdem());
		if (!maoDestino.isViva()) {
			jogador.reviverMao(maoDestino);
		}
		int somaDedos = maoOrigem.getDedos() + maoDestino.getDedos();
		int div1 = somaDedos / 2;
		int div2 = somaDedos - div1;
		jogador.getMaoDireita().setDedos(div1);
		jogador.getMaoEsquerda().setDedos(div2);
		if (div1 == 0) {
			jogador.getMaoDireita().setViva(false);
		}
		if (div2 == 0) {
			jogador.getMaoEsquerda().setViva(false);
		}
	}

	public void avaliarEncerramento() {
	}

	public Jogador avaliarCondicaoVitoria() {
		return null;
	}

	public void efetuaConcessao(Jogador jogador) {
		jogador.getMaoDireita().setDedos(0);
		jogador.getMaoDireita().setViva(false);
		jogador.getMaoEsquerda().setDedos(0);
		jogador.getMaoEsquerda().setViva(false);
		jogador.setNoJogo(false);
	}

	public String notificarNaoHabilitado() {
		return "Aguarde sua vez para jogar. Agora é a vez de " + daVez.getNome();
	}

	public void passarTurno() {
		daVez.setTurno(false);
		int prox = (daVez.getOrdem() % mesa.size()) + 1;
		Jogador proxJogador = getJogador(prox);
		while (!proxJogador.isNoJogo()) {
			prox = (prox % mesa.size()) + 1;
			proxJogador = getJogador(prox);
		}
		proxJogador.setTurno(true);
		daVez = proxJogador;
	}

	public Mao selecionarMao() {
		return null;
	}

	public int somaDedos(Mao maoOrigem, Mao maoDestino) {
		return 0;
	}

	public boolean encerrarHavendoPartida() {
		return false;
	}

	public void iniciarNovaPartida(int requisicao, List<String> adversarios) {
		MainController.getInstance().getJogadorLocal().setOrdem(requisicao);
		if (requisicao == 1) {
			MainController.getInstance().getJogadorLocal().setTurno(true);
			daVez = MainController.getInstance().getJogadorLocal();
		}
		mesa.add(MainController.getInstance().getJogadorLocal());
		for (int i = 1; i <= adversarios.size() + 1; i++) {
			if (i != requisicao) {
				Jogador adversario = new Jogador(AtorNetgames.getInstance().getNomeAdversario(i), i);
				mesa.add(adversario);
				if (i == 1) {
					adversario.setTurno(true);
					daVez = adversario;
				}
			}
		}
		partidaEmAndamento = true;
	}

	public void esvaziar() {
	}

	public String notificarJogadaInvalida() {
		return null;
	}

	public String notificarVitoria() {
		return null;
	}

	public String notificarEmpate() {
		return null;
	}

	public Jogador getJogador(int ordem) {
		for (Jogador jogador : mesa) {
			if (jogador.getOrdem() == ordem) {
				return jogador;
			}
		}
		return null;
	}

	public boolean isPartidaEmAndamento() {
		return partidaEmAndamento;
	}

	public void setPartidaEmAndamento(boolean partidaEmAndamento) {
		this.partidaEmAndamento = partidaEmAndamento;
	}

	public Jogador getVencedor() {
		return vencedor;
	}

	public void setVencedor(Jogador vencedor) {
		this.vencedor = vencedor;
	}

	public boolean isEmpate() {
		return empate;
	}

	public void setEmpate(boolean empate) {
		this.empate = empate;
	}

	public Jogador getDaVez() {
		return daVez;
	}

	public void setDaVez(Jogador daVez) {
		this.daVez = daVez;
	}

	public List<Jogador> getMesa() {
		return mesa;
	}

	public void setMesa(List<Jogador> mesa) {
		this.mesa = mesa;
	}

}
