package restbs.rest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import restbs.rest.bookstore.datactrl;

@CrossOrigin
@RestController
public class UserController {

    protected static Logger logger=LoggerFactory.getLogger(UserController.class);

    @Autowired
    private datactrl db;

    @RequestMapping(value = "/getbookdata",method = RequestMethod.GET)
    public
    @ResponseBody
    JSONArray getbookdata(){
        return this.db.getbookdata();
    }

    @RequestMapping(value = "/services/modifydata",method = RequestMethod.POST)
    public
    @ResponseBody
    String modifydata(@RequestBody JSONObject data){
        return this.db.modifydata(data);
    }

    @RequestMapping(value = "/services/deletedata",method = RequestMethod.POST)
    public
    @ResponseBody
    String deletedata(@RequestBody JSONObject data){
        return this.db.deletedata(data);
    }

    @RequestMapping(value = "/services/newdata",method = RequestMethod.POST)
    public
    @ResponseBody
    String newdata(@RequestBody JSONObject data) {
        return this.db.newdata(data);
    }


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public
    @ResponseBody
    JSONArray getLadder(@RequestParam String w1,@RequestParam String w2) {
        logger.debug("访问!");
        JSONObject obj = new JSONObject();
        obj.accumulate("key1",111);
        obj.accumulate("key2",222);
        JSONArray res = new JSONArray();
        res.add(obj);
        res.add(obj);

        return res;
    }

    @RequestMapping(value = "/init",method = RequestMethod.GET)
    public
    @ResponseBody
    String initList(@RequestParam String name){
        this.db.init();
        return "success";
    }

    @RequestMapping(value="/insert",method = RequestMethod.GET)
    public
    @ResponseBody
    String insert(){
        this.db.insert();
        return "success";
    }

    @RequestMapping(value="/modify",method = RequestMethod.GET)
    public
    @ResponseBody
    String modify(){
        this.db.modify();;
        return "modify success";
    }

    @RequestMapping(value="/select",method = RequestMethod.GET)
    public
    @ResponseBody
    String select(){
        this.db.select();
        return "select success";
    }

    @RequestMapping(value="/delete",method = RequestMethod.GET)
    public
    @ResponseBody
    String delete(){
        this.db.delete();
        return "delete success";
    }

}