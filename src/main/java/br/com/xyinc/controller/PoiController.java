package br.com.xyinc.controller;

import java.util.List;

import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/api")
public class PoiController {
	@Autowired
	private IPoiService service;
	
	/**
	 * Método do controller salvar POI
	 * @param cliente
	 * @return
	 */
	@PostMapping("/pois")
	public ResponseEntity<Poi> salvar(@RequestBody @Valid Poi p){
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
	@GetMapping("/pois")
	public ResponseEntity<List<Poi>> listarTodos(){
		List<Poi> pois = null;
		try {
			pois = service.listarTodos();
		} catch (ServiceException e) {			
			e.printStackTrace();
		}
		return new ResponseEntity<List<Poi>>(pois,HttpStatus.OK);
		
	}
	
	@GetMapping("/pois/{coord_x}/{coord_y}/{d_max}")
	public ResponseEntity<List<Poi>> listarPorProximidade(@PathVariable(value="coord_x") Integer coord_x,@PathVariable(value="coord_y") Integer coord_y, @PathVariable(value="d_max") Integer d_max){
		List<Poi> pois = null;
		try {
			pois = service.listarPorProximidade(coord_x, null, null);
		} catch (ServiceException e) {			
			e.printStackTrace();
		}
		return new ResponseEntity<List<Poi>>(pois,HttpStatus.OK);
		
	}

}
