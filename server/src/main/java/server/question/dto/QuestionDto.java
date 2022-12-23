package server.question.dto;

import lombok.Getter;
import server.question.entity.QuestionTag;

import java.util.ArrayList;
import java.util.List;

public class QuestionDto {
    @Getter
    public static class Post {
        //question_title, question_body, [tag1, tag2, tag3…]
        private String questionTitle;

        private String questionBody;

        private List<QuestionTagDto> questionTagDtos = new ArrayList<>();
        //db에 미리 태그를 넣어놓는다
        //tags -> tagName 이랑 비교해서 맞는거 호출해서 question 에 연결해준다?
    }
    @Getter
    public static class Patch {

    }
    @Getter
    public static class Response {

    }
}
