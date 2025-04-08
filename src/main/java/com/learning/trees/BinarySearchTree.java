package com.learning.trees;

import java.util.*;

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
        if(node == null)   {
            return null;
        }
        if(node.getValue()==value)  {
            return node;
        }
        else if(value < node.getValue())    {
            return lookUp(value,node.getLeft());
        }   else if(value > node.getValue())    {
            return lookUp(value, node.getRight());
        }
        return null;
    }

    public Node searchBST(Node root, int val)    {
        if(root == null)   {
            return null;
        }
        if(root.value==val)  {
            return root;
        }
        else if(val < root.value)    {
            return searchBST(root.left,val);
        }   else if(val > root.value)    {
            return searchBST(root.right, val);
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

    // Breadth First Search
    public void levelOrder(Node rootNode)   {
        Queue<Node> nodeQueue = new LinkedList<>();
        if(rootNode!=null)  {
            nodeQueue.offer(rootNode);
        }
        while(!nodeQueue.isEmpty()) {
            var node = nodeQueue.poll();
            System.out.print(node.value);
            System.out.print(" ");
            if (node.left != null)   {
                nodeQueue.offer(node.left);
            }
            if(node.right != null)  {
                nodeQueue.offer(node.right);
            }
        }
    }

//    Depth first search - recursive
    public void getInOrder(Node rootNode) {
        if(rootNode == null)    {
            return;
        }
        getInOrder(rootNode.left);
        System.out.print(rootNode.value);
        System.out.print(" ");
        getInOrder(rootNode.right);
    }

    public void getPreOrder(Node rootNode)    {
        if(rootNode == null)    {
            return;
        }
        System.out.print(rootNode.value);
        System.out.print(" ");
        getPreOrder(rootNode.left);
        getPreOrder(rootNode.right);
    }

    public void getPostOrder(Node rootNode)   {
        if(rootNode == null)    {
            return;
        }
        getPostOrder(rootNode.left);
        getPostOrder(rootNode.right);
        System.out.print(rootNode.value);
        System.out.print(" ");
    }

    // DFS - iterative

    public void getPreOrderIterative(Node rootNode)   {
        Stack<Node> stack = new Stack<>();
        if(rootNode != null)    {
            stack.push(rootNode);
        }
        while(!stack.isEmpty()) {
            var node = stack.pop();
            System.out.print(node.value);
            System.out.print(" ");
            if(node.right != null)  {
                stack.push(node.right);
            }
            if(node.left != null)   {
                stack.push(node.left);
            }
        }
    }

    public void getInOrderIterative(Node rootNode)   {
        Stack<Node> stack = new Stack<>();
        Node current = rootNode;
        while(!stack.isEmpty() || current != null)  {
            while(current != null)  {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            System.out.print(current.value + " ");
            current = current.right;
        }
    }

    public void getPostOrderIterative(Node rootNode)    {
        if(rootNode == null) return;
        var stack1 = new Stack<Node>();
        var stack2 = new Stack<Node>();
        stack1.push(rootNode);
        while(!stack1.isEmpty())    {
            var node = stack1.pop();
            stack2.push(node);
            if(node.left!=null) stack1.push(node.left);
            if(node.right!= null) stack1.push(node.right);
        }

        while(!stack2.isEmpty())    {
            System.out.print(stack2.pop().value + " ");
        }
    }
}

class TestBinaryTree    {
    public static void main(String[] args) {
        var binaryTree = new BinarySearchTree();
//        binaryTree.insert(9);
//        binaryTree.insert(4);
//        binaryTree.insert(1);
//        binaryTree.insert(6);
//        binaryTree.insert(5);
//        binaryTree.insert(7);
//        binaryTree.insert(12);
//        binaryTree.insert(15);
//        binaryTree.insert(14);
        binaryTree.insert(4);
        binaryTree.insert(2);
        binaryTree.insert(7);
        binaryTree.insert(1);
        binaryTree.insert(3);

        var node = binaryTree.searchBST(binaryTree.getRootNode(),2);
        if(node != null) {
            System.out.println(node);
        }   else {
            System.out.println("Node not found");
        }
//        System.out.println(binaryTree.remove(9));
//        System.out.println(binaryTree.getRootNode());
//        System.out.println("Printing Inorder");
//        binaryTree.getInOrder(binaryTree.getRootNode());
//        System.out.println();
//        System.out.println("Printing Preorder");
//        binaryTree.getPreOrder(binaryTree.getRootNode());
//        System.out.println();
//        System.out.println("Printing Postorder");
//        binaryTree.getPostOrder(binaryTree.getRootNode());
//        System.out.println();
//        System.out.println("Printing levelOrder");
//        binaryTree.levelOrder(binaryTree.getRootNode());
//        System.out.println();
//        System.out.println("Printing Preorder Iteratively");
//        binaryTree.getPreOrderIterative(binaryTree.getRootNode());
//        System.out.println();
//        System.out.println("Printing Inorder Iteratively");
//        binaryTree.getInOrderIterative(binaryTree.getRootNode());
//        System.out.println();
//        System.out.println("Printing Postorder Iteratively");
//        binaryTree.getPostOrderIterative(binaryTree.getRootNode());
//        System.out.println();
    }
}
