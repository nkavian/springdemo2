package info.jerrinot.springdemo.java;

import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import com.hazelcast.spring.context.SpringManagedContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
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

// UNCOMMENT THIS TO GET THE SPRING MANAGED CONTEXT INJECTED INTO HAZELCAST
//    @Bean
//    public Config createConfigWithSpringManagedContext(ApplicationContext context) {
//        Config config =  new Config();
//        SpringManagedContext springManagedContext = new SpringManagedContext();
//        springManagedContext.setApplicationContext(context);
//        config.setManagedContext(springManagedContext);
//        return config;
//    }
}
