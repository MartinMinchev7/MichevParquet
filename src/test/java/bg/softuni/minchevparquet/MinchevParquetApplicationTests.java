package bg.softuni.minchevparquet;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@WithMockUser(
        username = "testuser@mail.com",
        roles = {"CLIENT", "ADMIN"}

)
class MinchevParquetApplicationTests {

    @Test
    void contextLoads() {
    }

}
