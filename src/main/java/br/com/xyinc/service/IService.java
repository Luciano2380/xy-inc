package br.com.xyinc.service;

import java.util.List;

public interface IService<T> {

	T salvar(T t);
	
	List<T> listarTodos();
	
}
