package az.contasoft.xmies_patient.api.internal;

public class StandartResponse<T> {
    private int serverCode;
    private String serverMessage;
    private T responseData;

    public StandartResponse(int serverCode, String serverMessage, T responseData) {
        this.serverCode = serverCode;
        this.serverMessage = serverMessage;
        this.responseData = responseData;
    }

    public StandartResponse(ResponseStatus status) {
        switch (status){
            case ERROR:{
                serverCode = 100;
                serverMessage = "Error. plz see logs";
            }break;
            case SUCCESS:{
                serverCode = 200;
                serverMessage = "OK";
            }break;
            case NOTFOUND:{
                serverCode = 204;
                serverMessage = "Content not found";
            }break;
            default:{
                serverCode = 404;
                serverMessage = "Not found";
            }
        }
    }

    public StandartResponse() {
    }

    public int getServerCode() {
        return serverCode;
    }

    public void setServerCode(int serverCode) {
        this.serverCode = serverCode;
    }

    public String getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(String serverMessage) {
        this.serverMessage = serverMessage;
    }

    public T getResponseData() {
        return responseData;
    }

    public void setResponseData(T responseData) {
        this.responseData = responseData;
    }
}
