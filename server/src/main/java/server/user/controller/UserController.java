package server.user.controller;

import com.sun.xml.bind.v2.TODO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.user.dto.UserDto;
import server.user.entity.User;
import server.user.mapper.UserMapper;
import server.user.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/users")
public class UserController {
    // service, mapper DI 필요
    private static final int SIZE = 36;
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/account-recovery")
    public ResponseEntity accountRecovery(@Valid @RequestBody UserDto.Post requestBody) {
        // TODO: requestBody 에 email 이 입력되면 email 을 userRepository 에서 찾아서 응답을 보내주는 로직 필요
        return null;
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUp(@Valid @RequestBody UserDto.Post requestBody) {
        // TODO: nickName, email, password 가 입력되면 email 이 중복인지 확인하고 가입이 가능하면 정상 응답, 아닌 경우는 예외를 보내는 로직
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserDto.Post requestBody) {
        // TODO: email, password 입력을 받고 둘 다 기존 userRepository 의 내용과 일치하면 정상 응답, 아닌 경우는 예외 처리
        return null;
    }

    @PatchMapping("/edit/{user-id}")
    public ResponseEntity patchUser(@PathVariable("user-id") long userId,
                                    @Valid @RequestBody UserDto.Patch requestBody) {
        // TODO: userId 를 입력받고 requestBody 에 포함된 값들만 수정 후에 응답을 반환하거나 예외 처리
        return null;
    }

    @GetMapping("/{user-id}")
    public ResponseEntity getUser(@PathVariable("user-id") long userId) {
        // TODO: userId 를 입력받아서 UserDto.Response 에 있는 값을 응답으로 반환하거나 예외 처리
        return null;
    }

    @GetMapping
    public ResponseEntity getUsers(@Positive @RequestParam int page,
                                   @Positive @RequestParam String tab) {
        // TODO: page 와 tab 을 입력받아서 abc 순서(디폴트) 또는 reputation 기준으로 정렬하여 페이지네이션을 응답으로 반환하거나 예외 처리
        return null;
    }

    @DeleteMapping("/delete/{user-id}")
    public ResponseEntity deleteUser(@PathVariable("user-id") long userId) {
        // TODO: userId 와 일치하는 User 를 삭제한다.
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
