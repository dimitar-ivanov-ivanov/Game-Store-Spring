package gamestore.services;

import gamestore.exceptions.UserNotFoundException;
import gamestore.models.User;
import gamestore.repositories.UserRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    @Cacheable("users")
    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("da"));
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("Username %s not found", username)
                ));
    }
}
