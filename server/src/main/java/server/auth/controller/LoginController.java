//package server.auth.controller;

//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import server.auth.dto.LoginDto;
//
//import server.auth.userdetails.UsersDetailsService;
//import server.dto.SingleResponseDto;

//@RestController
//@RequestMapping
//public class LoginController {
//    private final UsersDetailsService usersDetailsService;
//    private final LoginMapper loginMapper;
//
//    public LoginController(UsersDetailsService usersDetailsService, LoginMapper loginMapper) {
//        this.usersDetailsService = usersDetailsService;
//        this.loginMapper = loginMapper;
//    }

//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody LoginDto requestBody) {
//        UserDetails userDetails = usersDetailsService.loadUserByUsername(requestBody.getUsername());
//        return new ResponseEntity<>(
//                new SingleResponseDto<>(loginMapper.userDetailsToLoginDtoResponse(userDetails))
//                , HttpStatus.ACCEPTED);
//    }
//}
