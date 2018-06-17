package restbs.rest.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class book {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String writer;
    private double price;
    private String date;
    private String publish;
    private String category;
    private int leftnum;

    public book() {}

    public book(String name, String writer,double price,String date,String publish,String category,int leftnum) {
        this.name=name;
        this.writer=writer;
        this.price=price;
        this.date=date;
        this.publish=publish;
        this.category = category;
        this.leftnum = leftnum;
    }

    public void setName(String name){ this.name = name; }
    public void setWriter(String writer){ this.writer = writer; }
    public void setPrice(double price){ this.price = price; }
    public void setDate(String date){ this.date = date; }
    public void setPublish(String publish){ this.publish = publish; }
    public void setLeftnum(int leftnum){ this.leftnum = leftnum;}
    public void setCategory(String category){ this.category  = category;}

    public Long getId(){ return this.id;}
    public String getName(){ return this.name; }
    public String getWriter() {return this.writer;}
    public double getPrice() { return this.price;}
    public String getDate() { return this.date;}
    public String getPublish() { return this.publish;}
    public int getLeftnum() { return this.leftnum;}
    public String getCategory() { return this.category;}

    @Override
    public String toString() {
        return String.format(
                "book[id=%d, 书名='%s', 作者='%s', 价格=%.2f, 出版日期='%s', 出版社='%s']",
                id, name, writer, price, date, publish);
    }



}
