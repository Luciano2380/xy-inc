package br.com.xyinc.service;

import java.util.ArrayList;
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
	public Poi salvar(Poi t) {	
		return poiRepository.save(t);
	}

	@Override
	public List<Poi> listarTodos(){		
		return poiRepository.findAll();
	}

	@Override
	public List<Poi> listarPorProximidade(Integer coord_x, Integer coord_y, Integer d_max) {
		List<Poi> listPoisProximos = new ArrayList<Poi>();
		List<Poi> listaPois = listarTodos();
		
		listaPois.forEach(p->{
			boolean resultado = calculaDistanciaEntrePontos(p, coord_x, coord_y, d_max);
			if(resultado) {
				listPoisProximos.add(p);
			}
		});
		
		return listPoisProximos;
	}
	
	public boolean calculaDistanciaEntrePontos(Poi poi, Integer coord_x, Integer coord_y, Integer d_max) {
		boolean retorno = false;
		//Calculo das coordenadas x
		Double resultadoX = Math.pow((poi.getCoordX()-coord_x), 2);
		//Calculo das coordenadas x
		Double resultadoY = Math.pow((poi.getCoordY()-coord_y), 2);
		//Calculo do ponto
		Double resultado = Math.sqrt((resultadoX+resultadoY));
		
		if(resultado<=d_max) {
			retorno = true;
		}
		return retorno;
	}

	

}
