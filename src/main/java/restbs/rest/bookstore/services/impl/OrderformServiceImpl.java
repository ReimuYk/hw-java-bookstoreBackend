package restbs.rest.bookstore.services.impl;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restbs.rest.bookstore.dao.bookRepository;
import restbs.rest.bookstore.model.book;
import restbs.rest.bookstore.model.role;
import restbs.rest.bookstore.model.orderform;
import restbs.rest.bookstore.dao.orderformRepository;
import restbs.rest.bookstore.dao.roleRepository;
import restbs.rest.bookstore.services.OrderformService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class OrderformServiceImpl implements OrderformService {
    @Autowired
    protected orderformRepository oRepo;

    @Autowired
    protected roleRepository rRepo;

    @Autowired
    protected bookRepository cRepo;

    public String createOrder(JSONObject data){
        Date day = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(day);
        String chars = "abcdefghijklmnopqrstuvwxyz";
        for (int i=0;i<5;i++){
            dateString = chars.charAt((int)(Math.random()*26))+dateString;
        }

        String username = data.getString("username");
        List<role> userlist = rRepo.findByEmail(username);
        if (userlist.isEmpty()){
            userlist = rRepo.findByNickname(username);
        }
        if (userlist.isEmpty()){
            return "Error: No such user";
        }
        role user = userlist.get(0);
        Long userid = user.getId();
        JSONArray booklist = data.getJSONArray("booklist");
        int l = booklist.size();
        for (int i=0;i<l;i++){
            JSONObject item = booklist.getJSONObject(i);
            Long bookid = item.getLong("bookid");
            int num = item.getInt("num");
            //update num
            List<book> temp = cRepo.findById(bookid);
            book tb = temp.get(0);
            int leftnum = tb.getLeftnum();
            if (leftnum>=num){
                tb.setLeftnum(leftnum-num);
            }
            cRepo.save(tb);
            //update num end
            double single = item.getDouble("price");
            orderform of = new orderform();
            of.setOrderid(dateString);
            of.setUserid(userid);
            of.setBookid(bookid);
            of.setNum(num);
            of.setSingleprice(single);
            oRepo.save(of);
        }

        return "Success";
    }

    public JSONArray getUserList(String username){
        List<role> userlist = rRepo.findByEmail(username);
        if (userlist.isEmpty()){
            userlist = rRepo.findByNickname(username);
        }
        if (userlist.isEmpty()){
            System.out.println("no such user");
            return null;
        }
        role user = userlist.get(0);
        Long userid = user.getId();
        System.out.println("userid"+userid);
        List<orderform> orderlist = oRepo.findByUserid(userid);
        HashSet<String> orderidSet = new HashSet();
        for (orderform of:orderlist){
            orderidSet.add(of.getOrderid());
        }
        JSONArray res = new JSONArray();
        for (String orderid:orderidSet){
            JSONObject item = new JSONObject();
            item.put("orderid",orderid);
            JSONArray orderdata = new JSONArray();
            for (orderform of:orderlist){
                if (of.getOrderid().equals(orderid)){
                    JSONObject dataitem = new JSONObject();
                    dataitem.put("bookid",of.getBookid());
                    dataitem.put("num",of.getNum());
                    dataitem.put("price",of.getSingleprice());
                    orderdata.add(dataitem);
                }
            }
            item.put("orderdata",orderdata);
            res.add(item);
        }
        return res;
    }

    public JSONArray getorderdata(){
        Iterable<orderform> orderlist = oRepo.findAll();
        JSONArray res = new JSONArray();
        for (orderform ofm:orderlist){
            JSONObject item = new JSONObject();
            item.accumulate("id",ofm.getId());
            String orderid = ofm.getOrderid();
            item.accumulate("orderid",orderid);
            item.accumulate("ordertime",orderid.substring(5,17));
            item.accumulate("singleprice",ofm.getSingleprice());
            item.accumulate("num",ofm.getNum());
            Long bookid = ofm.getBookid();
            book tb = cRepo.findById(bookid).get(0);
            item.accumulate("bookid",bookid);
            item.accumulate("bookname",tb.getName());
            item.accumulate("bookcategory",tb.getCategory());
            item.accumulate("bookwriter",tb.getWriter());
            Long roleid = ofm.getUserid();
            role tr = rRepo.findById(roleid).get(0);
            item.accumulate("userid",roleid);
            item.accumulate("username",tr.getNickname());
            res.add(item);
        }
        return res;
    }

    public JSONArray getSelectData(String timedown,String timeup,String category,String username,String writername){
        Iterable<orderform> orderlist = oRepo.findAll();
        JSONArray res = new JSONArray();
        System.out.println(timedown);
        System.out.println(timeup);
        System.out.println(category);
        System.out.println(username);
        System.out.println(writername);
        for (orderform ofm:orderlist){
            JSONObject item = new JSONObject();
            item.accumulate("id",ofm.getId());
            String orderid = ofm.getOrderid();
            item.accumulate("orderid",orderid);
            String ordertime = orderid.substring(5,17);
            item.accumulate("ordertime",ordertime);
            item.accumulate("singleprice",ofm.getSingleprice());
            item.accumulate("num",ofm.getNum());
            Long bookid = ofm.getBookid();
            book tb = cRepo.findById(bookid).get(0);
            item.accumulate("bookid",bookid);
            item.accumulate("bookname",tb.getName());
            item.accumulate("bookcategory",tb.getCategory());
            item.accumulate("bookwriter",tb.getWriter());
            Long roleid = ofm.getUserid();
            role tr = rRepo.findById(roleid).get(0);
            item.accumulate("userid",roleid);
            item.accumulate("username",tr.getNickname());
            String bookcategory = tb.getCategory();
            String bookwriter = tb.getWriter();
            String bookuser = tr.getNickname();
            if ((timeup.equals("")||timeup.compareTo(ordertime.substring(0,timeup.length()))<=0)
                    && (timedown.equals("")||timedown.compareTo(ordertime.substring(0,timedown.length()))>=0)
                    && (category.equals("")||bookcategory.equals(category))
                    && (username.equals("")||bookuser.equals(username))
                    && (writername.equals("")||bookwriter.equals(writername))) {
                res.add(item);
            }
        }
        return res;
    }
}
