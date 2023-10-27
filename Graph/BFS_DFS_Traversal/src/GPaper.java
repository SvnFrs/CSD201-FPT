
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

    public int getNumberOfVertices() {
        return NumberOfVertices;
    }

    public int[][] getGraph() {
        return graph;
    }

    public ArrayList<GVertex> getVertices() {
        return vertices;
    }

    public String getTraverSalResult() {
        return this.result;
    }

    public void BFS(int startVertex) {
        result= "";
        int fromVertex;
        isVisited[startVertex] = true;
        q.add(startVertex);
        while (!q.isEmpty()) {
            fromVertex = q.poll();
            result += fromVertex + ",";
            for (int toVertex = 0; toVertex < NumberOfVertices; toVertex++) {
                if (isVisited[toVertex] == false && graph[fromVertex][toVertex] > 0) {
                    q.add(toVertex);
                    isVisited[toVertex] = true;
                }
            }
        }
    }

    public void DFS(int startVertex) {
        traversalReset();
        result= "";
        int fromVertex;
        s.push(startVertex);

        while (!s.isEmpty()) {
            fromVertex = s.pop();
            if (isVisited[fromVertex] == false) {
                result += fromVertex + ",";
                isVisited[fromVertex] = true;
                for (int toVertex = NumberOfVertices - 1; toVertex >= 0; toVertex--) {
                    if (isVisited[toVertex] == false && graph[fromVertex][toVertex] > 0) {
                        s.push(toVertex);
                    }
                }
            }
        }
    }

}
