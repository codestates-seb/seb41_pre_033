package server.user.dto;

import lombok.Builder;
import lombok.Getter;
@Builder
@Getter
public class UserTagResponseDto {
    private String tagName;
    private String explanation;
}
