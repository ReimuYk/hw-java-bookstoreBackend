package restbs.rest.bookstore.services;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import restbs.rest.bookstore.model.book;
import restbs.rest.bookstore.dao.bookRepository;
import restbs.rest.bookstore.model.role;
import restbs.rest.bookstore.dao.roleRepository;

public interface BookService {

    public JSONArray getbookdata();

    public String modifydata(JSONObject data);

    public String newdata(JSONObject data);

    public String register(JSONObject data);

    public String login(JSONObject data);

    public String rememberLogin(String username,String password);

    public JSONObject getUserInfo(String username);

    public String modifyUser(JSONObject data);

    public String modifyUserPwd(JSONObject data);

    public String deletedata(JSONObject data);

    public void init();

    public void insert();

    public void modify();

    public void select();

    public void delete();

}
