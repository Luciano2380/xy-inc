package br.com.xyinc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xyinc.entidade.Poi;
import br.com.xyinc.exception.RegistroNaoEncontradoException;
import br.com.xyinc.repository.PoiRepository;
/**
 * Classe de serviços do POI
 * 
 * @author Luciano
 *
 */
@Service
public class PoiServiceImpl implements IPoiService {
	
	@Autowired
	PoiRepository poiRepository;

	/**
	 * Método que salva um POI na base de dados
	 */
	@Override
	public Poi salvar(Poi t) {	
		return poiRepository.save(t);
	}
	/**
	 * Método que lista todos os POI na base de dados
	 */
	@Override
	public List<Poi> listarTodos(){		
		List<Poi> pois = poiRepository.findAll();
		if(pois.isEmpty()) {
			throw new RegistroNaoEncontradoException();			
		}
		return pois;
	}

	/**
	 * Método que retorna todos os POI de acordo com as coordenadas e a distância
	 */
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
		
		if(listPoisProximos.isEmpty()) {
			throw new RegistroNaoEncontradoException();			
		}
		
		return listPoisProximos;
	}
	
	/**
	 * Método que faz o calculo da distância entre os pontos
	 * @param poi
	 * @param coord_x
	 * @param coord_y
	 * @param d_max
	 * @return
	 */
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
