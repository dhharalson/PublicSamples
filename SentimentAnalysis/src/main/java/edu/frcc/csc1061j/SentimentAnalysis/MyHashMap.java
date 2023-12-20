package edu.frcc.csc1061j.SentimentAnalysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import edu.frcc.csc1061j.SentimentAnalysis.MyHashMap.Entry;

public class MyHashMap<K, V> implements Map<K, V> {
	private static final int INITIAL_NUM_BUCKETS = 4;

	private int size = 0;
	private double loadFactorThreshold = 0.5;
	private List<Entry<K, V>>[] buckets;

	public static class Entry<K, V> implements Map.Entry<K, V> {
		K key;
		V value;

		public Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V oldValue = this.value;
			this.value = value;
			return oldValue;
		}
	}

	public MyHashMap() {
		buckets = new LinkedList[INITIAL_NUM_BUCKETS];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean containsKey(Object key) {
		if (get(key) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] != null) {
				List<Entry<K, V>> bucket = buckets[i];
				for (Entry<K, V> entry : bucket) {
					if (entry.getValue().equals(value)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	@Override
	public V get(Object key) {
		int bucketIndex = Math.abs(key.hashCode()) % buckets.length;
		List<Entry<K, V>> bucket = buckets[bucketIndex];
		if (bucket != null) {
			for (Entry<K, V> entry : bucket) {
				if (entry.getKey().equals(key)) {
					return entry.getValue();
				}
			}
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		int bucketIndex = Math.abs(key.hashCode()) % buckets.length;
		List<Entry<K, V>> bucket = buckets[bucketIndex];
		if (bucket == null) {
			bucket = new LinkedList<Entry<K, V>>();
			buckets[bucketIndex] = bucket;
		}
		for (Entry<K, V> entry : bucket) {
			if (entry.getKey().equals(key)) {
				V oldValue = entry.getValue();
				entry.value = value;
				return oldValue;
			}
		}

		bucket.add(new Entry<K, V>(key, value));
		size++;

		if ((1.0 * size) / buckets.length > loadFactorThreshold) {
			rehash();
		}
		return value;
	}

	// Homework part 1
	private void rehash() {
		System.out.println("\n*** Rehash Starting ***\n");

		// The present bucket list is made temp
		List<Entry<K, V>>[] temp = buckets;

		// New bucket list of double the old size is created
		buckets = new LinkedList[buckets.length * 2];

		// Initialize each bucket in the new array as Linked List:
		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = new LinkedList<>();
		}
		// Reset the size of the hashmap
		size = 0;
		// Loop through each bucket in "old" buckets (temp)
		for (List<Entry<K, V>> bucket : temp) {
			// Check if it contains entries
			if (bucket != null) {
				// For each entry, call the put() method to insert into bigger bucket array
				// The put method will automatically handle finding the right index (using
				// hashcode) in the new buckets array.
				for (Entry<K, V> entry : bucket) {
					put(entry.getKey(), entry.getValue());
				}
			}
		}
		System.out.println("\n*** Rehash Ending ***\n");
	}

	@Override
	public V remove(Object key) {
		int bucketIndex = Math.abs(key.hashCode()) % buckets.length;
		List<Entry<K, V>> bucket = buckets[bucketIndex];
		if (bucket != null) {
			for (Entry<K, V> entry : bucket) {
				if (entry.getKey().equals(key)) {
					V value = entry.getValue();
					bucket.remove(entry);
					size--;
					return value;
				}
			}
		}
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub

	}

	@Override
	public void clear() {
		for (List<Entry<K, V>> bucket : buckets) {
			bucket = null;
		}
		size = 0;
	}

	@Override
	public Set<K> keySet() {
		Set<K> set = new HashSet<K>();
		for (int i = 0; i < buckets.length; i++) {
			if (buckets[i] != null) {
				List<Entry<K, V>> bucket = buckets[i];
				for (Entry<K, V> entry : bucket) {
					set.add(entry.getKey());
				}
			}
		}
		return set;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

}
