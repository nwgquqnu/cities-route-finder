package com.crfinder.routefinder.service.search;

public class SearchNode<T> implements Comparable<SearchNode<T>> {
    final T data;
    private SearchNode<T> parent;
    private NodeColor color = NodeColor.WHITE;
    private long distance = 0;

    public SearchNode(T data) {
        this.data = data;
    }
    
    public SearchNode(T data, long distance) {
        this.data = data;
        this.distance = distance;
    }
    
    public T getData() {
        return data;
    }
    public SearchNode<T> getParent() {
        return parent;
    }
    public void setParent(SearchNode<T> parent) {
        this.parent = parent;
    }
    public NodeColor getColor() {
        return color;
    }
    public void setColor(NodeColor color) {
        this.color = color;
    }
    public long getDistance() {
        return distance;
    }
    public void setDistance(long distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "SearchNode [data=" + data + ", color=" + color + ", distance=" + distance + "]";
    }

    @Override
    public int compareTo(SearchNode<T> o) {
        return Long.compare(distance, o.distance);
    }

    
}