package Arvore;
import java.util.LinkedList;
import java.util.Queue;

import javax.swing.table.DefaultTableModel;

public class AVLTree<T extends Comparable<T>> {
	Node<T> root;

	public Node<T> getRoot() {
		return root;
	}

	public AVLTree() {
		root = null;
	}

	public T Maximum() {
		Node<T> local = root;
		if (local == null)
			return null;
		while (local.getRight() != null)
			local = local.getRight();
		return local.getData();
	}

	public T Minimum() {
		Node<T> local = root;
		if (local == null)
			return null;
		while (local.getLeft() != null) {
			local = local.getLeft();
		}
		return local.getData();
	}

	private int depth(Node<T> node) {
		if (node == null)
			return 0;
		return node.getDepth();
	}

	public Node<T> insert(T data, String linha) {
		if(search1(data,root,linha)){
			root = insert(root, data, linha);
			switch (balanceNumber(root)) {
				case 1:
					root = rotateLeft(root);
					break;
				case -1:
					root = rotateRight(root);
					break;
				default:
					break;
			}
			return root;
		}else{
			return null;
		}
	}
	
	
	public boolean search1(T data,Node<T> root,String linha) {
		if(root == null){ 
			return true;
		}
		int i = ((String) root.getData()).compareToIgnoreCase((String) data);
		if (i == 0){
			root.getLinhas().add(linha);
			return false;
		}else if(i > 0){
			search1(data, root.getLeft(), linha);
			
		}else{
			search1(data,root.getRight(),linha);
		}
		return true;
	}
	
	
	
	public Node<T> insert(Node<T> node, T data, String linha) {
		if (node == null)
			return new Node<T>(data, linha);
		if (((String) node.getData()).compareToIgnoreCase((String) data) > 0){
			
			node = new Node<T>(node.getData(),node.getLinha(), insert(node.getLeft(), data, linha),
					node.getRight());
			
		} else if (((String) node.getData()).compareToIgnoreCase((String) data) <= 0) {
			
			node = new Node<T>(node.getData(),node.getLinha(), node.getLeft(), insert(
					node.getRight(), data, linha));
			
		}
		switch (balanceNumber(node)) {
		case 1:
			node = rotateLeft(node);
			break;
		case -1:
			node = rotateRight(node);
			break;
		default:
			return node;
		}
		return node;
	}

	private int balanceNumber(Node<T> node) {
		int L = depth(node.getLeft());
		int R = depth(node.getRight());
		if (L - R >= 2)
			return -1;
		else if (L - R <= -2)
			return 1;
		return 0;
	}

	private Node<T> rotateLeft(Node<T> node) {
		Node<T> q = node;
		Node<T> p = q.getRight();
		Node<T> c = q.getLeft();
		Node<T> a = p.getLeft();
		Node<T> b = p.getRight();
		q = new Node<T>(q.getData(),q.getLinha(), c, a);
		p = new Node<T>(p.getData(),p.getLinha(), q, b);
		return p;
	}

	private Node<T> rotateRight(Node<T> node) {
		Node<T> q = node;
		Node<T> p = q.getLeft();
		Node<T> c = q.getRight();
		Node<T> a = p.getLeft();
		Node<T> b = p.getRight();
		q = new Node<T>(q.getData(),q.getLinha(), b, c);
		p = new Node<T>(p.getData(),p.getLinha(), a, q);
		return p;
	}
	
	public String toString() {
		return root.toString();
	}

	public DefaultTableModel search(T data, DefaultTableModel model) {
		String[] i = null;
		String t;
		Queue<Node<T>> queue = new LinkedList<Node<T>>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Node<T> node = queue.poll();
			if(((String) node.getData()).compareToIgnoreCase((String) data) == 0 ){
				for (int j = 0; j < node.getLinhas().size(); j++){
					t = (String) node.getLinhas().get(j);
					i = t.split(";");
					model.addRow(i);
				}
			}
			Node<T> left = node.getLeft();
			Node<T> right = node.getRight();
			if (left != null) {
				queue.add(left);
			}
			if (right != null) {
				queue.add(right);
			}
		}
		queue.clear();
		return model;
	}
}