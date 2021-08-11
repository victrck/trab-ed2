package Arvore;

import java.util.ArrayList;

public class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

	private T data;
	private ArrayList<String> linhas = new ArrayList<String>();
	private String linha;
	private Node<T> left;
	private Node<T> right;
	public int level;
	private int depth;

	public Node(T data, String linha) {
		this(data,linha, null, null);
	}

	public Node(T data, String linha, Node<T> left, Node<T> right) {
		super();
		this.data = data;
		this.left = left;
		this.right = right;
		this.linha = (String) linha;
		this.linhas.add((String) linha);
		if (left == null && right == null)
			setDepth(1);
		else if (left == null)
			setDepth(right.getDepth() + 1);
		else if (right == null)
			setDepth(left.getDepth() + 1);
		else
			setDepth(Math.max(left.getDepth(), right.getDepth()) + 1);
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Node<T> getLeft() {
		return left;
	}

	public void setLeft(Node<T> left) {
		this.left = left;
	}

	public Node<T> getRight() {
		return right;
	}

	public void setRight(Node<T> right) {
		this.right = right;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	@Override
	public int compareTo(Node<T> o) {
		return ((String) this.data).compareToIgnoreCase((String) o.data);
	}

	@Override
	public String toString() {
		return "Level " + level + ": " + data;
	}

	public ArrayList<String> getLinhas() {
		return linhas;
	}

	public void setLinhas(ArrayList<String> linha) {
		this.linhas = linha;
	}
	public String getLinha() {
		return linha;
	}

	public void setLinha(String linha) {
		this.linha = linha;
	}
}
