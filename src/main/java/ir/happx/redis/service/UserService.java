package ir.happx.redis.service;

import org.springframework.stereotype.Service;

import ir.happx.redis.domain.BUser;
import ir.happx.redis.repository.UserRepository;

@Service
public class UserService  {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public BUser addUser(BUser BUser){
        return userRepository.save(BUser);
    }
}
