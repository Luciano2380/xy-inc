package br.com.xyinc.exception;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
/**
 * Classe que controla e trata as exceções da api
 * 
 * @author Luciano
 *
 */
@RestControllerAdvice
public class XyExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final static String CAMPOS_INVALIDOS="A Requisição contém campos inválidos";
	private final static String REGISTROS_NAO_ENCONTRADOS="Registros Não Encontrados";
   
	/**
	 * Método que trata as exceções de validação dos campos da Entidade
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<XyObjectErro> errors = getErrosMethodArgument(ex);
		XyErroResponse errorResponse = new XyErroResponse(CAMPOS_INVALIDOS, status.value(), status.getReasonPhrase(),errors);
		return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	/**
	 * Método que trata as exceções de registros não encontrados
	 */
	@ExceptionHandler({ RegistroNaoEncontradoException.class })
	public ResponseEntity<Object> handleEmptyResultDataAccessException(RegistroNaoEncontradoException ex, WebRequest request) {
		 XyErroResponse errorResponse = new XyErroResponse(REGISTROS_NAO_ENCONTRADOS,HttpStatus.NOT_FOUND.value(),HttpStatus.NOT_FOUND.getReasonPhrase());
		return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	/**
	 * Método que trata as exceções de validações dos requestParam
	 */
	@ExceptionHandler(value = { ConstraintViolationException.class })
	 public ResponseEntity<Object> handleResourceNotFoundException(ConstraintViolationException ex, WebRequest request) {	     
	      List<XyObjectErro> errors = getErrosCostraint(ex);	    
	      XyErroResponse errorResponse = new XyErroResponse(CAMPOS_INVALIDOS,HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(),errors);
		  return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);	    
	 }	

	/**
	 * Monta a resposta de validação dos campos da Entidade
	 * @param ex
	 * @return
	 */
	private List<XyObjectErro> getErrosMethodArgument(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getFieldErrors().stream()
				.map(error -> new XyObjectErro(error.getDefaultMessage(), error.getField(), error.getRejectedValue()))
				.collect(Collectors.toList());
	}
	/**
	 * Monta a resposta de validação dos  requestParam
	 * @param ex
	 * @return
	 */
	private List<XyObjectErro> getErrosCostraint(ConstraintViolationException ex) {
		return ex.getConstraintViolations().stream()
				.map(error -> new XyObjectErro(error.getMessage(),String.valueOf(error.getPropertyPath()),null))
				.collect(Collectors.toList());
	}
	
	
}
