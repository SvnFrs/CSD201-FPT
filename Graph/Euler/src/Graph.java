
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chiha
 */
public class Graph {

    static final int MAX_VERTEX = 50;
    int[][] graph;
    int numberOfVertices;
    ArrayList<Edge> edges = new ArrayList<>();
    boolean[] isVisited = new boolean[MAX_VERTEX];

    //Euler
    ArrayList<Vertex> ec = new ArrayList<>();
    int start;

    private String inputFile = "ex01_input.txt";
    private String outputtFile = "ex01_output.txt";

    public Graph() {
        this.graph = new int[MAX_VERTEX][MAX_VERTEX];
        this.numberOfVertices = 0;
        for (int i = 0; i < MAX_VERTEX; i++) {
            for (int j = 0; j < MAX_VERTEX; j++) {
                graph[i][j] = 0;
            }
        }
    }

    void readMatrixDataFile() {
        try {
            File openFile = new File("ex01_input.txt");
            Scanner sc = new Scanner(openFile);
            this.edges.clear();
            this.numberOfVertices = sc.nextInt();
            this.start = sc.nextInt();

            for (int i = 0; i < numberOfVertices; i++) {
                for (int j = 0; j < numberOfVertices; j++) {
                    graph[i][j] = sc.nextInt();

                    //add edge
                    if (graph[i][j] > 0 && i < j) {
                        edges.add(new Edge(new Vertex(i), new Vertex(j), graph[i][j]));
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    void printGraph() {
        for (int i = 0; i < numberOfVertices; i++) {
            for (int j = 0; j < numberOfVertices; j++) {
                System.out.println(graph[i][j] + " ");
            }
            System.out.println("");
        }
    }

    public void resetIsVisited() {
        for (int i = 0; i < MAX_VERTEX; i++) {
            isVisited[i] = false;
        }
    }

    public boolean hasNeighbor(int fromVertex) {
        for (int toVertex = 0; toVertex < numberOfVertices; toVertex++) {
            if (graph[fromVertex][toVertex] > 0) {
                return true;
            }
        }
        return false;
    }

    public void euler(int start) {
        Stack<Vertex> s = new Stack<>();
        ec.clear();
        s.push(new Vertex(start));
        while (!s.isEmpty()) {
            int current = s.peek().getValue();
            if (hasNeighbor(current)) {
                int toVertex = -1;
                //get the first neighbor of the current vertex
                for (int i = 0; i < numberOfVertices; i++) {
                    if (graph[current][i] > 0) {
                        toVertex = i;
                        break;
                    }
                }
                s.push(new Vertex(toVertex));
                //remove edge
                graph[current][toVertex] = graph[toVertex][current] = 0;
            } else {
                s.pop();
                ec.add(new Vertex(current));
            }
        }
    }

    public void printEuler() {
        try {
            FileWriter fw = new FileWriter("ex01_output.txt");
            euler(start);
            if (ec.get(0).getValue() == ec.get(ec.size() - 1).getValue()) {
                for (int i = 0; i < ec.size(); i++) {
                    fw.write(ec.get(i).getValue() + (i < ec.size() - 1 ? "," : ""));
                }
                fw.write("\r\n");
            }else{
                fw.write("No rulerian cycle!\r\n");
                for (int i = 0; i < ec.size(); i++) {
                    fw.write(ec.get(i).getValue() + (i<ec.size()-1?",":""));
                }
            }
            fw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.readMatrixDataFile();
        graph.printEuler();
    }
}
