package br.com.xyinc.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.xyinc.entidade.Poi;
import br.com.xyinc.service.IPoiService;

/**
 * Classe de Controller do Poi Faz a ligação do front-end com back-end
 * 
 * @author Luciano
 *
 */
@RestController
@RequestMapping("/pois")
@Validated
public class PoiController {
	@Autowired
	private IPoiService service;

	/**
	 * Método do controller salvar POI
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Poi> salvar(@RequestBody @Valid Poi p) {
		Poi poi = service.salvar(p);
		return ResponseEntity.ok(poi);

	}

	/**
	 * Método Controller retorna todos POI da base de dados
	 * 
	 * @return List<Poi>
	 */
	@RequestMapping(value = "/listarTodos", method = RequestMethod.GET)
	public ResponseEntity<List<Poi>> listarTodos() {
		List<Poi> pois = service.listarTodos();
		return ResponseEntity.ok(pois);

	}

	/**
	 * Método Controller retorna todos POI da base de dados 
	 * de acordo com  as coordenadas e distância estabelecida	 * 
	 * @return List<Poi>
	 */
	@RequestMapping(value = "/listarProximidade", method = RequestMethod.GET)
	public ResponseEntity<List<Poi>> listarPorProximidade(@NotNull(message = "{coordy.nao.vazio}") @Min(value = 0, message = "{coordx.inteiro}")@RequestParam(value = "coord_x") Integer coord_x,			
			@NotNull(message = "{coordy.nao.vazio}") @Min(value = 0, message = "{coordy.inteiro}") @RequestParam(value = "coord_y") Integer coord_y,
			@NotNull(message = "{dmax.nao.vazio}") @Min(value = 0, message = "{dmax.inteiro}") @RequestParam(value = "d_max") Integer d_max) {
		List<Poi> pois = service.listarPorProximidade(coord_x, coord_y, d_max);
		return ResponseEntity.ok(pois);

	}

}
