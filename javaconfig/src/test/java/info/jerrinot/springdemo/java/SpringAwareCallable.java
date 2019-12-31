package info.jerrinot.springdemo.java;

import com.hazelcast.spring.context.SpringAware;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.concurrent.Callable;

@SpringAware
public class SpringAwareCallable implements Callable<Integer>, Serializable {

    @Autowired
    private transient SpringService springService;

    @Override
    public Integer call() {
        return springService.getMagicValue();
    }
}
