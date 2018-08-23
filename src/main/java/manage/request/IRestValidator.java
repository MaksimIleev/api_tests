package manage.request;

public interface IRestValidator {

    void statusCodeEquals(int statusCode);
    void statusCodeEquals(int statusCode, String customErrorMessage);
    void responseMessageAttribute(String message);
    void notNullOrEmpty(String value);

    interface Mandatory {
        RestValidatorBase assertAll();
    }
}

