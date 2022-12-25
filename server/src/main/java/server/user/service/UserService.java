package server.user.service;

import org.springframework.stereotype.Service;
import server.user.entity.User;
import server.user.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void deleteUser(long userId) {
        userRepository.deleteById(userId);
    }

    public void findVerifiedUser(Long userId) {
    }
}
