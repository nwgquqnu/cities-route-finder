package com.crfinder.routefinder.service.search;

public class SearchNodeWithWeigth<T> {

    private SearchNode<T> node;
    private long weigth;

    public SearchNodeWithWeigth(SearchNode<T> node, long weigth) {
        this.node = node;
        this.weigth = weigth;
    }

    public SearchNode<T> getNode() {
        return node;
    }

    public void setNode(SearchNode<T> node) {
        this.node = node;
    }

    public long getWeigth() {
        return weigth;
    }

    public void setWeigth(long weigth) {
        this.weigth = weigth;
    }

}
