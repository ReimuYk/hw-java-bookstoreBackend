package restbs.rest.bookstore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class orderform {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String orderid;
    private Long userid;
    private Long bookid;
    private int num;
    private double singleprice;

    public orderform(){};

    public orderform(String orderid, Long userid, Long bookid, int num, double singleprice){
        this.orderid = orderid;
        this.userid = userid;
        this.bookid = bookid;
        this.num = num;
        this.singleprice = singleprice;
    }

    public void setOrderid(String orderid){this.orderid = orderid;}
    public void setUserid(Long userid){this.userid = userid;}
    public void setBookid(Long bookid){this.bookid = bookid;}
    public void setNum(int num){this.num = num;}
    public void setSingleprice(double singleprice){this.singleprice = singleprice;}

    public String getOrderid(){return this.orderid;}
    public Long getUserid(){return this.userid;}
    public Long getBookid(){return this.bookid;}
    public int getNum(){return this.num;}
    public double getSingleprice(){return this.singleprice;}
}
