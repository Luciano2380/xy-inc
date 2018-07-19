package br.com.xyinc;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.xyinc.entidade.Poi;
import br.com.xyinc.repository.PoiRepository;
/**
 * Classe de Teste da api
 * 
 * @author Luciano
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class XyIncApplicationTests {

	final String BASE_PATH = "http://localhost:8080/pois";

	@Autowired
	private PoiRepository repository;
	
    private RestTemplate restTemplate;
    
    private ObjectMapper MAPPER = new ObjectMapper();
    
    /**
     * Adiciona dados na base para o Teste
     * 
     * @throws Exception
     */
    @Before
    public void setBaseDados() throws Exception {
        repository.deleteAll();

        repository.save(new Poi("Lanchonete",27, 12));
        repository.save(new Poi("Posto", 31, 18));
        repository.save(new Poi("Joalheria", 15, 12));
		repository.save(new Poi("Floricultura", 19, 21));
		repository.save(new Poi("Pub", 12, 8));       
		repository.save(new Poi("Supermercado", 23, 6));
		repository.save(new Poi("Churrascaria", 28, 2));
       
        restTemplate = new RestTemplate();
    }
    
    /**
     * Testa Requisição Post
     * 
     * @throws Exception
     */
    @Test
    public void testPost() throws JsonProcessingException{
    	Poi po1 = new Poi("Hotel", 29, 2);
        Poi response1 = restTemplate.postForObject(BASE_PATH, po1, Poi.class);
        Assert.assertEquals("Hotel", response1.getNome());
        
        Poi po2 = new Poi("Igreja", 30, 3);
        Poi response2 = restTemplate.postForObject(BASE_PATH, po2, Poi.class);
        Assert.assertEquals("Igreja", response2.getNome());
    }
   
    /**
     * Testa a Requisição Get, o metodo ListarTodos    
     * @throws IOException
     */
    @Test
    public void testGetListarTodos() throws IOException{
    	String response = restTemplate.getForObject(BASE_PATH + "/listarTodos", String.class);
        List<Poi> poi = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Poi.class));
    	Assert.assertEquals("Lanchonete", poi.get(0).getNome());
    	Assert.assertEquals("Posto", poi.get(1).getNome());
    	Assert.assertEquals("Joalheria", poi.get(2).getNome());
    	Assert.assertEquals("Floricultura", poi.get(3).getNome());
    }
    /**
     * Testa a Requisição Get, o metodo ListarProximidade    
     * @throws IOException
     */
    @Test
    public void testGetListarProximidade() throws IOException{
    	String response = restTemplate.getForObject(BASE_PATH + "/listarProximidade?coord_x=20&coord_y=10&d_max=10", String.class);
        List<Poi> poi = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Poi.class));
    	Assert.assertEquals("Lanchonete", poi.get(0).getNome());
    	Assert.assertEquals("Joalheria", poi.get(1).getNome());
    	Assert.assertEquals("Pub", poi.get(2).getNome());
    	Assert.assertEquals("Supermercado", poi.get(3).getNome());
    	
    	String response2 = restTemplate.getForObject(BASE_PATH + "/listarProximidade?coord_x=30&coord_y=15&d_max=10", String.class);
        List<Poi> poi2 = MAPPER.readValue(response2, MAPPER.getTypeFactory().constructCollectionType(List.class, Poi.class));
    	Assert.assertEquals("Lanchonete", poi2.get(0).getNome());
    	Assert.assertEquals("Posto", poi2.get(1).getNome());
    	
    }


}
