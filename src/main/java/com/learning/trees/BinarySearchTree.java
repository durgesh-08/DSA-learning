package com.learning.trees;

public class BinarySearchTree {
    Node rootNode;

    public BinarySearchTree()   {
        this.rootNode = null;
    }

    public BinarySearchTree(int value)  {
        this.rootNode = new Node();
        this.rootNode.setValue(value);
    }

    public void insert(int value)   {
        this.rootNode = insert(value, this.rootNode);
    }

    private Node insert(int value, Node node)    {
        if(node == null)    {
            node = new Node();
            node.setValue(value);
            return node;
        }
        if(value < node.getValue()) {
            node.left = insert(value, node.getLeft());
        }   else {
            node.right = insert(value, node.getRight());
        }
        return node;
    }

    public Node lookUp(int value)   {
        return lookUp(value, this.rootNode);
    }

    private Node lookUp(int value, Node node)    {
        if(node == null)    {
            return null;
        }
        if(value == node.getValue())    {
            return node;
        }
        if(value < node.getValue()) {
            return lookUp(value, node.getLeft());
        }   else if(value > node.getValue()) {
            return lookUp(value, node.getRight());
        }
        return null;
    }

    void preorder() {
        preorderRec(this.rootNode);
    }

    void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.getValue() + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }


}

class TestBinaryTree    {
    public static void main(String[] args) {
        var binaryTree = new BinarySearchTree();
        binaryTree.insert(9);
        binaryTree.insert(4);
        binaryTree.insert(6);
        binaryTree.insert(20);
        binaryTree.insert(170);
        binaryTree.insert(15);
        binaryTree.insert(1);

        binaryTree.preorder();
        var node = binaryTree.lookUp(170);
        if(node != null) {
            System.out.println(node);
        }   else {
            System.out.println("Node not found");
        }
    }
}
