package info.jerrinot.springdemo.java;

import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringdemoApplication {
    public static final String SPRING_AWARE_PROP = "springawareconfig";

    @Autowired
    private HazelcastInstance hazelcast;

    @Bean(destroyMethod = "")
    public IExecutorService executorService() {
        return hazelcast.getExecutorService("myExecutorService");
    }

    @Bean
    public SpringService springService() {
        return new SpringService(42);
    }

    @Bean
    @ConditionalOnProperty(SPRING_AWARE_PROP)
    @SpringAwareConfig
    public Config hazelcastConfigSpringAwareActivated() {
        return makeConfig();
    }

    @Bean
    @ConditionalOnMissingBean(Config.class)
    public Config hazelcastConfigSpringAwareDeactivated() {
        return makeConfig();
    }

    private static Config makeConfig() {
        Config config = new Config();
        config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        return config;
    }
}
