package server.tag.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

public class TagManagerDto {
    @Getter
    public static class ForQuestion{
        @NotBlank
        private String tagName;
    }
}
