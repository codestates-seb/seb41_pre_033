package server.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import server.exception.BusinessLogicException;
import server.exception.ExceptionCode;
import server.user.entity.User;
import server.user.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        verifyExistsEmail(user.getEmail());

        // Password 암호화
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public User findUser(long userId) {
        return findVerifiedUser(userId);
    }

    public Page<User> findUsers(int page, int size, String tab) {
        if (tab.equals("reputation")) {
            return userRepository.findAll(PageRequest.of(page, size,
                    Sort.by("reputation").descending()));
        }
        return userRepository.findAll(PageRequest.of(page, size,
                Sort.by("nickname").ascending()));
    }

    public User updateUser(User user) {
        User findUser = findVerifiedUser(user.getUserId());

        Optional.ofNullable(user.getPassword())
                .ifPresent(findUser::setPassword);
        Optional.ofNullable(user.getNickname())
                .ifPresent(findUser::setNickname);
        Optional.ofNullable(user.getCountry())
                .ifPresent(findUser::setCountry);
        Optional.ofNullable(user.getReputation())
                .ifPresent(findUser::setReputation);
        Optional.ofNullable(user.getTitle())
                .ifPresent(findUser::setTitle);
        Optional.ofNullable(user.getIntroduction())
                .ifPresent(findUser::setIntroduction);
        Optional.ofNullable(user.getLink())
                .ifPresent(findUser::setLink);

        return userRepository.save(findUser);
    }

    public void deleteUser(long userId) {
        User findUser = findVerifiedUser(userId);

        userRepository.delete(findUser);
    }

    @Transactional(readOnly = true)
    public User findVerifiedUser(long userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    private void verifyExistsEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent())
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
    }
}
