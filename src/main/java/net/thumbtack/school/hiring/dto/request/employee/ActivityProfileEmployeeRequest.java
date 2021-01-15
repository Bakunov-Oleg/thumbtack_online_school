package net.thumbtack.school.hiring.dto.request.employee;

public class ActivityProfileEmployeeRequest {

    private String token;
    private Boolean status;

    public ActivityProfileEmployeeRequest(String token, Boolean status){
        this.token = token;
        this.status = status;
    }
    
    public String getToken(){
        return token;
    }

    public Boolean getStatus(){
        return status;
    }

    public boolean validate() {
        return true;
    }
}
