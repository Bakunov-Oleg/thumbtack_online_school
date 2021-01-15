package net.thumbtack.school.hiring.dto.response;

import net.thumbtack.school.hiring.ServerErrorCode;

public class ErrorDtoResponse {
    private String errorString;

    public ErrorDtoResponse(ServerErrorCode error){
        this.errorString = error.getErrorCode();
    }

    public String getErrorString(){
        return errorString;
    }
}
