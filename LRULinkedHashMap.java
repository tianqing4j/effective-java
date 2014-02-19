package org.liubey.example.effectivejava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.Map;

/**
 * 利用LinkedHashMap实现简单的缓存， 必须实现removeEldestEntry方法，具体参见JDK文档
 * 
 * 使用ReentrantLock lock保证多线程安全
 * 
 * @param <K>
 * @param <V>
 */
public class LRULinkedHashMap<K, V> extends LinkedHashMap<K, V> {

	private static final long serialVersionUID = -7287011538577089043L;

	/** 存储数据容量 */
	private int capacity;
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	/** 最大数据存储容量 */
	private static final int LRU_MAX_CAPACITY = 1024;
	private final Lock lock = new ReentrantLock();

	/**
	 * 默认构造方法
	 */
	public LRULinkedHashMap() {
		super();
	}

	/**
	 * 带参数构造方法
	 * 
	 * @param initialCapacity
	 *            容量
	 * @param loadFactor
	 *            装载因子
	 * @param isLRU
	 *            是否使用lru算法，true：使用（按方案顺序排序）;false：不使用（按存储顺序排序）
	 */
	public LRULinkedHashMap(int initialCapacity, float loadFactor, boolean isLRU) {
		super(initialCapacity, DEFAULT_LOAD_FACTOR, true);
		capacity = LRU_MAX_CAPACITY;
	}

	/** 
     * 带参数构造方法 
     * @param initialCapacity   容量 
     * @param loadFactor        装载因子 
     * @param isLRU             是否使用lru算法，true：使用（按方案顺序排序）;false：不使用（按存储顺序排序） 
     * @param lruCapacity       lru存储数据容量        
     */  
    public LRULinkedHashMap(int initialCapacity, float loadFactor, boolean isLRU, int lruCapacity) {  
        super(initialCapacity, loadFactor, true);  
        this.capacity = lruCapacity;  
    }  
	
	@Override
	protected boolean removeEldestEntry(java.util.Map.Entry<K, V> eldest) {
		// super.removeEldestEntry(eldest);
		// eldest
		return size() > capacity;
	}

	@Override
	public boolean containsKey(Object key) {
		try {
			lock.lock();
			return super.containsKey(key);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public V get(Object key) {
		try {
			lock.lock();
			return super.get(key);
		} finally {
			lock.unlock();
		}
	}

	@Override
	public V put(K key, V value) {
		try {
			lock.lock();
			return super.put(key, value);
		} finally {
			lock.unlock();
		}
	}

	public int size() {
		try {
			lock.lock();
			return super.size();
		} finally {
			lock.unlock();
		}
	}

	public void clear() {
		try {
			lock.lock();
			super.clear();
		} finally {
			lock.unlock();
		}
	}

	public Collection<Map.Entry<K, V>> getAll() {
		try {
			lock.lock();
			return new ArrayList<Map.Entry<K, V>>(super.entrySet());
		} finally {
			lock.unlock();
		}
	}
}
