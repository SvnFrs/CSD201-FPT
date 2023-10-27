
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

    public String getTraverSalResult() {
        return this.result;
    }

    int V, pathCount;
    int[] path;

    public void findHamiltonCycle(int start) {
        V = graph.length;
        path = new int[V];

        for (int i = 0; i < V; i++) {
            path[i] = -1;
        }

        path[0] = start;
        pathCount = 1;

        if (!findFeasibleNode(start, 1)) {
            System.out.println("Khong tim thay chu trinh Hamilton.");
        } else {
            printCycle();
        }
    }

    public boolean findFeasibleNode(int vertex, int count) {
        if (count == V) {
            if (graph[vertex][path[0]] == 1) {
                path[count] = path[0];
                return true;
            } else {
                return false;
            }
        }

        for (int v = 0; v < V; v++) {
            if (graph[vertex][v] == 1) {
                path[count] = v;
                graph[vertex][v] = 0;
                graph[v][vertex] = 0;

                if (findFeasibleNode(v, count + 1)) {
                    return true;
                }

                graph[vertex][v] = 1;
                graph[v][vertex] = 1;
                path[count] = -1;
            }
        }

        return false;
    }

    public void printCycle() {
        System.out.print("Chu trinh Hamilton: ");
        for (int i = 0; i < V; i++) {
            System.out.print(path[i] + " ");
            result += path[i] + ",";
        }

        result += path[0];
    }

}
