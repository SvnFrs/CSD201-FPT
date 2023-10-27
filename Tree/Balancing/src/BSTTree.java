

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
    ArrayList<NodeData> treeData;

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
        treeData = new ArrayList<>();
    }

    public BSTTree(int screenWidth, int yMin) {
        root = null;
        this.screenWidth = screenWidth;
        this.yMin = yMin;
        result = "";
        path = new ArrayList<>();
        treeData = new ArrayList<>();
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
                    node.setCount(node.getCount() + 1);
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
                    node.setCount(node.getCount() + 1);
                    isAdded = true;
                }
            }
        }
    }

    public void preOrder() {
        result = "";
        preOrder(root);
    }

    private void preOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        for (int i = 0; i < node.getCount(); i++) {
            result += node.getData() + ",";
            System.out.print(node.getData() + ",");
        }
        preOrder(node.getLeftChild());
        preOrder(node.getRightChild());
    }

    public void inOrder() {
        result = "";
        inOrder(root);
    }

    private void inOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        inOrder(node.getLeftChild());
        for (int i = 0; i < node.getCount(); i++) {
            result += node.getData() + ",";
            System.out.print(node.getData() + ",");
        }
        inOrder(node.getRightChild());
    }

    public void postOrder() {
        result = "";
        postOrder(root);
    }

    private void postOrder(BSTNode node) {
        if (node == null) {
            return;
        }
        postOrder(node.getLeftChild());
        postOrder(node.getRightChild());
        for (int i = 0; i < node.getCount(); i++) {
            result += node.getData() + ",";
            System.out.print(node.getData() + ",");
        }
    }

    public ArrayList<BSTNode> getPath() {
        return this.path;
    }

    public void clear() {
        clear(this.root);
        this.root = null;
    }

    public void clear(BSTNode node) {
        if (node == null) {
            return;
        }
        if (node.isLeaf()) {
            node.getParent().removeLeafChild(node);
        } else {
            clear(node.getLeftChild());
            clear(node.getRightChild());
        }
    }

    private void BST2Array(BSTNode node) {
        if (node == null) {
            return;
        }
        BST2Array(node.getLeftChild());
        treeData.add(new NodeData(node.getData(), node.getCount()));
        BST2Array(node.getRightChild());
    }

    public void blacing() {
        treeData.clear();
        BST2Array(this.root);//store BST into ascending order array

        this.clear();//remove all node from this BST
        Queue<BSTRange> q = new LinkedList<>();
        q.add(new BSTRange(0, treeData.size() - 1));
        BSTRange range;
        NodeData nodeData;
        int middleIndex, leftIndex, rightIndex;
        while (!q.isEmpty()) {
            range = q.poll();
            leftIndex = range.getLeftIndex();
            rightIndex = range.getRightIndex();
            if (leftIndex <= rightIndex) {
                middleIndex = (leftIndex + rightIndex) / 2;
                nodeData = treeData.get(middleIndex);
                this.addNode(nodeData.getData(), nodeData.getCount());

                q.add(new BSTRange(leftIndex, middleIndex - 1));
                q.add(new BSTRange(middleIndex + 1, rightIndex));
            }
        }
    }
}


