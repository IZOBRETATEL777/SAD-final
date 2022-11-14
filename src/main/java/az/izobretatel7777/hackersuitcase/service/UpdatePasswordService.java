package az.izobretatel7777.hackersuitcase.service;

public interface UpdatePasswordService {
    void sendResetPasswordEmail(String login);
    void sendResetPasswordEmail();
    boolean resetPassword(String newPassword, String otp);
}
