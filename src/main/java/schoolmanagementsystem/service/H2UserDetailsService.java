package schoolmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import schoolmanagementsystem.entity.User;
import schoolmanagementsystem.repository.UserRepository;
import schoolmanagementsystem.security.H2UserDetails;

@Service
public class H2UserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public H2UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new H2UserDetails(user);
    }
}
