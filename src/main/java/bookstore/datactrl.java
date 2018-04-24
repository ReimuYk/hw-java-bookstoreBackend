package bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import bookstore.CustomerRepository;

import javax.transaction.Transactional;

@Service
public class datactrl {
    @Autowired
    protected CustomerRepository cRepo;

    public void init(){
        System.out.println("startinit");
        System.out.println(this.cRepo);
        System.out.println("init lists");
        cRepo.save(new book("bookname","bookwriter",99.80,"2015.08.09","bookpublish"));
    }
}
