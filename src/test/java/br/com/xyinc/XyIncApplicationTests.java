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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class XyIncApplicationTests {

	final String BASE_PATH = "http://localhost:8080/api/pois";

	@Autowired
	private PoiRepository repository;
	
    private RestTemplate restTemplate;
    
    private ObjectMapper MAPPER = new ObjectMapper();
    
    @Before
    public void setUp() throws Exception {
        repository.deleteAll();

        repository.save(new Poi("Lanchonete",27, 12));
        repository.save(new Poi("Posto", 31, 18));
        repository.save(new Poi("Joalheria", 15, 12));
		repository.save(new Poi("Floricultura", 19, 21));
		repository.save(new Poi("Pub", 12, 8));       
		repository.save(new Poi("Supermercado", 23, 6)); 
       
        restTemplate = new RestTemplate();
    }
    
    @Test
    public void testPost() throws JsonProcessingException{
    	Poi po = new Poi("Churrascaria", 28, 2);
        Poi response = restTemplate.postForObject(BASE_PATH, po, Poi.class);
        Assert.assertEquals("Churrascaria", response.getNome());
    }
   
    
    @Test
    public void testGetListarTodos() throws IOException{
    	String response = restTemplate.getForObject(BASE_PATH + "/listarTodos", String.class);
        List<Poi> poi = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Poi.class));
    	Assert.assertEquals("Lanchonete", poi.get(0).getNome());
    	Assert.assertEquals("Posto", poi.get(1).getNome());
    }
    @Test
    public void testGetListarProximidade() throws IOException{
    	String response = restTemplate.getForObject(BASE_PATH + "/listarProximidade?coord_x=20&coord_y=10&d_max=10", String.class);
        List<Poi> poi = MAPPER.readValue(response, MAPPER.getTypeFactory().constructCollectionType(List.class, Poi.class));
    	Assert.assertEquals("Lanchonete", poi.get(0).getNome());
    	Assert.assertEquals("Joalheria", poi.get(1).getNome());
    	Assert.assertEquals("Pub", poi.get(2).getNome());
    	Assert.assertEquals("Supermercado", poi.get(3).getNome());
    }


}
