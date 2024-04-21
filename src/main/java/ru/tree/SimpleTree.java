package ru.tree;

import java.util.*;

public class SimpleTree<E> implements Tree<E> {

    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> parentNodeOptional = findBy(parent);
        if (parentNodeOptional.isPresent()) {
            Node<E> parentNode = parentNodeOptional.get();
            if (!isValueExists(child)) {
                parentNode.children.add(new Node<>(child));
                return true;
            }
        }
        return false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> result = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> element = data.poll();
            if (element.value.equals(value)) {
                result = Optional.of(element);
                break;
            }
            data.addAll(element.children);
        }
        return result;
    }

    private boolean isValueExists(E value) {
        Queue<Node<E>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<E> current = queue.poll();
            if (current.value.equals(value)) {
                return true;
            }
            queue.addAll(current.children);
        }
        return false;
    }
}