package restbs.rest.bookstore.dao;

import restbs.rest.bookstore.model.bookImg;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import restbs.rest.bookstore.model.orderform;

import java.util.List;

public interface bookImgRepository extends MongoRepository<bookImg,String>  {
    List<bookImg> findByBookid(Long bookid);
}
