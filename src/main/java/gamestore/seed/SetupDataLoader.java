package gamestore.seed;

import com.google.common.collect.Sets;
import gamestore.models.Authority;
import gamestore.models.Role;
import gamestore.models.User;
import gamestore.repositories.AuthorityRepository;
import gamestore.repositories.RoleRepository;
import gamestore.repositories.UserRepository;
import gamestore.security.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import javax.transaction.Transactional;

@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {


    private boolean alreadySetup = false;

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthorityRepository authorityRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public SetupDataLoader(UserRepository userRepository,
                           RoleRepository roleRepository,
                           AuthorityRepository authorityRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authorityRepository = authorityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup) {
            return;
        }

        Authority userReadAuthority = createAuthorityIfNotFound("user:read");
        Authority userWriteAuthority = createAuthorityIfNotFound("user:write");
        Authority gameReadAuthority = createAuthorityIfNotFound("game:read");
        Authority gameWriteAuthority = createAuthorityIfNotFound("game:write");

        Set<Authority> adminAuthorities = Sets.newHashSet(
                userReadAuthority,
                userWriteAuthority,
                gameReadAuthority,
                gameWriteAuthority
        );

        Set<Authority> adminTraineeAuthorities = Sets.newHashSet(
                userReadAuthority,
                gameReadAuthority,
                userWriteAuthority
        );

        Set<Authority> userAuthorities = Sets.newHashSet(
                userReadAuthority,
                gameReadAuthority
        );


        createRoleIfNotFound("ADMIN", adminAuthorities);
        createRoleIfNotFound("ADMIN_TRAINEE", adminTraineeAuthorities);
        createRoleIfNotFound("USER", userAuthorities);

        Role adminRole = roleRepository.getByName("ADMIN");
        Role userRole = roleRepository.getByName("USER");
        Role adminTraineeRole = roleRepository.getByName("ADMIN_TRAINEE");

        User user = new User("Dimitar", "Ivanov",
                LocalDate.of(1999, 2, 20),
                "d1mn", "dimitar-ivanov@abv.bg", passwordEncoder.encode("password"),
                false, false, true);

        User user2 = new User("Ivan", "Ivanov",
                LocalDate.of(1979, 2, 20),
                "iv1n", "ivan-ivanov@abv.bg", passwordEncoder.encode("password"),
                false, false, true);

        User user3 = new User("Stoqn", "Ivanov",
                LocalDate.of(1999, 2, 20),
                "stoqn", "stoqn-ivanov@abv.bg", passwordEncoder.encode("password"),
                false, false, true);

        user.getRoles().add(adminRole);
        user2.getRoles().add(userRole);
        user3.getRoles().add(adminTraineeRole);
        userRepository.saveAll(Arrays.asList(user, user2, user3));

        alreadySetup = true;
    }

    @Transactional
    Role createRoleIfNotFound(String name,
                              Set<Authority> authorities) {

        Role role = roleRepository.getByName(name);
        if (role == null) {
            role = new Role(name);
            role.setAuthorities(authorities);
            roleRepository.save(role);
        }

        return role;
    }

    @Transactional
    Authority createAuthorityIfNotFound(String name) {
        Authority authority = authorityRepository.getByName(name);
        if (authority == null) {
            authority = new Authority(name);
            authorityRepository.save(authority);
        }

        return authority;
    }
}
