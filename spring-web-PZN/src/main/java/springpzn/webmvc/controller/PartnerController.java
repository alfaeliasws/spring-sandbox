package springpzn.webmvc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import springpzn.webmvc.model.Partner;


@Controller
public class PartnerController {
    

    @GetMapping(path = "/partner/current")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String getPartner(Partner partner) {
        return partner.getId() + " : " + partner.getName();
    }
    
}
