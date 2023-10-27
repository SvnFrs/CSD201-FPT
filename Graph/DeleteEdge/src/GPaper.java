
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author Nguyen Chi Hau CE170023
 */
public class GPaper {

    public static final int MAX_VERTEX = 50;
    public static final String SEPARATOR = " ";

    private int graphType = 0;

    int NumberOfVertices = 0;
    int[][] graph;
    ArrayList<GVertex> vertices;
    ArrayList<GEdge> edges;
    private int startIndex = -1, stopIndex = -1;
    private int edgeValue = 1;

    private String result = "";
    private boolean[] isVisited;
    private Queue<Integer> q;
    private Stack<Integer> s;

    public GPaper() {

        q = new LinkedList<Integer>();
        s = new Stack<Integer>();
        result = "";

        traversalReset();

        this.graph = new int[MAX_VERTEX][MAX_VERTEX];
        for (int i = 0; i < MAX_VERTEX; i++) {
            for (int j = 0; j < MAX_VERTEX; j++) {
                this.graph[i][j] = 0;
            }
        }
        this.NumberOfVertices = 0;
        this.vertices = new ArrayList<GVertex>();
        this.edges = new ArrayList<GEdge>();

    }

    private void traversalReset() {
        isVisited = new boolean[MAX_VERTEX];
        for (int i = 0; i < MAX_VERTEX; i++) {
            isVisited[i] = false;
        }
        q.clear();
        s.clear();
    }

    public int findVertexByValue(int v) {
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getValue() == v) {
                return i;
            }
        }
        return -1;
    }

    public int getNumberOfVertices() {
        return NumberOfVertices;
    }

    public int[][] getGraph() {
        return graph;
    }

    public ArrayList<GVertex> getVertices() {
        return vertices;
    }

    public void setGraphType(int type) {
        this.graphType = type;
    }

    public String getlResult() {
        return this.result;
    }

    public String Result() {
        return this.result;
    }

       //Này của output Matrix
    public void updateGraphInfo() {
        if (this.graphType == 0) {//MATRIX
            result += this.NumberOfVertices + "";
            for (int i = 0; i < this.NumberOfVertices; i++) {
                result += "\n" + graph[i][0];
                for (int j = 1; j < this.NumberOfVertices; j++) {
                    result += SEPARATOR + graph[i][j];
                }
            }
        }
    }
     
   /* //Này của output List
    public void updateGraphInfo() {
        int countEdge = 0;
        for (int i = 0; i < this.NumberOfVertices; i++) {
            for (int j = i + 1; j < this.NumberOfVertices; j++) {
                if (graph[i][j] > 0) {
                    result += "\n" + vertices.get(i).getLabel() + " " + vertices.get(j).getLabel() + " " + graph[i][j];
                    ++countEdge;
                }
            }
        }
        result = this.NumberOfVertices + " " + countEdge + result;
    }
*/
    public void removeEdgeByVertex(int from, int to) {
        GEdge edge;
        int edgeIndex = -1;
        for (int i = 0; i < edges.size(); i++) {
            edge = edges.get(i);
            if ((edge.getStart().getValue() == from && edge.getEnd().getValue() == to)
                    || (edge.getStart().getValue() == to && edge.getEnd().getValue() == from)) {
                edgeIndex = i;
            }
        }
        if(edgeIndex>-1){
           removeEdge(edgeIndex); 
        }
        
    }

    public void removeEdge(int index) {

        GEdge edge = this.edges.get(index);
        int from = edge.getStart().getValue();
        int to = edge.getEnd().getValue();

        graph[from][to] = 0;
        graph[to][from] = 0;
        this.edges.remove(index);
        updateGraphInfo();
    }

}
