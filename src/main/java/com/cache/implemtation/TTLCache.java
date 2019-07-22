package com.cache.implemtation;
/**
 *  interface for the ttl cache, where put, get, cleanUp methods are required for basic cache
 */
public interface TTLCache<K,V> {

    void put(K key,V item);

    V get(K key);

    void cleanUp();
}
