package restbs.rest.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.print.DocFlavor;

@Entity
public class role {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String email;
    private String nickname;
    private String password;
    private String address;
    private String phone;
    private String website;
    private String type;
    private int valid;

    public role() {}

    public role(String email,String nickname, String password, String address, String phone, String website) {
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.type = "user";
        this.valid = 1;
    }

    public void setEmail(String email){ this.email = email; }
    public void setNickname(String nickname){ this.nickname = nickname; }
    public void setPassword(String password) { this.password = password;}
    public void setAddress(String address){this.address = address;}
    public void setPhone(String phone){ this.phone = phone;}
    public void setWebsite(String website){ this.website = website;}
    public void setType(String type){this.type = type;}
    public void setValid(int valid){this.valid = valid;}

    public Long getId(){ return this.id;}
    public String getEmail(){ return this.email; }
    public String getNickname(){ return this.nickname;}
    public String getPassword(){ return this.password;}
    public String getAddress(){ return this.address;}
    public String getPhone(){return this.phone;}
    public String getWebsite(){return this.website;}
    public String getType(){return this.type;}
    public int getValid(){return this.valid;}


    @Override
    public String toString() {
        return String.format(
                "role[id=%d, 昵称='%s', 密码='%s', 地址='%s', 电话='%s', 主页='%s']",
                id, nickname, password, address, phone, website);
    }
}
