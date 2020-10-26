package br.com.ufsc.cco.es.rede;

import br.ufsc.inf.leobr.cliente.Jogada;
import br.ufsc.inf.leobr.cliente.OuvidorProxy;
import br.ufsc.inf.leobr.cliente.Proxy;
import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
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
		// TODO Auto-generated method stub

	}

	@Override
	public void receberMensagem(String msg) {
		// TODO Auto-generated method stub
	}

	@Override
	public void receberJogada(Jogada jogada) {
		// TODO Auto-generated method stub

	}

	@Override
	public void tratarConexaoPerdida() {
		// TODO Auto-generated method stub

	}

	@Override
	public void tratarPartidaNaoIniciada(String message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void iniciarNovaPartida(Integer posicao) {
		// TODO Auto-generated method stub
		
	}

}
