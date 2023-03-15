package VestelionKit;

import java.util.Iterator;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class VTree<T extends Comparable<T>> implements Iterable<T>
{
    private Node root;

    private class Node
    {
        private T value;
        private Node left, right;

        public Node(T value)
        {
            this.value = value;
        }
    }

    public void Add(T value)
    {
        root = Add(root, value);
    }

    private Node Add(Node node, T value)
    {
        if (node == null)
        {
            return new Node(value);
        }

        int cmp = value.compareTo(node.value);

        if (cmp < 0)
        {
            node.left = Add(node.left, value);
        }
        else if (cmp > 0)
        {
            node.right = Add(node.right, value);
        }

        return node;
    }

    public boolean Contains(T value)
    {
        return Contains(root, value);
    }

    private boolean Contains(Node node, T value)
    {
        if (node == null)
        {
            return false;
        }

        int cmp = value.compareTo(node.value);

        if (cmp == 0)
        {
            return true;
        }
        else if (cmp < 0)
        {
            return Contains(node.left, value);
        }
        else
        {
            return Contains(node.right, value);
        }
    }

    public void Remove(T value)
    {
        root = Remove(root, value);
    }

    private Node Remove(Node node, T value)
    {
        if (node == null)
        {
            return null;
        }

        int cmp = value.compareTo(node.value);

        if (cmp < 0)
        {
            node.left = Remove(node.left, value);
        }
        else if (cmp > 0)
        {
            node.right = Remove(node.right, value);
        }
        else
        {
            if (node.left == null)
            {
                return node.right;
            }

            if (node.right == null)
            {
                return node.left;
            }

            Node min = Min(node.right);
            min.right = RemoveMin(node.right);
            min.left = node.left;
            node = min;
        }
        return node;
    }

    private Node Min(Node node)
    {
        if (node.left == null)
        {
            return node;
        }
        else
        {
            return Min(node.left);
        }
    }

    private Node RemoveMin(Node node)
    {
        if (node.left == null)
        {
            return node.right;
        }

        node.left = RemoveMin(node.left);

        return node;
    }

    public VList<T> SelectIf(Predicate<T> condition)
    {
        VList<T> results = new VList<>();

        SelectIf(root, condition, results);

        return results;
    }

    private void SelectIf(Node node, Predicate<T> condition, VList<T> results)
    {
        if (node == null)
        {
            return;
        }

        if (condition.test(node.value))
        {
            results.Add(node.value);
        }

        SelectIf(node.left, condition, results);
        SelectIf(node.right, condition, results);
    }

    public void RemoveIf(Predicate<T> condition)
    {
        root = RemoveIf(root, condition);
    }

    private Node RemoveIf(Node node, Predicate<T> condition)
    {
        if (node == null)
        {
            return null;
        }

        node.left = RemoveIf(node.left, condition);
        node.right = RemoveIf(node.right, condition);

        if (condition.test(node.value))
        {
            node = Remove(node, node.value);
        }

        return node;
    }

    public void UpdateIf(Predicate<T> condition, UnaryOperator<T> updater)
    {
        UpdateIf(root, condition, updater);
    }

    private void UpdateIf(Node node, Predicate<T> condition, UnaryOperator<T> updater)
    {
        if (node == null)
        {
            return;
        }

        if (condition.test(node.value))
        {
            node.value = updater.apply(node.value);
        }

        UpdateIf(node.left, condition, updater);
        UpdateIf(node.right, condition, updater);
    }

    public int Height()
    {
        return Height(root);
    }

    private int Height(Node node)
    {
        if (node == null)
        {
            return -1;
        }

        int leftHeight = Height(node.left);
        int rightHeight = Height(node.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public int Size()
    {
        return Size(root);
    }

    private int Size(Node node)
    {
        if (node == null)
        {
            return 0;
        }

        return 1 + Size(node.left) + Size(node.right);
    }

    @Override
    public Iterator<T> iterator()
    {
        return new TreeIterator();
    }

    private class TreeIterator implements Iterator<T>
    {
        private VList<T> nodes = new VList<>();
        private int index = 0;

        public TreeIterator()
        {
            addNodes(root);
        }

        private void addNodes(Node node)
        {
            if (node != null) {
                addNodes(node.left);
                nodes.Add(node.value);
                addNodes(node.right);
            }
        }

        @Override
        public boolean hasNext()
        {
            return index < nodes.Size();
        }

        @Override
        public T next()
        {
            if (!hasNext())
            {
                throw new java.util.NoSuchElementException();
            }

            return nodes.Get(index++);
        }
    }
}