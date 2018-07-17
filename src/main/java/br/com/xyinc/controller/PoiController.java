package br.com.xyinc.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.xyinc.entidade.Poi;
import br.com.xyinc.exception.ServiceException;
import br.com.xyinc.service.IPoiService;

/**
 * Classe de Controller do Poi
 * Faz a ligação do front-end com back-end
 * @author luciano
 *
 */
@Controller
@RestController
@RequestMapping("/pois")
public class PoiController {
	@Autowired
	private IPoiService service;
	
	/**
	 * Método do controller salvar Cliente
	 * @param cliente
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Poi> salvar(@RequestBody Poi p){
		Poi poi = null;
		try {
			poi = service.salvar(p);
		} catch (ServiceException e) {			
			e.printStackTrace();
		}
		return new ResponseEntity<Poi>(poi,HttpStatus.OK);
		
	}
	/**
	 * Método Controller retorna todos POI da base de dados
	 * @return
	 */
	@GetMapping
	public List<Poi> listarTodos(){
		List<Poi> pois = null;
		try {
			pois = service.listarTodos();
		} catch (ServiceException e) {			
			e.printStackTrace();
		}
		return pois;
		
	}
	
	@GetMapping("/{coord_x}/{coord_y}/{d_max}")
	public List<Poi> listarPorProximidade(@PathParam("coord_x") Integer coord_x,@PathParam("coord_y") Integer coord_y, @PathParam("d_max") Integer d_max){
		List<Poi> pois = null;
		try {
			pois = service.listarPorProximidade(coord_x, coord_y, d_max);
		} catch (ServiceException e) {			
			e.printStackTrace();
		}
		return pois;
		
	}

}
