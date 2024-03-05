package caching;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Setter;
import lombok.extern.java.Log;
import factory.CacheFactory;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Log
public class InitCacheByConfig {
    private static final CacheFactory cacheFactory = CacheFactory.getInstance();

    /**
     * Read prop. from file pref. and set it to factory
     */
    public static boolean initConfigFromFile() {
        ObjectMapper om = new ObjectMapper(new YAMLFactory());
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("application.yml")).getFile());

        try {
            CacheConfig cacheConfig = om.readValue(file, CacheConfig.class);
                cacheFactory.setCacheType(cacheConfig.cacheType);
                cacheFactory.setCapacity(cacheConfig.capacity);
                log.config("cacheType = " + cacheConfig.cacheType + "; capacity = " + cacheConfig.capacity);
                return true;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Setter
    private static class CacheConfig {
        private String cacheType;
        private int capacity;
    }

}
