package ir.happx.redis.service;

import org.springframework.stereotype.Service;

import ir.happx.redis.domain.BUser;
import ir.happx.redis.repository.UserRepository;
import ir.happx.redis.util.Dictionary;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BUser addUser(BUser BUser) {
        return userRepository.save(BUser);
    }

    public String editUser(BUser bUser) {
        userRepository.findBUserById(bUser.getId());
        if (bUser == null) {
            userRepository.save(bUser);
        }
        throw Dictionary.exception("userNotFuond");
    }
}
