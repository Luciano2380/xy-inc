package br.com.xyinc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.xyinc.entidade.Poi;
import br.com.xyinc.exception.ServiceException;
import br.com.xyinc.service.IPoiService;

/**
 * Classe de Controller do Poi Faz a ligação do front-end com back-end
 * 
 * @author luciano
 *
 */
@RestController
@RequestMapping("/api")
public class PoiController {
	@Autowired
	private IPoiService service;

	/**
	 * Método do controller salvar POI
	 * 
	 * @param cliente
	 * @return
	 */
	@RequestMapping(value = "/pois", method = RequestMethod.POST)
	public ResponseEntity<Poi> salvar(@RequestBody @Valid Poi p) {
		Poi poi = service.salvar(p);
		return ResponseEntity.ok(poi);

	}

	/**
	 * Método Controller retorna todos POI da base de dados
	 * 
	 * @return
	 */
	@RequestMapping(value = "/pois/listarTodos", method = RequestMethod.GET)
	public ResponseEntity<List<Poi>> listarTodos() {
		List<Poi> pois = service.listarTodos();
		return !pois.isEmpty()? ResponseEntity.ok(pois):ResponseEntity.notFound().build();

	}

	@RequestMapping(value = "/pois/listarProximidade", method = RequestMethod.GET)
	public ResponseEntity<List<Poi>> listarPorProximidade(@RequestParam(value = "coord_x") Integer coord_x,
			@RequestParam(value = "coord_y") Integer coord_y, @RequestParam(value = "d_max") Integer d_max) {
		List<Poi> pois = service.listarPorProximidade(coord_x, coord_y, d_max);
		return !pois.isEmpty()? ResponseEntity.ok(pois):ResponseEntity.notFound().build();

	}

}
