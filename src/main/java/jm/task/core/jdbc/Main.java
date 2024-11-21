package jm.task.core.jdbc;
import java.util.List;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Sasha", "Gagiev", (byte) 32);
        userService.saveUser("Sasha", "Gagiev", (byte) 32);
        userService.saveUser("Svsha", "Gbiev", (byte) 52);
        userService.saveUser("Sgsha", "Gviev", (byte) 22);
        userService.saveUser("Sesha", "Ggiev", (byte) 12);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }

}
