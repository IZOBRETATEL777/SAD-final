package az.izobretatel7777.hackersuitcase.service;

import az.izobretatel7777.hackersuitcase.dao.entity.User;

public interface RegistrationService {
    void register(User user);
    public boolean activateUserByOtp(String otp);
}
