package com.modou.coeus.graph;

import java.util.*;

/**
 * @description: 图
 * @author: zhangwei
 * @create: 2021-08-20 17:36
 **/
public class ListGraph<V, E> extends Graph<V, E> {
	public ListGraph() {}

	private Map<V, Vertex<V, E>> vertices = new HashMap<>();
	private Set<Edge<V, E>> edges = new HashSet<>();

	@Override
	public int edgesSize() {
		return edges.size();
	}

	@Override
	public int verticesSize() {
		return vertices.size();
	}

	@Override
	public void addVertex(V v) {
		if (vertices.containsKey(v)) {
			return;
		}
		vertices.put(v, new Vertex<>(v));
	}

	@Override
	public void addEdge(V from, V to) {
		addEdge(from, to, null);
	}

	@Override
	public void addEdge(V from, V to, E weight) {

		Vertex<V, E> fromVertex = vertices.get(from);
		if (fromVertex == null) {
			fromVertex = new Vertex<>(from);
			vertices.put(from, fromVertex);
		}
		Vertex<V, E> toVertex = vertices.get(to);
		if (toVertex == null) {
			toVertex = new Vertex<>(to);
			vertices.put(to, toVertex);
		}

		Edge<V, E> edge = new Edge<>(fromVertex, toVertex, weight);
		if (fromVertex.outEdges.remove(edge)) {
			toVertex.inEdges.remove(edge);
			edges.remove(edge);
		}
		fromVertex.outEdges.add(edge);
		toVertex.inEdges.add(edge);
		edges.add(edge);
	}


	private PathInfo<V, E> getPathInfo(V from, V to, Map<V, Map<V, PathInfo<V, E>>> paths) {
		Map<V, PathInfo<V, E>> map = paths.get(from);
		return map == null ? null : map.get(to);
	}

	public void print() {
		System.out.println("[顶点]-------------------");
		vertices.forEach((V v, Vertex<V, E> vertex) -> {
			System.out.println(v);
			System.out.println("out-----------");
			System.out.println(vertex.outEdges);
			System.out.println("in-----------");
			System.out.println(vertex.inEdges);
		});

		System.out.println("[边]-------------------");
		edges.forEach((Edge<V, E> edge) -> {
			System.out.println(edge);
		});
	}

	/**
	 * @param begin 起始顶点
	 * @param end   结束顶点
	 */
	@Override
	public List<String> findAllPath(V begin, V end) {

		List<String> paths = new ArrayList<>();

		Vertex<V, E> beginVertex = vertices.get(begin);
		if (beginVertex == null) {
			return null;
		}

		Set<Vertex<V, E>> visitedVertices = new HashSet<>();
		Stack<Vertex<V, E>> stack = new Stack<>();

		stack.push(beginVertex);
		visitedVertices.add(beginVertex);

		Vertex<V, E> topVertex = null;
		Vertex<V, E> nextVertex = null;

		Map<Vertex<V, E>,Set<Vertex<V,E>>> visitedTopNearVertexs = new HashMap<>();
		vertices.values().forEach((Vertex<V, E> vertex)->{
			visitedTopNearVertexs.put(vertex, new HashSet<Vertex<V, E>>());
		});

		while (!stack.isEmpty()) {

			topVertex = stack.peek();
			if(topVertex.value.equals(end)){

				collectPath(paths,stack);

				final Vertex<V, E> tempVertex = stack.pop();
				//对弹出的顶点，清空他的邻接顶点
				visitedTopNearVertexs.get(tempVertex).clear();
				//对栈顶的顶点，将弹出的顶点（tempVertex）增加他的已访问邻接顶点集合中
				if(stack.size() > 0){
					visitedTopNearVertexs.get(stack.peek()).add(tempVertex);
				}
				visitedVertices.remove(topVertex);
			}
			else{
				nextVertex = getNextVertex(topVertex, visitedTopNearVertexs,visitedVertices);
				if(null != nextVertex){
					stack.push(nextVertex);
					visitedVertices.add(nextVertex);
				}
				else{
					final Vertex<V, E> tempVertex = stack.pop();

					visitedTopNearVertexs.get(tempVertex).clear();
					if(stack.size() > 0){
						visitedTopNearVertexs.get(stack.peek()).add(tempVertex);
					}
					visitedVertices.remove(tempVertex);
				}
			}

		}

		return paths;
	}

	private void collectPath(List<String> paths, Stack<Vertex<V,E>> stack) {
		StringBuilder sb = new StringBuilder();
		stack.forEach((Vertex vertex) -> {
			sb.append(vertex + "->");
		});
		sb.delete(sb.length() - 2, sb.length());
		paths.add(sb.toString());
	}

	private Vertex<V,E> getNextVertex(Vertex<V, E> topVertex, Map<Vertex<V, E>,Set<Vertex<V,E>>> visitedTopNearVertexs,
									  Set<Vertex<V, E>> visitedVertices) {
		if(topVertex.outEdges.size() == 0){
			return null;
		}
		Vertex<V,E> topNearVertex = null;
		final Iterator<Edge<V, E>> it = topVertex.outEdges.iterator();

		while(it.hasNext()){
			topNearVertex = it.next().to;
			//topNearVertex顶点的邻接顶点(to) 不再 topNearVertex的已访问邻接顶点集合中
			if(!visitedTopNearVertexs.get(topVertex).contains(topNearVertex)
					//topNearVertex顶点的邻接顶点(to)  不在 已访问的订单中
					&& !visitedVertices.contains(topNearVertex)){
				break;
			}
			topNearVertex = null;
		}
		return topNearVertex;
	}


	public static class Vertex<V, E> {
		V value;
		Set<Edge<V, E>> inEdges = new HashSet<>();
		Set<Edge<V, E>> outEdges = new HashSet<>();
		Vertex(V value) {
			this.value = value;
		}
		@Override
		public boolean equals(Object obj) {
			return Objects.equals(value, ((Vertex<V, E>)obj).value);
		}
		@Override
		public int hashCode() {
			return value == null ? 0 : value.hashCode();
		}
		@Override
		public String toString() {
			return value == null ? "null" : value.toString();
		}
	}

	private static class Edge<V, E> {
		Vertex<V, E> from;
		Vertex<V, E> to;
		E weight;

		Edge(Vertex<V, E> from, Vertex<V, E> to) {
			this(from,to,null);
		}

		Edge(Vertex<V, E> from, Vertex<V, E> to, E weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		EdgeInfo<V, E> info() {
			return new EdgeInfo<>(from.value, to.value, weight);
		}

		@Override
		public boolean equals(Object obj) {
			Edge<V, E> edge = (Edge<V, E>) obj;
			return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
		}
		@Override
		public int hashCode() {
			return from.hashCode() * 31 + to.hashCode();
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}

	public static void main(String[] args) {
		ListGraph<String, Integer> graph = new ListGraph<>();
		//构建图
		graph.addEdge("A", "B");
		graph.addEdge("A", "C3");
		graph.addEdge("B", "C3");
		graph.addEdge("B", "C2");
		graph.addEdge("B", "C");
		graph.addEdge("C", "D");
		graph.addEdge("C3", "D");

		graph.print();
	}
}
