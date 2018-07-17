package br.com.xyinc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xyinc.entidade.Poi;
import br.com.xyinc.exception.ServiceException;
import br.com.xyinc.repository.PoiRepository;
@Service
public class PoiServiceImpl implements IPoiService {
	
	@Autowired
	PoiRepository poiRepository;

	@Override
	public Poi salvar(Poi t) throws ServiceException {	
		return poiRepository.save(t);
	}

	@Override
	public List<Poi> listarTodos() throws ServiceException {		
		return poiRepository.findAll();
	}

	@Override
	public List<Poi> listarPorProximidade(Integer coord_x, Integer coord_y, Integer d_max) throws ServiceException {
		
		return null;
	}

	

}
