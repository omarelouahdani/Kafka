package ma.enset.securityservice.service;

import ma.enset.securityservice.entities.Role;
import ma.enset.securityservice.entities.User;

import java.util.List;

public interface AccountService {
    User addUser(User user);
    Role addRole(Role role);
    void addRoleToUser(String userName,String roleName);
    User loadUserByUserNmae(String userName);
    List<User> listUsers();
}
