package br.com.xyinc.exception;

import java.util.List;
/**
 * Classe que representa as respostas da exceções da api
 * 
 * @author Luciano
 *
 */
public class XyErroResponse {
	private String mensagem;
	private int codigo;
	private String status;
	private List<XyObjectErro> erros;

	public XyErroResponse(String mensagem, int codigo, String status, List<XyObjectErro> erros) {
		super();
		this.mensagem = mensagem;
		this.codigo = codigo;
		this.status = status;	
		this.erros = erros;
	}
	
	

	public XyErroResponse(String mensagem,int codigo, String status) {
		super();
		this.mensagem = mensagem;
		this.codigo = codigo;
		this.status = status;	
	}


	public String getMensagem() {
		return mensagem;
	}

	public int getCodigo() {
		return codigo;
	}

	public String getStatus() {
		return status;
	}	

	public List<XyObjectErro> getErros() {
		return erros;
	}

}
