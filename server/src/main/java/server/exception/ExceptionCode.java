package server.exception;

import lombok.Getter;

public enum ExceptionCode {
    CANNOT_CHANGE_QUESTION(403, "Answered question can not be changed"),
    TAG_NOT_FOUND(404, "Tag not found"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    CANNOT_DELETE_QUESTION(400, "Answered question can not be deleted"),
    ANSWER_NOT_FOUND(404, "Answer not found" ),
    CANNOT_MODIFY_ANSWER(403,"Accepted answer can not be modified" ),
    CANNOT_DELETE_ANSWER(403, "Accepted answer can not be deleted" ),
    ACCESS_DENIED(401, "Access denied"),
    USER_NOT_FOUND(404, "User not found"),
    USER_EXISTS(409, "User exists"),
    SAME_PASSWORD(400, "Current Password and New Password must be different"),
    DIFFERENT_PASSWORD(400, "New Password and Check Password must be the same");
    

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
