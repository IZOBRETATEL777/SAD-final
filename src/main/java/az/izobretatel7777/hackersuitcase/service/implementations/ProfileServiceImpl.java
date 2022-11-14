package az.izobretatel7777.hackersuitcase.service.implementations;

import az.izobretatel7777.hackersuitcase.dao.entity.User;
import az.izobretatel7777.hackersuitcase.dao.repo.UserRepository;
import az.izobretatel7777.hackersuitcase.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;

    @Override
    public boolean updateProfile(User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = userRepository.findByEmail(auth.getName());
        if (currentUser == null)
            return false;
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setPhoneNumber(user.getPhoneNumber());
        userRepository.save(currentUser);
        return true;
    }

    @Override
    public boolean updatePassword(User user) {
        return false;
    }

    @Override
    public User getProfile() {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        user.setPassword(null);
        return user;
    }
}
