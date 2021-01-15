package net.thumbtack.school.hiring.dto.response;

public class GetUserStatusDtoResponse {
    private Boolean status;

    public GetUserStatusDtoResponse(Boolean status){
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public Boolean validate(){
        return true;
    }
}
