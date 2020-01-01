package info.jerrinot.springdemo.java;

import com.hazelcast.core.IExecutorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class SpringAwareConfigDeactivatedTests {

    @Autowired
    private IExecutorService executorService;

    @Test
    void dependencyNotInjectedIntoSpringAware() throws Exception {
        SpringAwareCallable callable = new SpringAwareCallable();
        Integer outcome = executorService.submit(callable).get();

        assertNull(outcome);
    }

}
