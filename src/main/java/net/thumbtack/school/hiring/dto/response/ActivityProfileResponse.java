package net.thumbtack.school.hiring.dto.response;

public class ActivityProfileResponse {
    private String token;
    private Boolean status;

    public ActivityProfileResponse(String status){
        this.status = new Boolean(status);
    }

    public String getToken(){
        return token;
    }

    public Boolean getStatus(){
        return status;
    }
}
