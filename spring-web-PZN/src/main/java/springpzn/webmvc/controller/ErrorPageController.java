package springpzn.webmvc.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

public class ErrorPageController implements ErrorController{

    @RequestMapping(path = "/error")
    public ResponseEntity<String> error (HttpServletRequest request){
        Integer status = (Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        String message = (String) request.getAttribute(RequestDispatcher.ERROR_MESSAGE);

        String html = """
                    <html>
                    <body>
                    <h1>
                    $status - $message
                    </h1>
                    </>
                    </html>
                """.replace("$status", status.toString()).replace("$message", message);

                return ResponseEntity.status(status).body(html);
    }

}
