package server.answer.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import springfox.documentation.annotations.ApiIgnore;

public class AnswerDto {
    @Getter
    public static class PostAnswer{
        @ApiModelProperty(example = "답변 작성자 id")
        private Long userId;

        @ApiModelProperty(example = "답변 본문")
        private String body;
    }

    @Getter
    public static class PatchAnswer{
        @ApiModelProperty(example = "path variable로 입력되므로 입력x")
        private Long answerId;

        @ApiModelProperty(example = "수정을 요청하는 유저의 id")
        private Long userId;

        @ApiModelProperty(example = "수정된 답변 본문")
        private String body;

        public void setAnswerId(Long answerId) {
            this.answerId = answerId;
        }
    }

    @Getter
    public static class PatchVote{
        @ApiModelProperty(example = "투표 요청을 보내는 유저의 id")
        private long userId;

        @ApiModelProperty(example = "path variable로 입력되므로 입력x")
        private long answerId;
        public void setAnswerId(long answerId){this.answerId = answerId;}
    }
    @Getter
    public static class PatchAccept{
        @ApiModelProperty(example = "채택 요청을 보내는 유저의 id")
        private long userId;
        @ApiModelProperty(example = "path variable로 입력되므로 입력x")
        private long answerId;
        public void setAnswerId(long answerId){this.answerId = answerId;}
    }

    @Getter
    public static class DeleteAnswer{
        @ApiModelProperty(example = "삭제 요청을 보내는 유저의 id")
        private long userId;
    }

    @Getter
    @Setter
    @Builder
    public static class Response{
        @ApiModelProperty(example = "답변 id")
        private Long answerId;
        @ApiModelProperty(example = "답변 본문")
        private String body;
        @ApiModelProperty(example = "채택 여부")
        private Boolean accepted;
        @ApiModelProperty(example = "투표 수")
        private int vote;
        @ApiModelProperty(example = "답변 작성자 id")
        private Long userId;
        @ApiModelProperty(example = "답변 대상자 닉네임")
        private String nickname;
        @ApiModelProperty(example = "답변 대상 질문 id")
        private Long questionId;
    }
}
