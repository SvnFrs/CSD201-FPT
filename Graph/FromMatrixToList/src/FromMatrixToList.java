
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author chiha
 */
public class FromMatrixToList {

    private String inputFile = "ex01_input.txt";
    private String outputtFile = "ex01_output.txt";

    private String fi, fo;

    public void setFile(String[] args) {
        if (args.length >= 2) {
            fi = args[0];
            fo = args[1];
        } else {
            fi = inputFile;
            fo = outputtFile;
        }
    }

    int NumberOfVertices, valueBegin;
     //Input là list thì xài này còn matrix thì xoá
    int countEdge;
    
    GPaper graph;
    String ListGraph;
    
    //Này của matrix
    public void readData(){
        graph = new GPaper();
        try {
            Scanner sc = new Scanner(new File(fi));
            NumberOfVertices = sc.nextInt();            
            for (int i = 0; i < NumberOfVertices; i++) {
                graph.vertices.add(new GVertex(i));
            }
            
            for (int i = 0; i < this.NumberOfVertices; i++) {
                for (int j = 0; j < this.NumberOfVertices; j++) {
                    graph.graph[i][j] = sc.nextInt();
                    if (i < j && graph.graph[i][j] > 0) {
                        graph.edges.add(new GEdge(graph.graph[i][j], graph.vertices.get(i), graph.vertices.get(j)));
                    }
                }
            }
            graph.NumberOfVertices = NumberOfVertices;

            sc.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    //Này của list
  /*  public void readData() {
        graph = new GPaper();
        try {
            Scanner sc = new Scanner(new File(fi));
            NumberOfVertices = sc.nextInt();
            countEdge = sc.nextInt();
            valueBegin = sc.nextInt();
            for (int i = 0; i < NumberOfVertices; i++) {
                graph.vertices.add(new GVertex(i));
            }

            System.out.println(NumberOfVertices + " " + countEdge + " " + valueBegin);
            int start, end; int value = 1;
            for (int i = 0; i < countEdge; i++) {
                    start = sc.nextInt();
                    end = sc.nextInt();
                    System.out.println(start + " " + end);
                    graph.edges.add(new GEdge(value, graph.vertices.get(start), graph.vertices.get(end)));
                    graph.graph[start][end] = graph.graph[end][start] = value;
                }
            
           graph.NumberOfVertices = NumberOfVertices;

            sc.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
*/
    public void solve() {
        ListGraph = graph.list();
        System.out.println(ListGraph);
    }

    public void printResult() {
        try {
            FileWriter fw = new FileWriter(fo);

            fw.write(ListGraph + "\n");
            fw.flush();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        FromMatrixToList app = new FromMatrixToList();
        app.setFile(args);
        app.readData();
        app.solve();
        app.printResult();
    }

}
