package info.jerrinot.springdemo.java;

import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringAwarePostprocessorConfiguration {

    @Bean
    public BeanDefinitionRegistryPostProcessor springAwareConfigPostprocessor() {
        return new SpringAwareConfigBeanFactoryPostprocessor();
    }
}
