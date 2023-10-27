package q3;

import java.util.ArrayList;

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
                        node.setRightChild(new BSTNode(data, count));
                        isAdded = true;
                    }
                } else {
                    isAdded = true;
                }
            }
        }
    }

    public void findLeaf() {
        result = "";
        findLeaf(root);
    }

    private void findLeaf(BSTNode node) {
        if (node == null) {
            return;
        }

        for (int i = 0; i < node.getCount(); i++) {
            if (node.getLeftChild() == null && node.getRightChild() == null) {
                result += node.getData() + ",";
            }
        }

        findLeaf(node.getLeftChild());
        findLeaf(node.getRightChild());
    }
}
