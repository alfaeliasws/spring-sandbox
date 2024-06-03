package springpzn.webmvc.controller;

import java.io.IOException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import springpzn.webmvc.service.HelloService;


@Controller
public class HelloController {
    
    @Autowired
    private HelloService helloService;

    @RequestMapping("/hello")
    public void helloWorld(HttpServletResponse response) throws IOException {
        response.getWriter().println("Hello World");
    }
    
    // @RequestMapping(path = "/hello-name", method = RequestMethod.GET)
    @GetMapping(path = "/hello-name")
    // public void helloWorld(HttpServletResponse response, HttpServletRequest request) throws IOException {
    public void helloWorld(@RequestParam(name ="name", required = false) String name, HttpServletResponse response) throws IOException {
        // String name = request.getParameter("name");

        // if(Objects.isNull(name)){
        //     name = "Guest";
        // }
        String responseBody = helloService.hello(name);

        response.getWriter().println("Hello " + name);
        response.getWriter().println(responseBody);
    }


}
