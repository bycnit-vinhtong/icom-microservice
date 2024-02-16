package com.icommerce.catalog.dto;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class EventCatalog<K, T> {

    public enum Type {SEARCH, VIEW}

    private EventCatalog.Type eventType;
    private K key;
    private T data;
    private LocalDateTime eventCreatedAt;

    public EventCatalog() {
        this.eventType = null;
        this.key = null;
        this.data = null;
        this.eventCreatedAt = null;
    }

    public EventCatalog(Type eventType, K key, T data) {
        this.eventType = eventType;
        this.key = key;
        this.data = data;
        this.eventCreatedAt = now();
    }

    public Type getEventType() {
        return eventType;
    }

    public K getKey() {
        return key;
    }

    public T getData() {
        return data;
    }

    public LocalDateTime getEventCreatedAt() {
        return eventCreatedAt;
    }
}
