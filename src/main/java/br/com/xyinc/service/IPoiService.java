package br.com.xyinc.service;

import java.util.List;

import br.com.xyinc.entidade.Poi;

public interface IPoiService extends IService<Poi> {
	List<Poi> listarPorProximidade(Integer coord_x, Integer coord_y, Integer d_max);

}
