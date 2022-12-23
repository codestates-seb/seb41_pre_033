package server.tag.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


public class TagDto {
    public static class Response {
        String tagName;
        String tagExplanation;
    }
}