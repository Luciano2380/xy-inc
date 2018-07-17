package br.com.xyinc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.xyinc.entidade.Poi;
@Repository
public interface PoiRepository extends JpaRepository<Poi, Long> {

}
