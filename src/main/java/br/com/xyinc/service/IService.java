package br.com.xyinc.service;

import java.util.List;

import br.com.xyinc.exception.ServiceException;

public interface IService<T> {

	T salvar(T t) throws ServiceException;
	
	List<T> listarTodos() throws ServiceException;
	
}
