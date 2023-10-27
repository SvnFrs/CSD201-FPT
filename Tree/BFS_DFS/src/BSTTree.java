
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author CE170023 Nguyen Chi Hau
 */
public class BSTTree {

    BSTNode root;
    String result;
    ArrayList<BSTNode> path;
    /*===for drawing===  */
    int screenWidth;
    int yMin;

    /*===for drawing===  */
    public BSTTree() {
        root = null;
        this.screenWidth = 0;
        this.yMin = 0;
        result = "";
        path = new ArrayList<>();
    }

    public BSTTree(int screenWidth, int yMin) {
        root = null;
        this.screenWidth = screenWidth;
        this.yMin = yMin;
        result = "";
        path = new ArrayList<>();
    }

    public BSTNode getRoot() {
        return this.root;
    }

    public String getTraverSalResult() {
        return this.result;
    }

    public void addNode(int data) {
        if (root == null) {
            root = new BSTNode(data, yMin, screenWidth);
        } else {
            boolean isAdded = false;
            BSTNode node = root;
            while (!isAdded) {
                if (data < node.getData()) {
                    if (node.hasLeftChild()) {
                        node = node.getLeftChild();
                    } else {
                        node.setLeftChild(new BSTNode(data));
                        isAdded = true;
                    }
                } else if (data > node.getData()) {
                    if (node.hasRightChild()) {
                        node = node.getRightChild();
                    } else {
                        node.setRightChild(new BSTNode(data));
                        isAdded = true;
                    }
                } else {
                    isAdded = true;
                }
            }
        }
    }

    public void addNode(int data, int count) {
        if (root == null) {
            root = new BSTNode(data, yMin, screenWidth);
        } else {
            boolean isAdded = false;
            BSTNode node = root;
            while (!isAdded) {
                if (data < node.getData()) {
                    if (node.hasLeftChild()) {
                        node = node.getLeftChild();
                    } else {
                        node.setLeftChild(new BSTNode(data, count));
                        isAdded = true;
                    }
                } else if (data > node.getData()) {
                    if (node.hasRightChild()) {
                        node = node.getRightChild();
                    } else {
                        isAdded = true;
                    }
                } else {
                    node.setCount(node.getCount() + 1);
                    isAdded = true;
                }
            }
        }
    }

    public void BFS() {
        this.result = "";
        Queue<BSTNode> q = new LinkedList<>();
        q.add(root);

        BSTNode node;
        while (!q.isEmpty()) {
            node = q.poll();
            if (node != null) {
                for (int i = 0; i < node.getCount(); i++) {
                    result += node.getData() + ",";
                }
                q.add(node.getLeftChild());
                q.add(node.getRightChild());
            }

        }
    }

    public void DFS() {
        this.result = "";
        Stack<BSTNode> s = new Stack<>();
        s.add(root);

        BSTNode node;
        while (!s.isEmpty()) {
            node = s.pop();
            if (node != null) {
                for (int i = 0; i < node.getCount(); i++) {
                    result += node.getData() + ",";
                }
                s.add(node.getRightChild());
                s.add(node.getLeftChild());
            }

        }
    }
}
