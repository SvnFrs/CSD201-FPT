
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

    public int findEdgeByVertex(int from, int to) {
        GEdge edge;
        for (int i = 0; i < edges.size(); i++) {
            edge = edges.get(i);
            if ((edge.getStart().getValue() == from && edge.getEnd().getValue() == to)
                    || (edge.getStart().getValue() == to && edge.getEnd().getValue() == from)) {
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
/*   //Này của output Matrix
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
*/
    
    //Này của output List
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
    
    public void removeVertex(int index) {
        for (int from = 0; from < this.NumberOfVertices - 1; from++) {
            for (int to = 0; to < this.NumberOfVertices; to++) {
                graph[from][to] = graph[from + 1][to];
                graph[to][from] = graph[to][from + 1];
            }
        }
        this.NumberOfVertices--;

        GEdge edge;
        for (int i = this.edges.size() - 1; i >= 0; i--) {
            edge = this.edges.get(i);
            if (edge.getStart().getValue() == index || edge.getEnd().getValue() == index) {
                this.edges.remove(i);
            }
        }

        this.vertices.remove(index);
        for (int i = index; i < this.NumberOfVertices; i++) {
            this.vertices.get(i).setValue(this.vertices.get(i).getValue() - 1);
        }
        updateGraphInfo();
    }
}
