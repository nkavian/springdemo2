package info.jerrinot.springdemo.java;

import com.hazelcast.core.IExecutorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(SpringdemoApplication.SPRING_AWARE_PROP)
class SpringAwareConfigActivatedTests {

    @Autowired
    private IExecutorService executorService;

    @Test
    void dependencyInjectedIntoSpringAware() throws Exception {
        SpringAwareCallable callable = new SpringAwareCallable();
        Integer outcome = executorService.submit(callable).get();

        assertEquals(42, outcome);
    }

}
