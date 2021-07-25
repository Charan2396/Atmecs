import java.util.*;

class Node {
    private String id;
    private String value;
    private ArrayList<Node> children;
    
    Node(String id) {
        this.id = id;
        this.value = null;
        this.children = new ArrayList<Node>();
    }
    
    Node(String id, String value) {
        this.id = id;
        this.value = value;
        this.children = new ArrayList<Node>();
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String getId() {
        return this.id;
    }
    
    public ArrayList<Node> getChildren() {
        return this.children;
    }
    
    // If searchValue is found in the children, returns that Node. Else we return null
    public Node returnChildNode(String searchId) {
        for (int i=0;i<this.children.size();i++) {
            Node temp = this.children.get(i);
            if ( temp.id.equals(searchId)) {
                return temp;
            }
        }
        return null; 
    }
    
    // If succesful returns newNode, else returns null
    public Node addChildNode(String id) {
        Node currentNode = this.returnChildNode(id);
        if (currentNode==null) {
            Node newNode = new Node(id);
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
            System.out.println(currentNode.getId() + " -> " + currentNode.getValue());
            q.addAll(currentNode.getChildren());
        }
        System.out.println("----------------------------");
    }
    
    public Node create(String path, String newNodeId) {
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
        Node newNode = currentNode.addChildNode(newNodeId);
        if (newNode == null) {
            return null;
        }
        return newNode;
    }
    
    public boolean set_value(String path, String value) {
        if(path.equals("/")) {
            this.root.setValue(value);
            return true;
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
        if(path.equals("/")) {
            return this.root.getValue();
        }
        String[] pathSplit = path.split("/");
        Node currentNode = this.root;
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
        graph.create("/a/d", "m");
        graph.create("/a/d/m", "n");
        graph.printGraph();
        
        graph.set_value("/", "root");
        graph.set_value("/a", "aa");
        graph.set_value("/b", "bb");
        graph.set_value("/f", "ff");
        graph.set_value("/a/c", "cc");
        graph.set_value("/a/d", "dd");
        graph.set_value("/a/d/m", "mm");
        graph.set_value("/a/d/m", "mmm");
        graph.set_value("/a/d/m/n", "nn");
        graph.printGraph();
        
        String val = graph.get_value("/a/d");
        System.out.println(val);
        
    }
    
}
