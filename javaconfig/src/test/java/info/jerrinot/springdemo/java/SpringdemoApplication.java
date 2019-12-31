package info.jerrinot.springdemo.java;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
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
}
