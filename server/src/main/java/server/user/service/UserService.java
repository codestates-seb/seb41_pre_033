package server.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import server.answer.repository.AnswerRepository;
import server.answer.service.AnswerService;
import server.exception.BusinessLogicException;
import server.exception.ExceptionCode;
import server.question.repository.QuestionRepository;
import server.question.service.QuestionService;
import server.tag.entity.Tag;
import server.tag.repository.TagRepository;
import server.tag.service.TagService;
import server.user.entity.User;
import server.user.entity.UserTag;
import server.user.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final TagRepository tagRepository;



    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JavaMailSender mailSender,
                       TagRepository tagRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSender = mailSender;
        this.tagRepository = tagRepository;
    }

    public User createUser(User user) {
        verifyExistsEmail(user.getEmail());
        user.setReputation(0);
        // Password 암호화
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    public void sendMail(String email) {
        String tempPassword = getTempPassword();

        // 비밀번호를 변경해주어야 한다.
        // email로 User를 찾아서 저장
        User findUser = findUserByEmail(email);

        // 비번 변경
        findUser.setPassword(passwordEncoder.encode(tempPassword));

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no_reply@chaning.com");
        message.setTo(email);
        message.setSubject("[Stackoverflow] 임시 비밀번호 안내입니다.");
        message.setText(
                "귀하의 임시 비밀번호는 " + tempPassword + "입니다. \n알아서 변경하세요. ^_^"
        );
        mailSender.send(message);
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
        Optional.ofNullable(user.getUserTags())
                .ifPresent(findUser::setUserTags);

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

    // 이메일과 일치하는 user를 찾아서 반환
    private User findUserByEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.orElseThrow(() ->
                new BusinessLogicException(ExceptionCode.USER_NOT_FOUND));
    }

    // 가입시 중복 이메일 확인
    private void verifyExistsEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent())
            throw new BusinessLogicException(ExceptionCode.USER_EXISTS);
    }

    private String getTempPassword() {
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        StringBuilder str = new StringBuilder();

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str.append(charSet[idx]);
        }
        return str.toString();
    }
}
