package com.mercadolibre.mutant.mutantdna.process.run;

import com.mercadolibre.mutant.mutantdna.domain.RequestMutantADN;
import com.mercadolibre.mutant.mutantdna.exception.ParametrosEntrada;
import com.mercadolibre.mutant.mutantdna.process.Process;
import java.util.Iterator;

import java.util.ArrayList;


/**
 * Clase  que procesa el adn
 * @author william corredor - MercadoLibre - prueba adn mutante
 * @date 5/11/2021
 * @since 1.0
 */
public class ProcessDNAArray implements Process {

	private int last = 0;

	private final Node root = new Node();

	private Node activeLeaf = root;

	private static final String[] MUTANT_DNA = { "AAAA", "CCCC", "GGGG", "TTTT" };
	/**
	 * metodo que recibe el adn y empieza a recorrer cada 
	 */
	@Override
	public boolean processDNA(RequestMutantADN request) {

		boolean isMutant = false;
		int count = 0;
		@SuppressWarnings("unchecked")
		ArrayList<String> words = getWords(request.getDna());

		for (int i = 0; i < words.size(); i++) {
			this.put(words.get(i), i);
		}

		for (String mutantDnaItem : MUTANT_DNA) {
			count += this.getCount(mutantDnaItem);

			if (count >= 2) {
				return Boolean.TRUE;
			}
		}
		return isMutant;

	}

	/**
	 * metodo encargado de recorrer la cadena de adn
	 * @param dna recibe los daros de adn
	 * @return devuelve un arreglo de la cadena de adn
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static ArrayList getWords(String[] dna) {

		ArrayList<String> words = new ArrayList();

		// se agregan filas
		for (String s : dna) {
			words.add(s);
		}

		// se agregan columnas
		for (int row = 0; row < dna.length; row++) {

			StringBuffer strColumn = new StringBuffer(dna.length);

			for (int column = 0; column < dna.length; column++) {
				strColumn.append(dna[column].charAt(row));
			}

			words.add(strColumn.toString());
		}

		// Armar secuencias dna
		for (int i = 0; i < dna.length / 2; i++) {
			StringBuffer obliqueDna1 = new StringBuffer(dna.length);
			StringBuffer obliqueDna2 = new StringBuffer(dna.length);

			for (int j = 0; j < dna.length - i; j++) {
				obliqueDna1.append(dna[j].charAt(j + i));

				if (i != 0) {
					obliqueDna2.append(dna[i + j].charAt(j));
				}
			}

			if (obliqueDna1.length() > 0) {
				words.add(obliqueDna1.toString());
			}

			if (obliqueDna2.length() > 0) {
				words.add(obliqueDna2.toString());
			}
		}

		return words;
	}

	/**
	 * metodo encargado de realizar la insercion de los valores indexados e identificarlos
	 * @param key
	 * @param index
	 * @throws IllegalStateException
	 */
	public void put(String key, int index) throws IllegalStateException {
		if (index < last) {

			throw new ParametrosEntrada(" el indice de entrada no debe ser menor previamente insertado, envio " + index
					+ " y se espera" + last);

		} else {
			last = index;
		}

		activeLeaf = root;

		String remainder = key + "$";
		Node s = root;

		String text = "";
		for (int i = 0; i < remainder.length(); i++) {
			text += remainder.charAt(i);
			text = text.intern();

			Pair<Node, String> active = update(s, text, remainder.substring(i), index);
			active = canonize(active.getFirst(), active.getSecond());

			s = active.getFirst();
			text = active.getSecond();
		}

		if (null == activeLeaf.getSuffix() && activeLeaf != root && activeLeaf != s) {
			activeLeaf.setSuffix(s);
		}

	}

	/**
	 * metodo encargado de validar la posicion de cada valor
	 * @param inputNode
	 * @param stringPart
	 * @param rest
	 * @param value
	 * @return
	 */
	private Pair<Node, String> update(final Node inputNode, final String stringPart, final String rest,
			final int value) {
		Node s = inputNode;
		String tempstr = stringPart;
		char newChar = stringPart.charAt(stringPart.length() - 1);

		Node oldroot = root;

		Pair<Boolean, Node> ret = testAndSplit(s, tempstr.substring(0, tempstr.length() - 1), newChar, rest, value);

		Node r = ret.getSecond();
		boolean endpoint = ret.getFirst();

		Node leaf;
		while (!endpoint) {
			Edge tempEdge = r.getEdge(newChar);
			if (null != tempEdge) {
				leaf = tempEdge.getDest();
			} else {
				leaf = new Node();
				leaf.addRef(value);
				Edge newedge = new Edge(rest, leaf);
				r.addEdge(newChar, newedge);
			}

			if (activeLeaf != root) {
				activeLeaf.setSuffix(leaf);
			}
			activeLeaf = leaf;

			if (oldroot != root) {
				oldroot.setSuffix(r);
			}

			oldroot = r;

			if (null == s.getSuffix()) { // root node
				assert (root == s);
				tempstr = tempstr.substring(1);
			} else {
				Pair<Node, String> canret = canonize(s.getSuffix(), safeCutLastChar(tempstr));
				s = canret.getFirst();
				tempstr = (canret.getSecond() + tempstr.charAt(tempstr.length() - 1)).intern();
			}

			ret = testAndSplit(s, safeCutLastChar(tempstr), newChar, rest, value);
			r = ret.getSecond();
			endpoint = ret.getFirst();

		}

		if (oldroot != root) {
			oldroot.setSuffix(r);
		}
		oldroot = root;

		return new Pair<Node, String>(s, tempstr);
	}

