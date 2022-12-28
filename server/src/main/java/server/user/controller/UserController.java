package server.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.dto.MultiResponseDto;
import server.dto.SingleResponseDto;
import server.user.dto.MailDto;
import server.user.dto.UserDto;
import server.user.dto.UserPasswordDto;
import server.user.entity.User;
import server.user.mapper.UserMapper;
import server.user.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Api(tags = { "User Controller" })
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

    @ApiOperation(value = "사용자 비밀번호 찾기", notes = "사용자가 입력한 이메일로 임시 비밀번호를 발송한다.")
    @PostMapping("/account-recovery")
    public void accountRecovery(@ApiParam(value = "이메일 주소 입력") @RequestBody MailDto mailDto) {
//         TODO: requestBody 에 email 이 입력되면 email 을 userRepository 에서 찾아서 응답을 보내주는 로직 필요
//         TODO: 사실 보안상으로는 존재하지 않는 이메일을 표시해주는 것보다 이메일을 보내는 데 성공했다고만 표시해주는 것이 좋다.
        userService.sendMail(mailDto.getEmail());
    }

    @ApiOperation(value = "회원 가입", notes = "닉네임, 이메일, 비밀번호를 입력하여 회원 정보를 등록한다.")
    @PostMapping("/sign-up")
    public ResponseEntity signUp(@ApiParam(value = "닉네임, 이메일, 비밀번호를 입력") @Valid @RequestBody UserDto.Post requestBody) {
        // TODO: nickName, email, password 가 입력되면 email 이 중복인지 확인하고 가입이 가능하면 정상 응답, 아닌 경우는 예외를 보내는 로직
        User user = userMapper.userPostToUser(requestBody);
        User createdUser = userService.createUser(user);

        return new ResponseEntity<>(
                new SingleResponseDto<>(userMapper.userToUserResponse(createdUser)),
                HttpStatus.CREATED
        );
    }

    // TODO: email, password 입력을 받고 둘 다 기존 userRepository 의 내용과 일치하면 정상 응답, 아닌 경우는 예외 처리
    // TODO: login 은 config 패키지의 SecurityConfiguration 이 담당한다.

    @ApiOperation(value = "회원 정보 수정", notes = "닉네임, 국가, 소제목, 자기소개, 개인 사이트 링크, 태그 중 변경하고 싶은 정보를 수정할 수 있다.")
    @PatchMapping("/edit/{user-id}")
    public ResponseEntity patchUser(@ApiParam(value = "user-id 값 입력") @PathVariable("user-id") long userId,
                                    @ApiParam(value = "닉네임, 국가, 소제목, 자기소개, 개인 사이트 링크, 태그 중 변경하고 싶은 정보를 입력") @Valid @RequestBody UserDto.Patch requestBody) {
        // TODO: userId 를 입력받고 requestBody 에 포함된 값들만 수정 후에 응답을 반환하거나 예외 처리
        requestBody.setUserId(userId);
        User user = userService.updateUser(userMapper.userPatchToUser(requestBody));

        return new ResponseEntity<>(
                new SingleResponseDto<>(userMapper.userToUserResponse(user))
                , HttpStatus.OK);
    }
    @ApiOperation(value = "비밀번호 변경", notes = "현재 비밀번호, 바꿀 비밀번호, 바꿀 비밀번호를 체크할 비밀번호를 입력받아서 user-id에 해당하는 회원의 비밀번호를 변경할 수 있다.")
    @PatchMapping("/edit/{user-id}/change-password")
    public ResponseEntity changePassword(@ApiParam(value = "user-id 값 입력") @PathVariable("user-id") long userId,
                                         @ApiParam(value = "현재 비밀번호, 바꿀 비밀번호, 바꿀 비밀번호를 체크할 비밀번호") @Valid @RequestBody UserPasswordDto requestBody) {
        // TODO 비즈니스 로직 작성 필요
        String currentPassword = requestBody.getCurrentPassword();
        String newPassword = requestBody.getNewPassword();
        String checkPassword = requestBody.getCheckPassword();

        userService.updatePassword(userId, currentPassword, newPassword, checkPassword);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "회원 정보 조회", notes = "user-id에 해당하는 회원의 정보를 출력한다.")
    @GetMapping("/{user-id}")
    public ResponseEntity getUser(@ApiParam(value = "user-id 값 입력") @PathVariable("user-id") long userId) {
        // TODO: userId 를 입력받아서 UserDto.Response 에 있는 값을 응답으로 반환하거나 예외 처리
        User user = userService.findUser(userId);
        return new ResponseEntity<>(
                new SingleResponseDto<>(userMapper.userToUserResponse(user))
                , HttpStatus.OK);
    }

    @ApiOperation(value = "전체 회원 정보 조회", notes = "page 번호와 정렬 기준을 tab으로 받아서 기본으로는 알파벳순 또는 reputation순으로 정렬하여 페이지네이션을 응답으로 반환한다.")
    @GetMapping
    public ResponseEntity getUsers(@ApiParam(value = "page 번호 입력") @Positive @RequestParam int page,
                                   @ApiParam(value = "abc(default), reputation 중 하나를 선택해서 입력") @Positive @RequestParam String tab) {
        // TODO: page 와 tab 을 입력받아서 abc 순서(디폴트) 또는 reputation 기준으로 정렬하여 페이지네이션을 응답으로 반환하거나 예외 처리
        Page<User> pageUsers = userService.findUsers(page - 1, SIZE, tab);
        List<User> users = pageUsers.getContent();
        return new ResponseEntity<>(
                new MultiResponseDto<>(userMapper.usersToUserResponses(users),
                        pageUsers),
                HttpStatus.OK);
    }

    @ApiOperation(value = "회원 탈퇴", notes = "user-id에 해당하는 회원의 정보를 삭제한다.")
    @DeleteMapping("/delete/{user-id}")
    public ResponseEntity deleteUser(@ApiParam(value = "user-id 값 입력") @PathVariable("user-id") @Positive long userId) {
        // TODO: userId 와 일치하는 User 를 삭제한다.
        userService.deleteUser(userId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
