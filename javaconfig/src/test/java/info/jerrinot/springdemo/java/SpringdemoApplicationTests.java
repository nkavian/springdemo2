package info.jerrinot.springdemo.java;

import com.hazelcast.core.IExecutorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class SpringdemoApplicationTests {

    @Autowired
    private IExecutorService executorService;

    @Test
    void dependencyInjectedIntoSpringAware() throws Exception {
        SpringAwareCallable callable = new SpringAwareCallable();
        int outcome = executorService.submit(callable).get();

        assertEquals(42, outcome);
    }

}
