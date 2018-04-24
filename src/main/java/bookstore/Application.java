//package bookstore;
//
//import org.slf4j.Logger;
//
//import org.slf4j.LoggerFactory;
//
//import org.springframework.boot.CommandLineRunner;
//
//import org.springframework.boot.SpringApplication;
//
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import org.springframework.context.annotation.Bean;
//
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//
//
//
//@SpringBootApplication
//@EnableJpaRepositories
//public class Application {
//    private static final Logger log = LoggerFactory.getLogger(Application.class);
//
//    public static void main(String[] args) {
//        SpringApplication.run(Application.class);
//    }
//
//
//    /**
//
//     * 返回CommandLineRunner的Bean，在spring boot启动前加载并且执行
//
//     *
//
//     * @param repository
//
//     * @return
//
//     */
//
//    @Bean
//    public CommandLineRunner demo(CustomerRepository repository) {
//
//        return (args) -> {
//
//            // save a couple of customers
//            repository.save(new tt("Jack","Bauer"));
//            repository.save(new tt("Chloe","O'Brian"));
//            repository.save(new tt("Kim","Bauer"));
//            repository.save(new tt("David","Palmer"));
//            repository.save(new tt("Michelle","Dessler"));
//
//            // fetch all customers
//
//            log.info("Customers found with findAll():");
//
//            log.info("-------------------------------");
//
//            for (tt customer : repository.findAll()) {
//
//                log.info(customer.toString());
//                System.out.println(customer.toString());
//
//            }
//
//            log.info("");
//
//
//
//            // fetch an individual customer by ID
//
//            tt customer = repository.findOne(1L);
//
//            log.info("Customer found with findOne(1L):");
//
//            log.info("--------------------------------");
//
//            log.info(customer.toString());
//
//            log.info("");
//
//
//
//            // fetch customers by last name
//
//            log.info("Customer found with findByLastName('Bauer'):");
//
//            log.info("--------------------------------------------");
//
//            for (tt rbauer : repository.findByLastName("Bauer")) {
//
//                log.info(rbauer.toString());
//
//            }
//
//
//
//            // fetch customers by last name
//
//            log.info("Customer found with findByFirstName('David'):");
//
//            log.info("--------------------------------------------");
//
//            for (tt bauer : repository.findByFirstName("David")) {
//
//                log.info(bauer.toString());
//
//            }
//
//            log.info("");
//
//        };
//
//    }
//
//
//
//}