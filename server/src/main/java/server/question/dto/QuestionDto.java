package server.question.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import server.answer.dto.AnswerDto;
import server.user.entity.User;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {
    @Getter
    public static class PostQuestion {
        private long userId;

        @NotBlank
        private String title;

        @NotBlank
        private String body;

        private List<QuestionTagDto> questionTags;
    }
    @Getter
    public static class PatchQuestion{
        private long userId;
        private long questionId;
        private String title;
        private String body;
        private int bounty;
        private List<QuestionTagDto> questionTags;

        public void setQuestionId(long questionId) {
            this.questionId = questionId;
        }
    }

    @Getter
    public static class PatchQuestionVote {
        private long userId;
        private long questionId;

        public void setQuestionId(long questionId) {
            this.questionId = questionId;
        }
    }
    @Getter
    @Setter
    public static class ResponseQ{
        @ApiModelProperty(example = "질문 id")
        private long questionId;
        @Setter(AccessLevel.NONE)
        @ApiModelProperty(example = "작성자 id")
        private long userId;

        @ApiModelProperty(example = "작성자 닉네임")
        private String nickname;

        @ApiModelProperty(example = "제목")
        private String title;

        @ApiModelProperty(example = "본문")
        private String body;

        @ApiModelProperty(example = "바운티")
        private int bounty;

        @ApiModelProperty(example = "작성일")
        private LocalDateTime created;

        @ApiModelProperty(example = "조회수")
        private int viewed;

        @ApiModelProperty(example = "투표수")
        private int vote;

        @ApiModelProperty(example = "적용된 태그들")
        private List<QuestionTagResponseDto> questionTags;

        @ApiModelProperty(example = "답변들")
        private List<AnswerDto.Response> answers;

        public void setUserId(User user){
            this.userId = user.getUserId();
        }
    }
}
