
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.swing.JOptionPane;

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

    public String findIsolatedVertices() {
        s.push(0);
        while (!s.isEmpty()) {
            int node = s.pop();
            if (!isVisited[node]) {

                isVisited[node] = true;
                for (int toVertex = NumberOfVertices - 1; toVertex >= 0; toVertex--) {
                    if (isVisited[toVertex] == false && graph[node][toVertex] > 0) {
                        s.push(toVertex);
                    }
                }
            }
        }
        String str = "";
        for (int i = 0; i < NumberOfVertices; i++) {
            if (!isVisited[i]) {
                str += i + ",";
            }
        }
        return str;
    }

}
