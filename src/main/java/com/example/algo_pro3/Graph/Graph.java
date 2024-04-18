package com.example.algo_pro3.Graph;



public class Graph {
	private final hashTable<Vertex, Vertex> table;

	public Graph(int size) {
		table = new hashTable<>(size);
	}

//	public Graph() {
//		table = new hashTable<Vertex, Vertex>(10);
//	}

	public void add(Vertex vertex) {
		table.put(vertex, vertex);
	}

	public Vertex get(Vertex vertex) {
		return table.get(vertex);
	}

	public void addEdge(Vertex vertex1, Vertex vertex2) {
		Vertex v = table.get(vertex1);
		Vertex v2 = table.get(vertex2);
		if (v != null && v2 != null)
			v.getList().insertSorted(new Edge(v, v2));
		else {
//			System.out.println("yahoo");
			table.put(vertex1, vertex1);
			table.get(vertex1).getList().insertSorted(new Edge(v, v2));
			if (v2 == null)
				table.put(vertex2, vertex2);
		}
	}

//	public String printAllVertex() {
//		String s = "";
//		if (table.getTableLength() != 0) {
//			for (SingleLinkedList list : table.getArray()) {
//				Node<Entry<String, Vertex>> curr = list.getFirst();
//				while (curr != null) {
//					s += curr.getData().getValue() + "\n";
//					curr = curr.getNext();
//				}
//			}
//		}
//		return s;
//	}

//	public String printAll() {
//		String s = "";
//		if (table.getSize() != 0) {
//			for (SingleLinkedList list : table.getArray()) {
//				Node<Entry<String, Vertex>> curr = list.getFirst();
//				while (curr != null) {
//					s += curr.getData().getValue().getLocation();
//					Node<Edge> currVertex = curr.getData().getValue().getList().getFirst();
//					while (currVertex != null) {
//						s += "-->" + currVertex.getData().getDistination();
//						currVertex = currVertex.getNext();
//					}
//					s += "\n";
//					curr = curr.getNext();
//				}
//			}
//		}
//		return s;
//	}

	public hashTable getTable() {
		return table;
	}

	public int size() {
		return table.getSize();
	}

}
