package SnakeAndFood;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Snake {
    private Deque<Pair> body;
    private Map<Pair, Boolean> positionMap;

    public Snake(int startRow, int startCol) {
        this.body = new LinkedList<>();
        this.positionMap = new HashMap<>();

        Pair initialPosition = new Pair(startRow, startCol);
        this.body.offerFirst(initialPosition);
        this.positionMap.put(initialPosition, true);
    }

    Pair getHead() {
        return body.peekFirst();
    }

    Pair getTail() {
        return body.peekLast();
    }

    boolean containsKey(Pair newHead) {
        return positionMap.containsKey(newHead);
    }

    void pollLast() {
        body.pollLast();
    }

    void removeTail(Pair currentTail) {
        positionMap.remove(currentTail);
    }

    void addFirst(Pair newHead) {
        body.addFirst(newHead);
        positionMap.put(newHead, true);
    }

    int getSize() {
        return body.size();
    }
}
