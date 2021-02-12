package ma.enset.securityservice;

import ma.enset.securityservice.entities.Role;
import ma.enset.securityservice.entities.User;
import ma.enset.securityservice.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class SecurityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecurityServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AccountService accountService){
        return args -> {
            accountService.addRole(new Role(null,"USER"));
            accountService.addRole(new Role(null,"ADMIN"));
            accountService.addRole(new Role(null,"CUSTOMER_MANAGER"));
            accountService.addRole(new Role(null,"PRODUCT_MANAGER"));
            accountService.addRole(new Role(null,"BILLS_MANAGER"));

            accountService.addUser(new User(null,"user1","1234",new ArrayList<>()));
            accountService.addUser(new User(null,"admin","1234",new ArrayList<>()));
            accountService.addUser(new User(null,"user2","1234",new ArrayList<>()));
            accountService.addUser(new User(null,"user3","1234",new ArrayList<>()));
            accountService.addUser(new User(null,"admin2","1234",new ArrayList<>()));

            accountService.addRoleToUser("user1","USER");
            accountService.addRoleToUser("admin","USER");
            accountService.addRoleToUser("admin","ADMIN");
            accountService.addRoleToUser("user2","CUSTOMER_MANAGER");

        };
    }
}
