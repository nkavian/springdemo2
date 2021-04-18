package info.jerrinot.springdemo.java;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hazelcast.map.MapStore;
import com.hazelcast.spring.context.SpringAware;

@Component
@SpringAware
public class SpringStore implements MapStore<String, String> {

    @Autowired
    private SpringService springService;

    @Override
    public void delete(final String key) {
        //
    }

    @Override
    public void deleteAll(final Collection<String> keys) {
        //
    }

    @Override
    public String load(final String key) {
        return "" + springService.getMagicValue();
    }

    @Override
    public Map<String, String> loadAll(final Collection<String> keys) {
        return null;
    }

    @Override
    public Iterable<String> loadAllKeys() {
        return null;
    }

    @Override
    public void store(final String key, final String value) {
        //
    }

    @Override
    public void storeAll(final Map<String, String> map) {
        //
    }

}
