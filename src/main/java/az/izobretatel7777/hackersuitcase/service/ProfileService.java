package az.izobretatel7777.hackersuitcase.service;

import az.izobretatel7777.hackersuitcase.dao.entity.User;

public interface ProfileService {
    boolean updateProfile(User user);
    User getProfile();
}
