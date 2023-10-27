

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


    public ArrayList<BSTNode> getPath() {
        return this.path;
    }

/**
 * Tim tong do cao cua cay
 */
    public int getHeight(BSTNode node) {
        if (node == null) {
            return -1;
        } else {
            int leftHeight = getHeight(node.getLeftChild());
            int rightHeight = getHeight(node.getRightChild());
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public int getHeight() {
        return getHeight(root);
    }
    
    /**
     * Tim do cao cua nut nguoi dung nhap vao
     */
    public int getNodeHeight(int value) {
    BSTNode node = root;
    int height = 0;

    while (node != null) {
        if (value == node.getData()) {
            return height;
        } else if (value < node.getData()) {
            node = node.getLeftChild();
            height++;
        } else {
            node = node.getRightChild();
            height++;
        }
    }

    return -1; // Nếu nút không tồn tại trong BST
}

}

