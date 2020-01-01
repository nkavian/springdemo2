package info.jerrinot.springdemo.java;

import com.hazelcast.config.Config;
import com.hazelcast.spring.context.SpringManagedContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.MethodMetadata;

import java.lang.annotation.Annotation;

class SpringAwareConfigBeanFactoryPostprocessor implements BeanDefinitionRegistryPostProcessor {
    private static final String POSTPROCESSOR_BEAN_NAME_SUFFIX = "-springaware-postprocessor";

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        String[] beanNames = registry.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            BeanDefinition beanDefinition = registry.getBeanDefinition(beanName);
            ResolvableType resolvableType = beanDefinition.getResolvableType();
            if (factoryHasAnnotation(beanDefinition, SpringAwareConfig.class) && isConfig(resolvableType)) {
                BeanDefinition bd = new GenericBeanDefinition();
                bd.setBeanClassName(SpringAwareConfigPostProcessor.class.getName());
                bd.getConstructorArgumentValues().addGenericArgumentValue(beanName);
                String name = createPostProcessorBeanName(beanName);
                registry.registerBeanDefinition(name, bd);
            }
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //intentionally empty
    }

    static class SpringAwareConfigPostProcessor implements BeanPostProcessor {
        private final String name;

        @Autowired
        private ApplicationContext context;


        SpringAwareConfigPostProcessor(String name) {
            this.name = name;
        }

        @Override
        public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
            if (bean instanceof Config && beanName.equals(name)) {
                SpringManagedContext managedContext = new SpringManagedContext();
                managedContext.setApplicationContext(context);
                ((Config) bean).setManagedContext(managedContext);
            }
            return bean;
        }
    }

    private static <A extends Annotation> boolean factoryHasAnnotation(BeanDefinition beanDefinition, Class<A> annotation) {
        if (!(beanDefinition instanceof AnnotatedBeanDefinition)) {
            return false;
        }
        AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition) beanDefinition;
        MethodMetadata factoryMethodMetadata = annotatedBeanDefinition.getFactoryMethodMetadata();
        if (factoryMethodMetadata == null) {
            return false;
        }
        MergedAnnotations annotations = factoryMethodMetadata.getAnnotations();
        return annotations.isPresent(annotation);
    }

    private static boolean isConfig(ResolvableType resolvableType) {
        return Config.class.equals(resolvableType.resolve());
    }

    private static String createPostProcessorBeanName(String beanName) {
        return beanName + POSTPROCESSOR_BEAN_NAME_SUFFIX;
    }
}
