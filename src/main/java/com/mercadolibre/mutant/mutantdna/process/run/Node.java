package com.mercadolibre.mutant.mutantdna.process.run;

import lombok.Data;
import java.util.Collection;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;

/**
 * Clase node donde se encontraran las secuencias que van existiendo.
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */
@Data
class Node {

	private Node suffix;
	private final Map<Character, Edge> edges;
	private int[] data;
	private static final int START_SIZE = 0;
	private int lastIdx = 0;
	private static final int INCREMENT = 1;

	Node() {
		edges = new EdgeBag();
		suffix = null;
		data = new int[START_SIZE];
	}

	Collection<Integer> getData() {
		return getData(-1);
	}

	Collection<Integer> getData(int numElements) {
		Set<Integer> ret = new HashSet<Integer>();
		for (int num : data) {
			ret.add(num);
			if (ret.size() == numElements) {
				return ret;
			}
		}

		for (Edge e : edges.values()) {
			if (-1 == numElements || ret.size() < numElements) {
				for (int num : e.getDest().getData()) {
					ret.add(num);
					if (ret.size() == numElements) {
						return ret;
					}
				}
			}
		}
		return ret;
	}

	Edge getEdge(char ch) {
		return edges.get(ch);
	}

	void addRef(int index) {
		if (contains(index)) {
			return;
		}

		addIndex(index);

		Node iter = this.suffix;
		while (iter != null) {
			if (iter.contains(index)) {
				break;
			}
			iter.addRef(index);
			iter = iter.suffix;
		}

	}

	private boolean contains(int index) {
		int low = 0;
		int high = lastIdx - 1;

		while (low <= high) {
			int mid = (low + high) >>> 1;
			int midVal = data[mid];

			if (midVal < index)
				low = mid + 1;
			else if (midVal > index)
				high = mid - 1;
			else
				return true;
		}
		return false;
	}

	private void addIndex(int index) {
		if (lastIdx == data.length) {
			int[] copy = new int[data.length + INCREMENT];
			System.arraycopy(data, 0, copy, 0, data.length);
			data = copy;
		}
		data[lastIdx++] = index;
	}

	void addEdge(char ch, Edge e) {
		edges.put(ch, e);
	}

}
