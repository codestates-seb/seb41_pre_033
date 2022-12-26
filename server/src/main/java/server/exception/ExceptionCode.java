package server.exception;

import lombok.Getter;

public enum ExceptionCode {
    CANNOT_CHANGE_QUESTION(403, "Answered question can not be changed"),
    TAG_NOT_FOUND(404, "Tag not found"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    CANNOT_DELETE_QUESTION(400, "Answered question can not be deleted"),
    ACCESS_DENIED(401, "Only author can edit" ),
    ANSWER_NOT_FOUND(404, "Answer not found" ),
    CANNOT_MODIFY_ANSWER(403,"Accepted answer can not be modified" ),
    CANNOT_DELETE_ANSWER(403, "Accepted answer can not be deleted" );


    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
