package gamestore;

import gamestore.security.PasswordEncoder;
import gamestore.utils.formatters.LocalDateFormatter;
import gamestore.utils.mapper.ModelMapperConfig;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.format.Formatter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;

/**
 * The Gamestore application.
 *
 * @author Dimitar Ivanov
 */
@SpringBootApplication
public class GamestoreApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(GamestoreApplication.class, args);
    }

    /**
     * Setup model mapper config
     *
     * @return the model mapper
     * @see ModelMapperConfig
     */
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        ModelMapperConfig config = new ModelMapperConfig(modelMapper);
        return modelMapper;
    }

    /**
     * Local date formatter setup.
     *
     * @return the formatter for LocalDate
     * @see LocalDateFormatter
     */
    @Bean
    @Primary
    public Formatter<LocalDate> localDateFormatter() {
        return new LocalDateFormatter();
    }
}
