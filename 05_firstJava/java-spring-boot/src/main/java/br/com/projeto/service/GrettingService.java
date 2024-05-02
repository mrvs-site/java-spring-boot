package br.com.projeto.service;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import br.com.projeto.exceptions.UnsupportedMathOperationException;

@Service
public class GrettingService {

	
	public Double soma(String x, String y) {
		valida(x);
		valida(y);
		
		return convertToDouble(x) + convertToDouble(y);
	}
	
	public Double sub(@NonNull String x, @NonNull String y) {
		valida(x);
		valida(y);
		
		return convertToDouble(x) - convertToDouble(y);
	}
	
	public Double mult(@NonNull String x, @NonNull String y) {
		valida(x);
		valida(y);
		
		return convertToDouble(x) * convertToDouble(y);
	}
	
	public Double div(@NonNull String x, @NonNull String y) {
		valida(x);
		valida(y);
		
		return convertToDouble(x) / convertToDouble(y);
	}
	
	public Double raiz(@NonNull String x) {
		valida(x);
		
		return Math.sqrt(convertToDouble(x));
	}
	
	private void valida(String valor) {
		if(!isNumeric(valor)) {
			throw new UnsupportedMathOperationException("Favor passar parâmetro numérico!");
		}
	}
	
	private Double convertToDouble(String valor) {
		if(valor == null) return 0D;
		String number  = valor.replaceAll(",", ".");
		if(isNumeric(number)) return Double.parseDouble(number);
		return null;
	}

	private boolean isNumeric(String valor) {
		
		if( valor == null ) return false;
		String number = valor.replaceAll(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");

	}
	
}
