package springpzn.webmvc.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;


@Controller
public class DateController {
    
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

    @GetMapping(path = "/date")
    @ResponseBody 
    public String getMethodName(@RequestParam(name = "date") Date date) throws IOException {
        return "Date: " + dateFormat.format(date);
    }
    
    public void getDate(Date date){
        
    }
}
