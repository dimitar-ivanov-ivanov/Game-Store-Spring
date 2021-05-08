package gamestore.security.config;

import gamestore.security.CustomAuthenticationProvider;
import gamestore.security.CustomAuthenticator;
import gamestore.security.PasswordEncoder;
import gamestore.utils.jwt.JwtConfig;
import gamestore.utils.jwt.JwtTokenVerifier;
import gamestore.utils.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import gamestore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * The web Security config.
 *
 * @author Dimitar Ivanov
 */
@AllArgsConstructor
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * The config containing data for jwt validation
     *
     * @see JwtConfig
     */
    private final JwtConfig jwtConfig;

    /**
     * The password encoder
     *
     * @see gamestore.security.PasswordEncoder
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * The user detail's service
     *
     * @see UserService
     */
    private final UserService userService;

    private final CustomAuthenticationProvider authenticationProvider;

    /**
     * Set csrf disabled for now until we have a running website
     * Creation policy should be stateless because we want the requests to be independant
     * Add the two jwt filters
     * Permit everyone to be able to access the index page of the site
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(
                        jwtConfig, authenticationManager()
                ))
                .addFilterAfter(new JwtTokenVerifier(jwtConfig),
                        JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    /**
     * Dao authentication provider
     *
     * @return the dao authentication provider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder.bCryptPasswordEncoder());
        provider.setUserDetailsService(userService);

        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
        //auth.authenticationProvider(daoAuthenticationProvider());
    }
}
