import java.util.*;

class Node {
    private String value;
    private ArrayList<Node> children;
    
    Node(String value) {
        this.value = value;
        this.children = new ArrayList<Node>();
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public ArrayList<Node> getChildren() {
        return this.children;
    }
    
    // If searchValue is found in the children, returns that Node. Else we return null
    public Node returnChildNode(String searchValue) {
        for (int i=0;i<this.children.size();i++) {
            Node temp = this.children.get(i);
            if ( temp.value.equals(searchValue)) {
                return temp;
            }
        }
        return null; 
    }
    
    // If succesful returns newNode, else returns null
    public Node addChildNode(String value) {
        Node currentNode = this.returnChildNode(value);
        if (currentNode==null) {
            Node newNode = new Node(value);
            this.children.add(newNode);
            return newNode;
        } else {
            return null;
        }
    }
}

class Graph {
    private Node root;
    
    Graph() {
        this.root = new Node("/");
    }
    
    public Node getRoot() {
        return root;
    }
    
    public void printGraph() {
        System.out.println("----------------------------");
        System.out.println("Printing Graph: ");
        Node currentNode;
        ArrayList<Node> q = new ArrayList<Node>();
        q.add(this.root);
        while(q.size()!=0) {
            currentNode = q.remove(0);
            System.out.println(currentNode.getValue());
            q.addAll(currentNode.getChildren());
        }
        System.out.println("----------------------------");
    }
    
    public Node create(String path, String value) {
        String[] pathSplit = path.split("/");
        Node currentNode = this.root;
        if (pathSplit.length != 0) {
            if (pathSplit.length >= 2) {
                for(int i=1;i<pathSplit.length;i++) {
                    currentNode = currentNode.returnChildNode(pathSplit[i]);
                    if(currentNode==null) {
                        System.out.println("Specified Path Doesnt Exist");
                        return null;
                    }
                }
            } else {
                System.out.println("Specified Path format is invalid");
                return null;
            }
        }
        Node newNode = currentNode.addChildNode(value);
        if (newNode == null) {
            return null;
        }
        return newNode;
    }
    
    public boolean set_value(String path, String value) {
        if(path.equals("/")) {
            System.out.println("Specified Path refers to root. root value cant be updated");
            return false;
        }
        String[] pathSplit = path.split("/");
        Node currentNode = this.root;
        if (pathSplit.length >= 2) {
            for(int i=1;i<pathSplit.length;i++) {
                currentNode = currentNode.returnChildNode(pathSplit[i]);
                if(currentNode==null) {
                    System.out.println("Specified Path Doesnt Exist");
                    return false;
                }
            }
        } else {
            System.out.println("Specified Path format is invalid");
            return false;
        }
        currentNode.setValue(value);
        return true;
    }
    
    public String get_value(String path) {
        String[] pathSplit = path.split("/");
        pathSplit[0] = "/";
        Node currentNode = this.root;
        for(int i=0;i<pathSplit.length;i++) {
            currentNode = currentNode.returnChildNode(pathSplit[i]);
            if(currentNode==null) {
                System.out.println("Specified Path Doesnt Exist");
                return null;
            }
        }
        return currentNode.getValue();
    }
    
}

public class Main {
    
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.create("/", "a");
        graph.create("/", "b");
        graph.create("/", "f");
        graph.create("/b", "e");
        graph.create("/a", "c");
        graph.create("/a", "d");
        graph.printGraph();
        
        graph.set_value("/a/c", "cc");
        graph.printGraph();
        
        graph.set_value("/a/c", "cc");
        graph.printGraph();
        
    }
    
}

