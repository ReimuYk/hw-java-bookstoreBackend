package restbs.rest.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class bookImg {
    @Id
    private String id;
    private Long bookid;
    private String image;

    public bookImg(){};

    public bookImg(Long bookid,String image){
        this.bookid = bookid;
        this.image = image;
    }

    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getBookid(){
        return this.bookid;
    }

    public String getImage(){
        return this.image;
    }
}
