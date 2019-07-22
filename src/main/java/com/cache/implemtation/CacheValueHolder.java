package com.cache.implemtation;

import java.time.LocalDateTime;

public class CacheValueHolder<V> {

    /**
     *  The CacheValueHolder object is a wrapper for the
     *  cache item or value being stored, which has the “lastAccessTimestamp”
     *  used for removing items that have not been accessed more than TTL duration.
    * */
    private final V value;
    private LocalDateTime lastAccessedTimeStamp;


    public CacheValueHolder(V value) {
        this.value = value;
        this.lastAccessedTimeStamp = LocalDateTime.now();
    }

    public LocalDateTime getLastAccessedTimeStamp() {
        return  lastAccessedTimeStamp;
    }

    public void setLastAccessedTimeStamp(LocalDateTime lastAccessedTimeStamp) {
        this.lastAccessedTimeStamp = lastAccessedTimeStamp;
    }
    public V getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "CacheValue [ " + value + ";" + " Last Accessed time : " + lastAccessedTimeStamp + " ]";
    }


}
