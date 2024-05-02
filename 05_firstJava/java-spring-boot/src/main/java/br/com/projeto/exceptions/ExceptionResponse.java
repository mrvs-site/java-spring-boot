package br.com.projeto.exceptions;

import java.io.Serializable;
import java.util.Date;



public class ExceptionResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Date timestamp;
	private String mensagem;
	private String details;
	
	public ExceptionResponse(Date timestamp, String mensagem, String details) {
		this.timestamp = timestamp;
		this.mensagem = mensagem;
		this.details = details;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	
	
}
