package server.exception;

import lombok.Getter;

public enum ExceptionCode {
//    MEMBER_NOT_FOUND(404, "Member not found"),
//    MEMBER_EXISTS(409, "Member exists"),
//    COFFEE_NOT_FOUND(404, "Coffee not found"),
//    COFFEE_CODE_EXISTS(409, "Coffee Code exists"),
//    ORDER_NOT_FOUND(404, "Order not found"),
//    NOT_IMPLEMENTATION(501, "Not Implementation"),
//    INVALID_MEMBER_STATUS(400, "Invalid member status");
    CANNOT_CHANGE_QUESTION(403, "Question can not be changed"),
    TAG_NOT_FOUND(404, "Tag not found"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    CANNOT_DELETE_QUESTION(400, "Answered question can not be deleted");



    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
