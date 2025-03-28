package com.learning.trees;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree {
    Node rootNode;

    public BinarySearchTree()   {
        this.rootNode = null;
    }

    public BinarySearchTree(int value)  {
        this.rootNode = new Node();
        this.rootNode.setValue(value);
    }

    public Node getRootNode() {
        return rootNode;
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

    public boolean remove(int value)    {
        var nodes = getNodeAndParentNode(value, this.rootNode, null);
        if(nodes == null)   {
            return false;
        }
        var valueNode = nodes.get(0);
        var parentNode = nodes.get(1);
        return remove(valueNode, parentNode, value);
    }

    private boolean remove(Node node, Node parentNode, int value)   {
        if(node == null)    {
            return false;
        }
        if(node.left == null && node.right == null) {
            if (parentNode == null) {
                this.rootNode = null;
                return true;
            } else if (parentNode.getLeft().getValue() == value) {
                parentNode.setLeft(null);
                return true;
            } else if (parentNode.getRight().getValue() == value) {
                parentNode.setRight(null);
                return true;
            }
            return false;
        }   else if(node.getLeft() == null || node.getRight() == null)   {
            Node child = (node.left != null) ? node.left : node.right;
            if(parentNode == null)  {
                this.rootNode = child;
            }   else if(parentNode.getLeft() == node)   {
                parentNode.setLeft(child);
            }   else {
                parentNode.setRight(child);
            }
            return true;

        }   else {
            var nodeList = findNodeForReplacingRemovedValue(node.getRight(), node);
            var replacingParentNode = nodeList.get(1);
            var replacingNode = nodeList.get(0);
            node.setValue(replacingNode.getValue());
            if(replacingParentNode.getLeft().equals(replacingNode)) {
                replacingParentNode.setLeft(replacingNode.getRight());
            }   else {
                replacingParentNode.setRight(replacingNode.getRight());
            }
            return true;
        }

    }



    private List<Node> getNodeAndParentNode(int value, Node node, Node parentNode)   {
        if(node == null)    {
            return null;
        }
        if(node.getValue() == value)    {
            var list = new ArrayList<Node>();
            list.add(node);
            list.add(parentNode);
            return list;
        }
        if(value < node.getValue()) {
            return getNodeAndParentNode(value, node.getLeft(), node);
        }   else if(value > node.getValue())   {
            return getNodeAndParentNode(value, node.getRight(), node);
        }
        return null;
    }

    private List<Node> findNodeForReplacingRemovedValue(Node node, Node parentNode)    {
        if(node.getLeft() == null && node.getRight() == null)   {
            return List.of(node, parentNode);
        }   else if(node.getLeft() != null) {
            return findNodeForReplacingRemovedValue(node.getLeft(), node);
        }
        return List.of(node,parentNode);
    }

}

class TestBinaryTree    {
    public static void main(String[] args) {
        var binaryTree = new BinarySearchTree();
//        binaryTree.insert(9);
//        binaryTree.insert(4);

        var node = binaryTree.lookUp(9);
        if(node != null) {
            System.out.println(node);
        }   else {
            System.out.println("Node not found");
        }
        System.out.println(binaryTree.remove(9));
        System.out.println(binaryTree.getRootNode());
    }
}
