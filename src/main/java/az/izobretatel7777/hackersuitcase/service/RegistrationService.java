package az.izobretatel7777.hackersuitcase.service;

import az.izobretatel7777.hackersuitcase.dao.entity.User;

public interface RegistrationService {
    void register(User user);
    boolean activateUserByOtp(String otp);
}
