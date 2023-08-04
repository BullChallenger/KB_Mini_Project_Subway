package view;

public class FailView {

    public static void errorMessage(int errorCode, String message) {
        System.out.println(errorCode + ": " + message);
    }
}
