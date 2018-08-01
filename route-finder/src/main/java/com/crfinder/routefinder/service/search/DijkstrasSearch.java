package com.crfinder.routefinder.service.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;

public class DijkstrasSearch<T> {

    private PriorityQueue<SearchNode<T>> queue;
    private Map<T, SearchNode<T>> usedNodes;

    private SearchNode<T> startNode;
    private final Function<SearchNode<T>, List<SearchNode<T>>> getDirtyNextNodes;

    public DijkstrasSearch(Function<SearchNode<T>, List<SearchNode<T>>> getDirtyNextNodes) {
        this.getDirtyNextNodes = getDirtyNextNodes;
    }


    public Map<T, SearchNode<T>> performSearch(T origin) {
        this.resetCollections();
        this.startNode = new SearchNode<T>(origin);
        SearchNode<T> root = this.startNode;
        root.setColor(NodeColor.GREY);
        this.queue.add(root);
        this.usedNodes.put(origin, root);

        while (this.queue.size() > 0) {
            SearchNode<T> node = this.queue.poll();

            List<SearchNodeWithWeigth<T>> nextNodes = getNextNodes(node);
            for (SearchNodeWithWeigth<T> nextNodeWithWeight : nextNodes) {
                SearchNode<T> nextNode = nextNodeWithWeight.getNode();
                long newDistance = node.getDistance() + nextNodeWithWeight.getWeigth();
                if (nextNode.getColor() == NodeColor.WHITE) {
                    nextNode.setColor(NodeColor.GREY);
                    nextNode.setDistance(newDistance);
                    nextNode.setParent(node);
                    this.queue.add(nextNode);
                } else if (nextNode.getColor() == NodeColor.GREY && nextNode.getDistance() > newDistance) {
                    this.queue.remove(nextNode);
                    nextNode.setDistance(newDistance);
                    nextNode.setParent(node);
                    this.queue.add(nextNode);
                }
            }
            node.setColor(NodeColor.BLACK);
        }
        return usedNodes;

    }


    private void resetCollections() {
        queue = new PriorityQueue<>();
        usedNodes = new HashMap<>();
    }


    private List<SearchNodeWithWeigth<T>> getNextNodes(SearchNode<T> node) {
        List<SearchNode<T>> dirtyNextNodes = this.getDirtyNextNodes.apply(node);
        List<SearchNodeWithWeigth<T>> processedNextNodes = new ArrayList<>(dirtyNextNodes.size());

        for (SearchNode<T> dirtyNode : dirtyNextNodes) {
            SearchNode<T> nodeToUse = this.usedNodes.get(dirtyNode.data);
            if (nodeToUse == null) {
                nodeToUse = dirtyNode;
                this.usedNodes.put(nodeToUse.data, nodeToUse);
            }
            processedNextNodes.add(new SearchNodeWithWeigth<>(nodeToUse, dirtyNode.getDistance()));
        }
        return processedNextNodes;
    }
}
