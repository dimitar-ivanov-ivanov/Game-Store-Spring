package gamestore.utils.seed;

import com.google.common.collect.Sets;
import gamestore.models.entities.security.Authority;
import gamestore.models.entities.security.Role;
import gamestore.models.entities.user.User;
import gamestore.models.enums.Gender;
import gamestore.repositories.AuthorityRepository;
import gamestore.repositories.RoleRepository;
import gamestore.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

/**
 * The Setup data loader used for seeding roles,authorities and three users.
 *
 * @author Dimitar Ivanov
 */
@Component
public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    private final List<String> rolesToCreate = Arrays.asList(
            "ADMIN", "USER", "ADMIN_TRAINEE"
    );

    private final List<String> authoritiesToCreate = Arrays.asList(
            "user:read", "user:delete", "user:update",
            "game:read", "game:write", "game:delete", "game:update"
    );

    private final Set<String> adminAuthoritiesString = new HashSet<>(authoritiesToCreate);

    private final Set<String> adminTraineeAuthoritiesString = Sets.newHashSet(
            "user:read", "user:update",
            "game:read", "game:write", "game:update"
    );

    private final Set<String> userAuthoritiesString = Sets.newHashSet(
            "user:read", "game:read"
    );

    private boolean alreadySetup = false;

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthorityRepository authorityRepository;
    private PasswordEncoder passwordEncoder;

    /**
     * Instantiates a new Setup data loader.
     *
     * @param userRepository      the user repository
     * @param roleRepository      the role repository
     * @param authorityRepository the authority repository
     * @param passwordEncoder     the password encoder
     */
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

    /**
     * Seed roles, add to them authorities and then add users.
     *
     * @param contextRefreshedEvent
     */
    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup) {
            return;
        }

        for (int i = 0; i < authoritiesToCreate.size(); i++) {
            createAuthorityIfNotFound(authoritiesToCreate.get(i));
        }

        Set<Authority> adminAuthorities = adminAuthoritiesString
                .stream().map(a -> authorityRepository.getByName(a).get())
                .collect(Collectors.toSet());

        Set<Authority> adminTraineeAuthorities = adminTraineeAuthoritiesString
                .stream().map(a -> authorityRepository.getByName(a).get())
                .collect(Collectors.toSet());

        Set<Authority> userAuthorities = userAuthoritiesString
                .stream().map(a -> authorityRepository.getByName(a).get())
                .collect(Collectors.toSet());

        createRoleIfNotFound("ADMIN", adminAuthorities);
        createRoleIfNotFound("ADMIN_TRAINEE", adminTraineeAuthorities);
        createRoleIfNotFound("USER", userAuthorities);

        Role adminRole = roleRepository.getByName("ADMIN").get();
        Role userRole = roleRepository.getByName("USER").get();
        Role adminTraineeRole = roleRepository.getByName("ADMIN_TRAINEE").get();

        User user = new User("Dimitar", "Ivanov",
                LocalDate.of(1999, 2, 20),
                "d1mN", "dimitar-ivanov@abv.bg",
                passwordEncoder.encode("A_35aa51A"), Gender.MALE);

        User user2 = new User("Ivan", "Ivanov",
                LocalDate.of(1979, 2, 20),
                "iV1n", "ivan-ivanov@abv.bg",
                passwordEncoder.encode("A_35aa51A"), Gender.MALE);

        User user3 = new User("Stoqn", "Ivanov",
                LocalDate.of(1999, 2, 20),
                "stQn", "stoqn-ivanov@abv.bg",
                passwordEncoder.encode("A_35aa51A"), Gender.MALE);

        user.getRoles().add(adminRole);
        user2.getRoles().add(userRole);
        user3.getRoles().add(adminTraineeRole);
        createUsersIfNotFound(Arrays.asList(user, user2, user3));

        alreadySetup = true;
    }

    /**
     * Create users if not found
     *
     * @param users of users
     */
    void createUsersIfNotFound(List<User> users) {
        for (User user : users) {
            if (userRepository.getByUsername(user.getUsername()).isEmpty()) {
                userRepository.save(user);
            }
        }
    }

    /**
     * Create role if not found.
     *
     * @param name        the name
     * @param authorities the authorities
     */
    @Transactional
    void createRoleIfNotFound(String name,
                              Set<Authority> authorities) {

        boolean roleExists = roleRepository.getByName(name).isPresent();

        if (!roleExists) {
            Role role = new Role(name);
            role.getAuthorities().addAll(authorities);
            roleRepository.save(role);
        }
    }

    /**
     * Create authority if not found.
     *
     * @param name the name
     */
    @Transactional
    void createAuthorityIfNotFound(String name) {
        boolean authorityExists = authorityRepository.getByName(name).isPresent();

        if (!authorityExists) {
            Authority authority = new Authority(name);
            authorityRepository.save(authority);
        }
    }
}
