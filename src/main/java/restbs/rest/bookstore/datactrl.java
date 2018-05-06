package restbs.rest.bookstore;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class datactrl {
    @Autowired
    protected bookRepository cRepo;

    @Autowired
    protected  roleRepository rRepo;

    public JSONArray getbookdata(){
        Iterable<book> booklist = cRepo.findAll();
        JSONArray res = new JSONArray();
        for (book b:booklist){
            JSONObject item = new JSONObject();
            item.accumulate("id",b.getId());
            item.accumulate("name",b.getName());
            item.accumulate("writer",b.getWriter());
            item.accumulate("price",b.getPrice());
            item.accumulate("publish",b.getPublish());
            item.accumulate("date",b.getDate());
            res.add(item);
        }
        return res;
    }

    public String modifydata(JSONObject data){
        book b = cRepo.findOne(data.getLong("ID"));
        b.setName(data.getString("name"));
        b.setPrice(data.getDouble("cost"));
        b.setWriter(data.getString("writer"));
        b.setDate(data.getString("date"));
        b.setPublish(data.getString("publish"));
        System.out.println(b.toString());
        cRepo.save(b);
    ;   return "modify success";
    }

    public String newdata(JSONObject data){
        book b = new book();
        b.setName(data.getString("name"));
        b.setPrice(data.getDouble("cost"));
        b.setWriter(data.getString("writer"));
        b.setDate(data.getString("date"));
        b.setPublish(data.getString("publish"));
        cRepo.save(b);
        return "new success";
    }

    public String register(JSONObject data){
        List<role> rlist = rRepo.findByEmail(data.getString("email"));
        if (!rlist.isEmpty()){
            return "Email is existed";
        }
        rlist = rRepo.findByNickname(data.getString("nickname"));
        if (!rlist.isEmpty()){
            return "Nickname is existed";
        }
        if (data.getString("nickname").indexOf("@")!=-1){
            return "Nickname invalid char:@";
        }
        role r = new role();
        r.setEmail(data.getString("email"));
        r.setNickname(data.getString("nickname"));
        r.setPassword(data.getString("password"));
        r.setAddress(data.getString("residence"));
        r.setPhone(data.getString("phone"));
        r.setWebsite(data.getString("website"));
        r.setType("user");
        rRepo.save(r);
        return "register success";
    }

    public String login(JSONObject data){
        List<role> rlist = rRepo.findByEmail(data.getString("userName"));
        if (rlist.isEmpty()){
            rlist = rRepo.findByNickname(data.getString("userName"));
        }
        if (rlist.isEmpty()){
            return "Username not exists";
        }
        role r = rlist.get(0);
        System.out.println(r.toString());
        if (r.getPassword().equals(data.getString("password"))){
            return r.getType();
        }else{
            return "Incorrect password";
        }
    }

    public String rememberLogin(String username,String password){
        List<role> rlist = rRepo.findByEmail(username);
        if (rlist.isEmpty()){
            rlist = rRepo.findByNickname(username);
        }
        if (rlist.isEmpty()){
            return "NotLogin";
        }
        role r = rlist.get(0);
        if (r.getPassword().equals(password)){
            return r.getType();
        }
        return "NotLogin";
    }

    public JSONObject getUserInfo(String username){
        List<role> rlist = rRepo.findByEmail(username);
        if (rlist.isEmpty()){
            rlist = rRepo.findByNickname(username);
        }
        if (rlist.isEmpty()){
            return null;
        }
        role r = rlist.get(0);
        JSONObject res = new JSONObject();
        res.put("email",r.getEmail());
        res.put("nickname",r.getNickname());
        res.put("phone",r.getPhone());
        res.put("website",r.getWebsite());
        res.put("usertype",r.getType());
        return res;
    }

    public String modifyUser(JSONObject data){
        List<role> rlist = rRepo.findByEmail(data.getString("email"));
        if (rlist.isEmpty()){
            return "Error";
        }
        role r = rlist.get(0);
        r.setPhone(data.getString("phone"));
        r.setWebsite(data.getString("website"));
        rRepo.save(r);
        return "Success";
    }

    public String modifyUserPwd(JSONObject data){
        List<role> rlist = rRepo.findByEmail(data.getString("email"));
        if (rlist.isEmpty()){
            return "Error:identify username";
        }
        role r = rlist.get(0);
        if (!data.getString("oldpwd").equals(r.getPassword())){
            return "Error:incorrect password";
        }
        if (!data.getString("newpwd1").equals(data.getString("newpwd2"))){
            return "Error:two different passwords";
        }
        r.setPassword(data.getString("newpwd1"));
        rRepo.save(r);
        return "Success";
    }

    public String deletedata(JSONObject data){
        book b = cRepo.findOne(data.getLong("ID"));
        cRepo.delete(b);
        return "delete success";
    }

    public void init(){
        System.out.println("startinit");
        System.out.println(this.cRepo);
        System.out.println("init lists");
        cRepo.save(new book("bookname","bookwriter",99.80,"2015.08.09","bookpublish"));
    }

    public void insert(){
        cRepo.save(new book("bookname1","bookwriter1",99.80,"2015.08.10","bookpublish1"));
        cRepo.save(new book("bookname2","bookwriter2",99.80,"2015.08.11","bookpublish2"));
        cRepo.save(new book("bookname3","bookwriter3",99.80,"2015.08.12","bookpublish3"));
        cRepo.save(new book("bookname4","bookwriter4",99.80,"2015.08.13","bookpublish4"));
    }

    public void modify(){
        book b = cRepo.findOne(3L);
        b.setName("newname333");
        System.out.println("modify end");
        cRepo.save(b);
    }

    public void select(){
        Iterable<book> blist = cRepo.findAll();
        for (book b:blist){
            System.out.println(b.toString());
        }
        System.out.println("findAll");
        List<book> blist2 = cRepo.findByName("newname333");
        for (book b:blist2){
            System.out.println(b.toString());
        }
        System.out.println("findByName");
    }

    public void delete(){
        Iterable<book> blist = cRepo.findByDate("2015.08.12");
        for (book b:blist){
            System.out.println(b.toString());
            cRepo.delete(b);
        }
        System.out.println("deleteByPublish");
    }
}
