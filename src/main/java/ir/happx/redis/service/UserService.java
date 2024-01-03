package ir.happx.redis.service;

import org.springframework.stereotype.Service;

import java.util.List;

import ir.happx.redis.domain.BUser;
import ir.happx.redis.repository.UserRepository;
import ir.happx.redis.util.Dictionary;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<BUser> getUsers(){
        return userRepository.findAll();
    }
    public BUser addUser(BUser user) {
        return userRepository.save(user);
    }

    public String editUser(BUser user) {
        userRepository.findBUserById(user.getId());
        if (user == null) {
            return Dictionary.message("userNotFuondMessage");
        }
        userRepository.save(user);
        return Dictionary.message("successMessage");
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }
}
