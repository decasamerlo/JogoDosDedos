package br.com.ufsc.cco.es.rede;

import java.util.List;

import br.com.ufsc.cco.es.controller.MainController;
import br.com.ufsc.cco.es.model.Move;
import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;

public class AtorNetgames implements OuvidorProxy {

	private static final long serialVersionUID = 1L;
	protected Proxy proxy;
	private static AtorNetgames netgamesInstance;

	private AtorNetgames() {
		super();
		this.proxy = Proxy.getInstance();
		proxy.addOuvinte(this);
	}

	public static AtorNetgames getInstance() {
		if (netgamesInstance == null) {
			netgamesInstance = new AtorNetgames();
		}
		return netgamesInstance;
	}

	public String conectar(String servidor, String nome) {
		try {
			proxy.conectar(servidor, nome);
		} catch (JahConectadoException e) {
			e.printStackTrace();
			return "Voce ja esta conectado";
		} catch (NaoPossivelConectarException e) {
			e.printStackTrace();
			return "Nao foi possivel conectar";
		} catch (ArquivoMultiplayerException e) {
			e.printStackTrace();
			return "Voce esqueceu o arquivo de propriedades";
		}
		return "Sucesso: conectado a Netgames Server";

	}

	public String desconectar() {
		try {
			proxy.desconectar();
		} catch (NaoConectadoException e) {
			e.printStackTrace();
			return "Voce nao esta conectado";
		}
		return "Sucesso: desconectado de Netgames Server";
	}

	public String iniciarPartida(Integer numeroDeJogadores) {
		try {
			proxy.iniciarPartida(numeroDeJogadores);
		} catch (NaoConectadoException e) {
			return "Falha ao tentar enviar solicitação de início ao Netgames Server!";
		}
		return "Sucesso: Solicitação de início enviada ao Netgames Server!";
	}

	@Override
	public void finalizarPartidaComErro(String message) {
	}

	@Override
	public void receberMensagem(String msg) {
	}

	@Override
	public void receberJogada(Jogada jogada) {
		Move move = (Move) jogada;
		if (move.getMaoOrigem() == null || move.getMaoDestino() == null) {
			MainController.getInstance().getArena().efetuaConcessao(MainController.getInstance().getArena().getDaVez());
		} else if (move.getMaoOrigem().getJogador().getOrdem() == move.getMaoDestino().getJogador().getOrdem()) {
			MainController.getInstance().getArena().efetuaDivisaoDedos(move.getMaoOrigem(), move.getMaoDestino());
		} else {
			MainController.getInstance().getArena().efetuaAdicaoDedos(move.getMaoOrigem(), move.getMaoDestino());
		}
		MainController.getInstance().getArena().passarTurno();
		MainController.getInstance().getArena().atualizaJogadores();
	}

	@Override
	public void tratarConexaoPerdida() {
	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		List<String> adversarios = proxy.obterNomeAdversarios();
		MainController.getInstance().getArena().iniciarNovaPartida(posicao, adversarios);
		MainController.getInstance().showGame();
	}

	public String getNomeAdversario(Integer posicao) {
		return proxy.obterNomeAdversario(posicao);
	}

	public void enviaJogada(Jogada jogada) throws NaoJogandoException {
		proxy.enviaJogada(jogada);
	}

}
