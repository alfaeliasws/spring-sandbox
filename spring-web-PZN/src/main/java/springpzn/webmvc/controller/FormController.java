package springpzn.webmvc.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
public class FormController {

    @PostMapping(path = "/form/hello", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public String hello(@RequestParam(name = "name") String name){
        return "Hello " + name;
    }

    @PostMapping(
        path = "/form/helloHtml", 
        consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
        produces = MediaType.TEXT_HTML_VALUE
    )
    @ResponseBody
    public String helloHtml(@RequestParam(name = "name") String name){
        return """
                <html>
                <body>
                <h1>
                Hello $name
                </h1>
                </body>
                </html>
                """.replace("$name", name);
    }

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @PostMapping(path = "/form/person", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody    
    public String createPerson(
        @RequestParam(name = "name") String name,
        @RequestParam(name = "birthDate") Date birthDate,
        @RequestParam(name = "address") String address
    ) {
        return "Success create Person with name : " + name + ", birthDate : " + dateFormat.format(birthDate) + ", address : " + address;
    }
    

}