package springpzn.webmvc;


import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestTemplateTest {
    
    @LocalServerPort
    private Integer port;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void addTodoTest(){
        String url = "http://localhost:" + port + "/todos";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("todo", "Belajar Spring WebMVC");

        RequestEntity<MultiValueMap<String, Object>> request = new RequestEntity<>(form, headers, HttpMethod.POST, URI.create(url));

        ResponseEntity<List<String>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<String>>() {});

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Belajar Spring WebMVC", response.getBody().get(0));


    }

    @Test
    void getTodoTest(){
        String url = "http://localhost:" + port + "/todos";
        
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));

        RequestEntity<Object> request = new RequestEntity<>(headers, HttpMethod.GET, URI.create(url));

        ResponseEntity<List<String>> response = restTemplate.exchange(request, new ParameterizedTypeReference<List<String>>() {});

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Belajar Spring WebMVC", response.getBody().get(0));

    }

}
