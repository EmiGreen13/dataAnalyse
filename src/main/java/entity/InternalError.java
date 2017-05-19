package entity;


public class InternalError {

    private Integer errorNumber;
    private String errorMessage;

    public InternalError(){
        this.setErrorMessage("");
        this.setErrorNumber(0);
    }

    public InternalError(Integer errorNumber, String errorMessage){
        this.setErrorMessage(errorMessage);
        this.setErrorNumber(errorNumber);
    }

    public Integer getErrorNumber() {
        return errorNumber;
    }

    public void setErrorNumber(Integer errorNumber) {
        this.errorNumber = errorNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
