package restbs.rest.bookstore;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class datactrl {
    @Autowired
    protected CustomerRepository cRepo;

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
