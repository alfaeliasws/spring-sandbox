package springpzn.webmvc.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import springpzn.webmvc.model.CreatePersonRequest;


@Controller
// @Slf4j
public class ModelAttributeController {
    
    @PostMapping(path = "/person", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
    @ResponseBody
    public String createPerson(@ModelAttribute @Valid CreatePersonRequest request) {
        String a = new StringBuilder().append("Success create person ")
        .append(request.getFirstName()).append(" ")
        .append(request.getMiddleName()).append(" ")
        .append(request.getLastName()).append(" with email ")
        .append(request.getEmail()).append(" and phone ")
        .append(request.getPhone()).toString(); 
        
        // log.info("{}", a);

        return a;
    }

    @PostMapping(path = "/personBinding", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
    @ResponseBody
    public ResponseEntity<String> createPersonBinding(@ModelAttribute @Valid CreatePersonRequest request, BindingResult bindingResult) {
        // List<ObjectError> errors = bindingResult.getAllErrors();
        List<FieldError> errors = bindingResult.getFieldErrors();
        if(!errors.isEmpty()){

            // errors.forEach(objectError -> {
            //     System.out.println(objectError.getDefaultMessage());
            // });

            errors.forEach(fieldError -> {
                System.out.println(fieldError.getField() + " : " + fieldError.getDefaultMessage());
            });

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You send invalid data");
        }

        String a = new StringBuilder().append("Success create person ")
        .append(request.getFirstName()).append(" ")
        .append(request.getMiddleName()).append(" ")
        .append(request.getLastName()).append(" with email ")
        .append(request.getEmail()).append(" and phone ")
        .append(request.getPhone()).toString(); 
        
        // log.info("{}", a);

        return ResponseEntity.ok(a);
    }


    @PostMapping(path = "/personAddress", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
    @ResponseBody
    public String createPersonAddress(@ModelAttribute CreatePersonRequest request) {
        String a = new StringBuilder().append("Success create person ")
        .append(request.getFirstName()).append(" ")
        .append(request.getMiddleName()).append(" ")
        .append(request.getLastName()).append(" with email ")
        .append(request.getEmail()).append(" and phone ")
        .append(request.getPhone()).append(" with address ")
        .append(request.getAddress().getStreet()).append(" ")
        .append(request.getAddress().getCity()).append(" ")
        .append(request.getAddress().getCountry()).append(" ")
        .append(request.getAddress().getPostalCode()).toString();
        
        // log.info("{}", a);

        return a;
    }

    @PostMapping(path = "/personList", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE )
    @ResponseBody
    public String createPersonList(@ModelAttribute CreatePersonRequest request) {
        String a = new StringBuilder().append("Success create person ")
        .append(request.getFirstName()).append(" ")
        .append(request.getMiddleName()).append(" ")
        .append(request.getLastName()).append(" with email ")
        .append(request.getEmail()).append(" and phone ")
        .append(request.getPhone()).append(" with address ")
        .append(request.getAddress().getStreet()).append(" ")
        .append(request.getAddress().getCity()).append(" ")
        .append(request.getAddress().getCountry()).append(" ")
        .append(request.getAddress().getPostalCode()).toString();
        
        // log.info("{}", a);
        
        System.out.println(request);

        return a;
    }
    

}
