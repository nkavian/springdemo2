package info.jerrinot.springdemo.java;

import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringdemoApplication {

    @Autowired
    private HazelcastInstance hazelcast;

    @Bean
    public IExecutorService executorService() {
        return hazelcast.getExecutorService("myExecutorService");
    }

    @Bean
    public SpringService springService() {
        return new SpringService(42);
    }

    @Bean
    @SpringAwareConfig
    public Config hazelcastConfig() {
        return new Config();
    }
}
