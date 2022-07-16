package ru.bryanin.dev.questionnairebackend.office.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.bryanin.dev.questionnairebackend.office.model.user.BasicUser;
import ru.bryanin.dev.questionnairebackend.office.repository.BasicUserRepository;

@Service("userDetailsServiceImplementation")
public class UserDetailsServiceImplementation implements UserDetailsService {

    private final BasicUserRepository basicUserRepository;

    @Autowired
    public UserDetailsServiceImplementation(BasicUserRepository basicUserRepository) {
        this.basicUserRepository = basicUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        BasicUser basicUser = basicUserRepository.findBasicUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return SecurityUser.fromBasicUser(basicUser);
    }
}
