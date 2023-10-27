
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
        distance = new int[MAX_VERTEX];
        theVertexBefore = new int[MAX_VERTEX];
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

    public int getSum() {
        return this.sum;
    }

    int[] distance;
    int[] theVertexBefore;
    public static final int Infinity = 1000000000;
    boolean isGraphConnected;
    boolean isDrawPrimPath = false;
    int sum;
    ArrayList<Integer> dijkstra_theVertexBefore[]; // tu 1 dinh co the co nhieu dinh di toi
    ArrayList<String> dijkstraPath;
    String dijkstraMessage = "";
    int pathIndex = 0;

    public int findNearestVertex() {
        int minIndex = -1, minValue = Infinity;
        for (int i = 0; i < NumberOfVertices; i++) {
            if (isVisited[i] == false && distance[i] < minValue) {
                minValue = distance[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

    public void DijktraReset() {
        dijkstra_theVertexBefore = new ArrayList[MAX_VERTEX];
        for (int i = 0; i < MAX_VERTEX; i++) {
            distance[i] = Infinity;
            dijkstra_theVertexBefore[i] = new ArrayList<>();
            dijkstra_theVertexBefore[i].add(i);
            isVisited[i] = false;
        }
        dijkstraPath = new ArrayList<>();
        dijkstraMessage = "";
        pathIndex = 0;
    }

    public void Dijkstra(int startVertex, int endVertex) {

        DijktraReset();
        distance[startVertex] = 0;

        int currentVertex;
        isGraphConnected = true;
        for (int i = 0; i < NumberOfVertices; i++) {
            currentVertex = findNearestVertex();
            if (currentVertex == -1) {
                //do thi khong lien thong -> ko tim thay cay khung co trong so nho nhat
                isGraphConnected = false;
                break;
            } else {
                isVisited[currentVertex] = true;
                for (int toVertex = 0; toVertex < NumberOfVertices; toVertex++) {
                    if ((isVisited[toVertex] == false || toVertex == endVertex) //neu da di den roi thi van cho quet 1 lan nx
                            && graph[currentVertex][toVertex] > 0
                            && distance[currentVertex] + graph[currentVertex][toVertex] <= distance[toVertex]) {
                        if (distance[currentVertex] + graph[currentVertex][toVertex] < distance[toVertex]) {
                            dijkstra_theVertexBefore[toVertex].clear();
                        }
                        distance[toVertex] = distance[currentVertex] + graph[currentVertex][toVertex];
                        dijkstra_theVertexBefore[toVertex].add(currentVertex);
                    }
                }
            }
        }
        if (isGraphConnected) {
            dijkstraPath.clear();
            String path = "" + endVertex;
            currentVertex = endVertex;
            Dijkstra_displayPath(path, currentVertex, startVertex, endVertex);

            sum = distance[endVertex];
        } else {
            dijkstraMessage = "Can't find path from" + startVertex + " to " + endVertex + "!";
        }
        isDrawPrimPath = false;
    }

    public void Dijkstra_displayPath(String path, int currentVertex, int startVertex, int endVertex) {
        if (currentVertex != endVertex) {
            path = currentVertex + "->" + path;
        }

        if (currentVertex == startVertex) {
            dijkstraPath.add(path);
        } else {
            //Xem phia truoc co bao nhieu dinh
            for (int i = 0; i < dijkstra_theVertexBefore[currentVertex].size(); ++i) {
                Dijkstra_displayPath(path, dijkstra_theVertexBefore[currentVertex].get(i), startVertex, endVertex);
            }
        }
    }

    public void displayPath() {
        for (String string : dijkstraPath) {
            result += string + "\n";
        }
    }

}
