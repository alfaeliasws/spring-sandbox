package springpzn.webmvc.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import springpzn.webmvc.model.CreatePersonRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Controller
public class JsonController {
    
    @PostMapping(
        path = "/api/person",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public CreatePersonRequest createPersonJson(@RequestBody @Valid CreatePersonRequest request){
        return request;
    }

}
