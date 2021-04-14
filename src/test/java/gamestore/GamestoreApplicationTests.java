package gamestore;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class GamestoreApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testStr() {
        String constantString = "dimitar" + "ivanov";
        String newString = new String("dimitarivanov");

        assertThat(constantString).isNotSameAs(newString);
    }

}
