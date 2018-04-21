package restbs.rest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    protected static Logger logger=LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String getLadder(@RequestParam String w1,@RequestParam String w2) {
        logger.debug("访问!");
        return "hello world"+w1+w2;
    }
}