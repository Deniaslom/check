package factory;

import caching.InitCacheByConfig;
import caching.algoritm.Cache;
import caching.algoritm.LFUCache;
import caching.algoritm.LRUCache;
import lombok.Setter;

/**
 * Depending on the cache type specified in resources/application.yml, a cache object is created
 */
@Setter
public class CacheFactory {

    private int capacity;
    private String cacheType;

    private static CacheFactory instance;

    private CacheFactory() {
    }

    public static CacheFactory getInstance() {
        if (instance == null) {
            instance = new CacheFactory();
        }
        return instance;
    }

    public Cache<Object> getCache() {
        InitCacheByConfig.initConfigFromFile();
        Cache<Object> cache = null;

        switch (cacheType) {
            case "LFU":
                cache = new LFUCache<>(capacity);
                break;
            case "LRU":
                cache = new LRUCache<>(capacity);
                break;
        }
        return cache;
    }

}
