package com.modou.coeus.graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description: 图
 * @author: zhangwei
 * @create: 2021-08-20 16:36
 **/
public abstract class Graph<V, E> {

	public Graph() {}
	public abstract int edgesSize();
	public abstract int verticesSize();
	public abstract void addVertex(V v);
	public abstract void addEdge(V from, V to);
	public abstract void addEdge(V from, V to, E weight);
	public abstract List<String> findAllPath(V begin, V end);


	public static class PathInfo<V, E> {
		protected E weight;
		protected List<EdgeInfo<V, E>> edgeInfos = new LinkedList<>();
		public PathInfo() {}
		public PathInfo(E weight) {
			this.weight = weight;
		}
		public E getWeight() {
			return weight;
		}
		public void setWeight(E weight) {
			this.weight = weight;
		}
		public List<EdgeInfo<V, E>> getEdgeInfos() {
			return edgeInfos;
		}
		public void setEdgeInfos(List<EdgeInfo<V, E>> edgeInfos) {
			this.edgeInfos = edgeInfos;
		}
		@Override
		public String toString() {
			return "PathInfo [weight=" + weight + ", edgeInfos=" + edgeInfos + "]";
		}
	}
	
	public static class EdgeInfo<V, E> {
		private V from;
		private V to;
		private E weight;
		public EdgeInfo(V from, V to, E weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
		public V getFrom() {
			return from;
		}
		public void setFrom(V from) {
			this.from = from;
		}
		public V getTo() {
			return to;
		}
		public void setTo(V to) {
			this.to = to;
		}
		public E getWeight() {
			return weight;
		}
		public void setWeight(E weight) {
			this.weight = weight;
		}
		@Override
		public String toString() {
			return "EdgeInfo [from=" + from + ", to=" + to + ", weight=" + weight + "]";
		}
	}
}
