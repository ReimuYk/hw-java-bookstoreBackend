package restbs.rest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import restbs.rest.bookstore.datactrl;
import restbs.rest.bookstore.orderformCtrl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

@CrossOrigin
@RestController
public class UserController {

    protected static Logger logger=LoggerFactory.getLogger(UserController.class);

    @Autowired
    private datactrl db;

    @Autowired
    private orderformCtrl order;

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

    @RequestMapping(value = "/services/register",method = RequestMethod.POST)
    public
    @ResponseBody
    String register(@RequestBody JSONObject data) {
        return this.db.register(data);
    }

    @RequestMapping(value = "/services/login",method = RequestMethod.POST)
    public
    @ResponseBody
    String login(@RequestBody JSONObject data, HttpServletResponse httpServletResponse) {
        Cookie ck0 = new Cookie("username",data.getString("userName"));
        Cookie ck1 = new Cookie("password",data.getString("password"));
        httpServletResponse.addCookie(ck0);
        httpServletResponse.addCookie(ck1);
        return this.db.login(data);
    }

    @RequestMapping(value = "/services/remember",method = RequestMethod.GET)
    public String remember(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse){
        Cookie[] cookies = httpRequest.getCookies();
        String name="";
        String password="";
        for (Cookie cc:cookies){
            System.out.println(cc.getName());
            System.out.println(cc.getValue());
            if (cc.getName().equals("username")){
                name = cc.getValue();
            }
            if (cc.getName().equals("password")){
                password = cc.getValue();
            }
        }
        return this.db.rememberLogin(name,password);
    }

    @RequestMapping(value = "/services/orderlist",method = RequestMethod.GET)
    public
    @ResponseBody
    JSONArray orderlist(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) {
        Cookie[] cookies = httpRequest.getCookies();
        for (Cookie cc:cookies){
            if (cc.getName().equals("username")){
                System.out.println("getusername");
                return this.order.getUserList(cc.getValue());
            }
        }
        return null;
    }

    @RequestMapping(value = "/services/createorder",method = RequestMethod.POST)
    public
    @ResponseBody
    String createorder(HttpServletRequest httpRequest, @RequestBody JSONObject data) {
        Cookie[] cookies = httpRequest.getCookies();
        for (Cookie cc:cookies){
            if (cc.getName().equals("username")){
                data.put("username",cc.getValue());
            }
        }
        System.out.println(data);
        return this.order.createOrder(data);
    }

    @RequestMapping(value = "/services/getuserinfo",method = RequestMethod.GET)
    public
    @ResponseBody
    JSONObject getuserinfo(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse) {
        Cookie[] cookies = httpRequest.getCookies();
        for (Cookie cc:cookies){
            if (cc.getName().equals("username")){
                System.out.println("getusername");
                return this.db.getUserInfo(cc.getValue());
            }
        }
        return null;
    }

    @RequestMapping(value = "/services/modifyuserinfo",method = RequestMethod.POST)
    public
    @ResponseBody
    String modifyuserinfo(@RequestBody JSONObject data){
        return this.db.modifyUser(data);
    }

    @RequestMapping(value = "/services/modifypwd",method = RequestMethod.POST)
    public
    @ResponseBody
    String modifypwd(@RequestBody JSONObject data){
        return this.db.modifyUserPwd(data);
    }

    @RequestMapping(value = "/cookies",method = RequestMethod.GET)
    public String cookies(HttpServletRequest httpRequest, HttpServletResponse httpServletResponse){
        Cookie[] cookies = httpRequest.getCookies();
        for (Cookie cc:cookies){
            System.out.println(cc.getName());
            System.out.println(cc.getValue());
        }
        Cookie ck = new Cookie("test","hello");
        ck.setMaxAge(10);
        httpServletResponse.addCookie(ck);
        return "OK";
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