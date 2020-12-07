package br.com.ufsc.cco.es.model;

import java.util.ArrayList;
import java.util.List;

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
		System.out.println("Efetuando adicao de dedos");
	}

	public void atualizaJogadores() {
		System.out.println("Atualizando jogadores");
	}

	public void efetuaDivisaoDedos(Mao maoOrigem, Mao maoDestino) {
		System.out.println("Efetuando divisao de dedos para o jogador " + MainController.getInstance().getJogadorLocal().getNome());
		if (!maoDestino.isViva()) {
			getJogador(maoOrigem.getJogador().getOrdem()).reviverMao(maoDestino);
		}
		int somaDedos = maoOrigem.getDedos() + maoDestino.getDedos();
		int div1 = somaDedos/2;
		int div2 = somaDedos - div1;
		maoOrigem.setDedos(div1);
		maoDestino.setDedos(div2);
		getJogador(maoOrigem.getJogador().getOrdem()).matarMao(maoDestino);
	}

	public void avaliarEncerramento() {
		System.out.println("Avaliando Encerramento");
	}

	public Jogador avaliarCondicaoVitoria() {
		System.out.println("Avaliando Condicao de Vitoria");
		return null;
	}

	public void efetuaConcessao(Jogador jogador) {
		System.out.println("Efetuando concessao");
	}

	public String notificarNaoHabilitado() {
		System.out.println("Notificando nao habilitado");
		return null;
	}

	public String notificarConcessao() {
		System.out.println("Notificar concessao");
		return null;
	}

	public void passarTurno() {
		daVez.setTurno(false);
		int prox = (daVez.getOrdem() % mesa.size()) + 1;
		Jogador proxJogador = getJogador(prox);
		proxJogador.setTurno(true);
		daVez = proxJogador;
	}

	public Mao selecionarMao() {
		System.out.println("Selecionando Mao");
		return null;
	}

	public int somaDedos(Mao maoOrigem, Mao maoDestino) {
		System.out.println("Somando Dedos");
		return 0;
	}

	public boolean encerrarHavendoPartida() {
		System.out.println("Encerrando havendo partida");
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
		System.out.println("Esvaziando");
	}
	
	public String notificarJogadaInvalida() {
		System.out.println("Notificando jogada invalida");
		return null;
	}
	
	public String notificarVitoria() {
		System.out.println("Notificando Vitoria");
		return null;
	}
	
	public String notificarEmpate() {
		System.out.println("Notificando Empate");
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
