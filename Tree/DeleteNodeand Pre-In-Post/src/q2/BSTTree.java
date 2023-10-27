package q2;

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
            result += node.getData() + ", ";
            System.out.print(node.getData() + ", ");
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

    public BSTNode findNode(int data) {
        BSTNode node = this.root;
        path.clear();

        while (node != null) {
            path.add(node);
            if (data == node.getData()) {
                return node;
            } else if (data < node.getData()) {
                node = node.getLeftChild();
            } else {
                node = node.getRightChild();
            }
        }

        path.clear();
        return null;
    }
    
        
    public void deleteNode(int data){
        removeNode(data);
        path.clear();
    }

    public boolean removeNode(int data) {
        BSTNode node = findNode(data);
        return removeNode(node);
    }

    public boolean removeNode(BSTNode node) {
        if (node == null) {
            return false;
        }
        node.setCount(node.getCount() - 1);
        if (node.getCount() == 0) {
            if (node.isLeaf()) {
                node.getParent().removeLeafChild(node);
                return true;
            } else {
                BSTNode incomer = null;
                if (node.hasLeftChild()) {
                    incomer = node.getLeftChild().findMaxNode();
                } else {
                    incomer = node.getRightChild().findMinNode();
                }
                node.setData(incomer.getData());
                node.setCount(incomer.getCount());

                incomer.setCount(1);
                return removeNode(incomer);
            }
        } else {
            return true;
        }
    }

}
