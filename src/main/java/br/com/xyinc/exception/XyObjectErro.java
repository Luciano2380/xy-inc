package br.com.xyinc.exception;
/**
 * Classe que representa a exceções da api
 * 
 * @author Luciano
 *
 */
public class XyObjectErro {

	private String mensagem;
	private String campo;
	private Object parametro;

	public XyObjectErro(String mensagem, String campo, Object parametro) {
		super();
		this.mensagem = mensagem;
		this.campo = campo;
		this.parametro = parametro;
	}
	
	

	public XyObjectErro(String mensagem) {
		super();
		this.mensagem = mensagem;
	}



	public String getMensagem() {
		return mensagem;
	}

	public String getCampo() {
		return campo;
	}

	public Object getParametro() {
		return parametro;
	}

}