	/**
	 * delimita la secuencia enviada.
	 * @param seq
	 * @return
	 */
	private String safeCutLastChar(String seq) {
		if (seq.length() == 0) {
			return "";
		}
		return seq.substring(0, seq.length() - 1);
	}

	/**
	 * valida la estructura y retorna si encontro una secuencia
	 * @param inputs
	 * @param stringPart
	 * @param t
	 * @param remainder
	 * @param value
	 * @return
	 */
	private Pair<Boolean, Node> testAndSplit(final Node inputs, final String stringPart, final char t,
			final String remainder, final int value) {
		Pair<Node, String> ret = canonize(inputs, stringPart);
		Node s = ret.getFirst();
		String str = ret.getSecond();

		if (!"".equals(str)) {
			Edge g = s.getEdge(str.charAt(0));

			String label = g.getLabel();
			if (label.length() > str.length() && label.charAt(str.length()) == t) {
				return new Pair<Boolean, Node>(true, s);
			} else {
				String newlabel = label.substring(str.length());
				assert (label.startsWith(str));

				Node r = new Node();
				Edge newedge = new Edge(str, r);

				g.setLabel(newlabel);

				r.addEdge(newlabel.charAt(0), g);
				s.addEdge(str.charAt(0), newedge);

				return new Pair<Boolean, Node>(false, r);
			}

		} else {
			Edge e = s.getEdge(t);
			if (null == e) {
				return new Pair<Boolean, Node>(false, s);
			} else {
				if (remainder.equals(e.getLabel())) {
					e.getDest().addRef(value);
					return new Pair<Boolean, Node>(true, s);
				} else if (remainder.startsWith(e.getLabel())) {
					return new Pair<Boolean, Node>(true, s);
				} else if (e.getLabel().startsWith(remainder)) {
					Node newNode = new Node();
					newNode.addRef(value);

					Edge newEdge = new Edge(remainder, newNode);

					e.setLabel(e.getLabel().substring(remainder.length()));

					newNode.addEdge(e.getLabel().charAt(0), e);

					s.addEdge(t, newEdge);

					return new Pair<Boolean, Node>(false, s);
				} else {
					return new Pair<Boolean, Node>(true, s);
				}
			}
		}

	}

	/**
	 * canoniza o los guiarda los valores que estan coincidiendo.
	 * @param s
	 * @param inputstr
	 * @return
	 */
	private Pair<Node, String> canonize(final Node s, final String inputstr) {

		if ("".equals(inputstr)) {
			return new Pair<Node, String>(s, inputstr);
		} else {
			Node currentNode = s;
			String str = inputstr;
			Edge g = s.getEdge(str.charAt(0));
			while (g != null && str.startsWith(g.getLabel())) {
				str = str.substring(g.getLabel().length());
				currentNode = g.getDest();
				if (str.length() > 0) {
					g = currentNode.getEdge(str.charAt(0));
				}
			}

			return new Pair<Node, String>(currentNode, str);
		}
	}

	/**
	 * determian cuantos nodos coincidieron
	 * @param word
	 * @return
	 */
	public int getCount(String word) {
		Node tmpNode = searchNode(word);
		int count = 0;
		if (tmpNode == null) {
			return 0;
		}

		count += countChildNodes(tmpNode);
		return count;

	}

	/**
	 * busca los nodos que coinciden
	 * @param word
	 * @return
	 */
	private Node searchNode(String word) {

		Node currentNode = root;
		Edge currentEdge;

		for (int i = 0; i < word.length(); ++i) {
			char ch = word.charAt(i);

			currentEdge = currentNode.getEdge(ch);
			if (null == currentEdge) {
				return null;
			} else {
				String label = currentEdge.getLabel();
				int lenToMatch = Math.min(word.length() - i, label.length());
				if (!word.regionMatches(i, label, 0, lenToMatch)) {
					return null;
				}

				if (label.length() >= word.length() - i) {
					return currentEdge.getDest();
				} else {
					currentNode = currentEdge.getDest();
					i += lenToMatch - 1;
				}
			}
		}

		return null;
	}

	/**
	 * cuenta los nodos que coincideron
	 * @param n
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public int countChildNodes(Node n) {
		int count = 0;

		Iterator iteraEdges = n.getEdges().values().iterator();

		// It is a leaf edge
		if (n.getEdges().size() == 0) {
			return n.getData().size() * 1;
		}
		while (iteraEdges.hasNext()) {
			Edge e = (Edge) iteraEdges.next();

			if (e.getDest() != null) {
				count += countChildNodes(e.getDest());
			}
		}

		return count;
	}

	/**
	 * calse para mapear los datos
	 * @param <A>
	 * @param <B>
	 */
	private class Pair<A, B> {

		private final A first;
		private final B second;

		public Pair(A first, B second) {
			this.first = first;
			this.second = second;
		}

		public A getFirst() {
			return first;
		}

		public B getSecond() {
			return second;
		}
	}

}
