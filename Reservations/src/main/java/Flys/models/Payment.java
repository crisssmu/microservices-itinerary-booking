package Flys.models;

public class Payment {
    private String method;
    private String token;

    public Payment(String method, String token) {
        this.method = method;
        this.token = token;
    }
    
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    
}
