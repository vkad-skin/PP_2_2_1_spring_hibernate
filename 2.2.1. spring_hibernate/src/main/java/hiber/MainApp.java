package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru"
                , new Car("BMW", 5));
        User user2 = new User("User2", "Lastname2", "user2@mail.ru"
                , new Car("Opel", 12));
        User user3 = new User("User3", "Lastname3", "user3@mail.ru"
                , new Car("MB", 1));
        User user4 = new User("User4", "Lastname4", "user4@mail.ru"
                , new Car("BMW", 7));

        userService.add(user1);
        userService.add(user2);
        userService.add(user3);
        userService.add(user4);

        System.out.println();

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println(user);
        }

        User findUser = userService.findUserByCar(new Car("Opel", 12));

        System.out.println(findUser);

        context.close();
    }
}
