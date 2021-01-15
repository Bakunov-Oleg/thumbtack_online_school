package net.thumbtack.school.threads;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Task12 {
    //Написать свой класс, аналогичный ConcurrentHashMap , используя  ReadWriteLock.
    //Будет ли эта реализация хуже ConcurrentHashMap, и если да, то почему ?

    Map<Object, Object> map;
    ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public Task12() {
        this.map = new HashMap();
    }

    public void clear() {
        readWriteLock.writeLock().lock();
        try {
            map.clear();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object compute(Object key, BiFunction remappingFunction) {
        readWriteLock.writeLock().lock();
        try {
            return map.compute(key, remappingFunction);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object computeIfAbsent(Object key, Function mappingFunction) {
        readWriteLock.writeLock().lock();
        try {
            return map.computeIfAbsent(key, mappingFunction);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object computeIfPresent(Object key, BiFunction remappingFunction) {
        readWriteLock.writeLock().lock();
        try {
            return map.computeIfPresent(key, remappingFunction);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public boolean containsKey(Object key) {
        readWriteLock.readLock().lock();
        try {
            return map.containsKey(key);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public boolean containsValue(Object value) {
        readWriteLock.readLock().lock();
        try {
            return map.containsValue(value);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Set<Map.Entry<Object, Object>> entrySet() {
        readWriteLock.readLock().lock();
        try {
            return map.entrySet();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public void forEach(BiConsumer action) {
        readWriteLock.writeLock().lock();
        try {
            map.forEach(action);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object get(Object key) {
        readWriteLock.readLock().lock();
        try {
            return map.get(key);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public boolean isEmpty() {
        readWriteLock.readLock().lock();
        try {
            return map.isEmpty();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Set keySet() {
        readWriteLock.writeLock().lock();
        try {
            return map.keySet();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object merge(Object key, Object value, BiFunction remappingFunction) {
        readWriteLock.writeLock().lock();
        try {
            return map.merge(key, value, remappingFunction);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object put(Object key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            return map.put(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void putAll(Map m) {
        readWriteLock.writeLock().lock();
        try {
            map.putAll(m);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object putIfAbsent(Object key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            return map.putIfAbsent(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object remove(Object key) {
        readWriteLock.writeLock().lock();
        try {
            return map.remove(key);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public boolean remove(Object key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            return map.remove(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public boolean replace(Object key, Object oldValue, Object newValue) {
        readWriteLock.writeLock().lock();
        try {
            return map.replace(key, oldValue, newValue);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public Object replace(Object key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            return map.replace(key, value);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void replaceAll(BiFunction function) {
        readWriteLock.writeLock().lock();
        try {
            map.replaceAll(function);
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public int size() {
        readWriteLock.readLock().lock();
        try {
            return map.size();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public String toString() {
        readWriteLock.readLock().lock();
        try {
            return map.toString();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Collection values() {
        readWriteLock.readLock().lock();
        try {
            return map.values();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

    public Object getOrDefault(Object key, Object defaultValue) {
        readWriteLock.readLock().lock();
        try {
            return map.getOrDefault(key, defaultValue);
        } finally {
            readWriteLock.readLock().unlock();
        }
    }

}
