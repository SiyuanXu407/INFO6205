import java.util.Scanner;
public class Tree {
    private Node root;

    protected class Node{
        int data;
        Node rightChild;
        Node leftChild;

        public Node(int data){
            this.data = data;
        }
    }

    public Tree(){
        root = null;
    }

    /* Function to check if tree is empty */
    public boolean isEmpty(){
        return root == null;
    }

    public Node getRoot() {
        return root;
    }

    /* Functions to insert data */
    public void insert(int data){
        root = insert(root, data);
    }

    /* Functions to search for an element */
    public boolean search(int val){
        return search(root, val);
    }

    /* Functions to count number of nodes */
    public int countNodes(){
        return countNodes(root);
    }


    private int countNodes(Node r){
        if (r == null)
            return 0;
        else
        {
            int l = 1;
            l += countNodes(r.leftChild);
            l += countNodes(r.rightChild);
            return l;
        }
    }
    private Node insert(Node node, int data){
        //TODO:: Insert a node into the BST
        if(node ==null){
            node = new Node(data);
            return node;
        }

        if(data < node.data){

            node.leftChild = insert(node.leftChild, data);
        }else if(data > node.data){

            node.rightChild = insert(node.rightChild, data);
        }

        return node;
    }

    private boolean search(Node r, int val){
        //TODO:: Search for a node in the BST
        if(r ==null || r.data == val){
            return true;
        }

        if(r.data > val){
            return search(r.leftChild,val);

        }else if(r.data < val){
            return search(r.rightChild,val);
        }

        return false;
    }
    /* Functions for inorder traversal */
    public void inorder(){
        inorder(root);
    }

    private void inorder(Node r){
        if (r != null)
        {
            inorder(r.leftChild);
            System.out.print(r.data +" ");
            inorder(r.rightChild);
        }
    }

    /* Functions for preorder traversal */
    public void preorder(){
        preorder(root);
    }

    private void preorder(Node r)
    {
        if (r != null)
        {
            System.out.print(r.data +" ");
            preorder(r.leftChild);
            preorder(r.rightChild);
        }
    }

    /* Function for postorder traversal */
    public void postorder(){
        postorder(root);
    }

    private void postorder(Node r){
        if (r != null)
        {
            postorder(r.leftChild);
            postorder(r.rightChild);
            System.out.print(r.data +" ");
        }
    }

    public static void main(String args[] ) throws Exception {
        Scanner scan = new Scanner(System.in);
        Tree bst = new Tree();
        int numberOfOperations = scan.nextInt();
        for(int i=0;i<numberOfOperations;i++){
            String ops = scan.next();
            String[] opArray = ops.split(",");
            switch(opArray[0]){
                case "1" :
                    bst.insert(Integer.parseInt(opArray[1]));
                    break;
                case "2" :
                    System.out.println(bst.search(Integer.parseInt(opArray[1])));
                    break;
                case "3" :
                    System.out.println(bst.isEmpty());
                    break;
                case "4" :
                    System.out.println(bst.countNodes());
                    break;
                case "5" :
                    bst.preorder();
                    break;
                case "6" :
                    bst.inorder();
                    break;
                case "7" :
                    bst.postorder();
                    break;
            }
        }
    }
}