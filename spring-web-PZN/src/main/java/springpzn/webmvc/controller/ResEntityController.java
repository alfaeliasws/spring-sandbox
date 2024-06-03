package springpzn.webmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import springpzn.webmvc.model.User;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ResEntityController {
    

    @PostMapping(path = "/auth/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ResponseEntity<String> login(
        @RequestParam(name = "username") String username, @RequestParam(name = "password") String password 
    ) {
        if("eko".equals(username) && "rahasia".equals(password)){
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("KO", HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping(path = "/auth/loginCookie", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ResponseEntity<String> loginWithCookie(
        @RequestParam(name = "username") String username, 
        @RequestParam(name = "password") String password,
        HttpServletResponse servletResponse 
    ) {
        if("eko".equals(username) && "rahasia".equals(password)){
            Cookie cookie = new Cookie("username", username);
            cookie.setPath("/");
            servletResponse.addCookie(cookie);

            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("KO", HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping(path = "/auth/loginSession", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public ResponseEntity<String> loginSession(
        @RequestParam(name = "username") String username, 
        @RequestParam(name = "password") String password,
        HttpServletResponse servletResponse,
        HttpServletRequest servletRequest
    ) {
        if("eko".equals(username) && "rahasia".equals(password)){
            HttpSession session = servletRequest.getSession();

            session.setAttribute("user", new User(username));

            Cookie cookie = new Cookie("username", username);
            cookie.setPath("/");
            servletResponse.addCookie(cookie);

            return new ResponseEntity<>("OK", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("KO", HttpStatus.UNAUTHORIZED);
        }

    }

    @GetMapping(path = "/auth/user")
    public ResponseEntity<String> getUser(@CookieValue("username") String username){
        return ResponseEntity.ok("Hello " + username);
    }
    

}
