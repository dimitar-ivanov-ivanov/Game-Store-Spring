package gamestore;

import gamestore.security.PasswordEncoder;
import gamestore.utils.async.AsyncConfiguration;
import gamestore.utils.formatters.LocalDateFormatter;
import gamestore.utils.mapper.ModelMapperConfig;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.format.Formatter;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.concurrent.Executor;

/**
 * The Gamestore application.
 *
 * @author Dimitar Ivanov
 */
@SpringBootApplication
@EnableAsync
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

    @Bean
    public Executor taskExecutor() {
        AsyncConfiguration configuration = new AsyncConfiguration();
        return configuration.getAsyncExecutor();
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
