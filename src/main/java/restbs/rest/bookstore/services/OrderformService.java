package restbs.rest.bookstore.services;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restbs.rest.bookstore.model.role;
import restbs.rest.bookstore.model.orderform;
import restbs.rest.bookstore.dao.orderformRepository;
import restbs.rest.bookstore.dao.roleRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public interface OrderformService {
    public String createOrder(JSONObject data);

    public JSONArray getUserList(String username);

    public JSONArray getorderdata();

    public JSONArray getSelectData(String timedown,String timeup,String category,String username,String writername);
}
