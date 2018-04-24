package restbs.rest;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import bookstore.CustomerRepository;
import bookstore.datactrl;

import org.springframework.data.repository.CrudRepository;

@RestController
public class UserController {

    protected static Logger logger=LoggerFactory.getLogger(UserController.class);

    datactrl db=new datactrl();

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public JSONObject getLadder(@RequestParam String w1,@RequestParam String w2) {
        logger.debug("访问!");
        JSONObject obj = new JSONObject();
        obj.accumulate("key1",111);
        obj.accumulate("key2",222);

        return obj;
    }

    @RequestMapping(value = "/init",method = RequestMethod.GET)
    public String initList(@RequestParam String name){
        this.db.init();
        return "success";
    }

}