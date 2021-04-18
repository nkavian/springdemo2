package info.jerrinot.springdemo.java;

import com.hazelcast.core.HazelcastInstance;
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

    @Autowired
    private HazelcastInstance hazelcast;

    @Test
    void dependencyInjectedIntoSpringAware() throws Exception {
        SpringAwareCallable callable = new SpringAwareCallable();
        int outcome = executorService.submit(callable).get();

        assertEquals(42, outcome);
    }

    @Test
    void dependencyInjectedIntoMapStore() throws Exception {
        assertEquals("42", hazelcast.getMap("aMap").get("foo"));
    }

}
