package restbs.rest.bookstore;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Service
public class orderformCtrl {
    @Autowired
    protected  orderformRepository oRepo;

    @Autowired
    protected roleRepository rRepo;

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
}
