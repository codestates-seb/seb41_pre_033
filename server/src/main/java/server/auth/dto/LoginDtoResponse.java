package server.auth.dto;

import lombok.Getter;

@Getter
public class LoginDtoResponse {
    private String accessToken;
    private String refreshToken;
}
