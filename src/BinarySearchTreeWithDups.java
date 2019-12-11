import java.beans.BeanInfo;
import java.util.*;

public class BinarySearchTreeWithDups<T extends Comparable<? super T>> extends BinarySearchTree<T>
        implements SearchTreeInterface<T>, java.io.Serializable {

    public BinarySearchTreeWithDups() {
        super();
    }

    public BinarySearchTreeWithDups(T rootEntry) {
        super(rootEntry);
        setRootNode(new BinaryNode<T>(rootEntry));
    }

    @Override
    public T add(T newEntry) {
        T result = newEntry;
        if (isEmpty()) {
            setRootNode(new BinaryNode<T>(newEntry));
        } else {
            addEntryHelperNonRecursive(newEntry);
        }
        return result;
    }

    // YOUR CODE HERE! THIS METHOD CANNOT BE RECURSIVE.
    private void addEntryHelperNonRecursive(T newEntry) {
        BinaryNode<T> curr = getRootNode();
        Stack<BinaryNode<T>> stack = new Stack<>();

        if (curr == null) {
            curr = new BinaryNode<>(newEntry);
        }

        while (curr != null) {
            if (curr.getData().compareTo(newEntry) < 0) {
                stack.push(curr);
                curr = curr.getRightChild();
            } else {
                stack.push(curr);
                curr = curr.getLeftChild();
            }
        }

        curr = stack.pop();
        if (curr.getData().compareTo(newEntry) < 0) {
            curr.setRightChild(new BinaryNode<>(newEntry));
        } else {
            curr.setLeftChild(new BinaryNode<>(newEntry));
        }
    }


    // YOUR CODE HERE! THIS METHOD CANNOT BE RECURSIVE.
    // MAKE SURE TO TAKE ADVANTAGE OF THE SORTED NATURE OF THE BST!
    public int countEntriesNonRecursive(T target) {
        // this initial code is meant as a suggestion to get your started- use it or delete it!
        int count = 0;
        BinaryNode<T> curr  = getRootNode();

        while (curr != null) {
            if (curr.getData().compareTo(target) == 0) {
                count++;
                curr = curr.getLeftChild();
            } else if (curr.getData().compareTo(target) < 0) {
                curr = curr.getRightChild();
            }  else {
                curr = curr.getLeftChild();
            }
        }

        return count;
    }

    // YOUR CODE HERE! MUST BE RECURSIVE! YOU ARE ALLOWED TO CREATE A PRIVATE HELPER.
    // MAKE SURE TO TAKE ADVANTAGE OF THE SORTED NATURE OF THE BST!
    public int countGreaterRecursive(T target) {
        // this initial code is meant as a suggestion to get your started- use it or delete it!
        int count = 0;
        BinaryNode<T> curr = getRootNode();

        if (curr == null) {
            return 0;
        }

        count = countGreaterRecursiveHelper(curr, target);

        return count;
    }

    private int countGreaterRecursiveHelper(BinaryNode<T> curr, T target) {
        if (curr == null) {
            return 0;
        }

        int countLeft = countGreaterRecursiveHelper(curr.getLeftChild(), target);
        int countRight = countGreaterRecursiveHelper(curr.getRightChild(), target);
        return (curr.getData().compareTo(target) > 0 ? 1 : 0) + countLeft + countRight;
    }

    // YOUR CODE HERE! MUST USE A STACK!! MUST NOT BE RECURSIVE!
    // MAKE SURE TO TAKE ADVANTAGE OF THE SORTED NATURE OF THE BST!
    public int countGreaterWithStack(T target) {
        // this initial code is meant as a suggestion to get your started- use it or delete it!
        int count = 0;
        BinaryNode<T> rootNode = getRootNode();
        Stack<BinaryNode<T>> nodeStack = new Stack<BinaryNode<T>>();
        nodeStack.push(rootNode);

        // consider a loop based on the stack!


        return count;
    }

    // YOUR EXTRA CREDIT CODE HERE! THIS METHOD MUST BE O(n).
    // YOU ARE ALLOWED TO USE A HELPER METHOD. THE METHOD CAN BE ITERATIVE OR RECURSIVE.
    public int countUniqueValues() {
        return 0;
    }


    public int getLeftHeight() {
        BinaryNode<T> rootNode = getRootNode();
        if (rootNode == null) {
            return 0;
        } else if (!rootNode.hasLeftChild()) {
            return 0;
        } else {
            return rootNode.getLeftChild().getHeight();
        }
    }

    public int getRightHeight() {
        BinaryNode<T> rootNode = getRootNode();
        if (rootNode == null) {
            return 0;
        } else if (!rootNode.hasRightChild()) {
            return 0;
        } else {
            return rootNode.getRightChild().getHeight();
        }
    }


}