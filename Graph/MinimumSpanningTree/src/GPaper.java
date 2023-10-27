
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

    private int mouseX, mouseY, selectedVertexIndex = -1, selectedEdgeIndex = -1;
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

        this.q = new LinkedList<Integer>();
        this.s = new Stack<Integer>();

        this.mouseX = 0;
        this.mouseY = 0;
        this.selectedVertexIndex = -1;
        this.selectedEdgeIndex = -1;

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

    int[] distance;
    int[] theVertexBefore;
    public static final int Infinity = 1000000000;
    boolean isGraphConnected;
    boolean isDrawPrimPath = false;
    
    public void PrimReset() {
        for (int i = 0; i < MAX_VERTEX; i++) {
            distance[i] = Infinity;
        }
        for (int i = 0; i < MAX_VERTEX; i++) {
            theVertexBefore[i] = i;
            isVisited[i] = false;
        }
        isDrawPrimPath = true;
    }

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

    public int PrimTotal() {
        int sum = 0;
        if (isDrawPrimPath == true) {
            if (isGraphConnected) {
                
                for (int i = 0; i < NumberOfVertices; i++) {
                    sum += distance[i];
                }

                int fromVertex;
                int toVertex;
                int edgeIndex;
                for (int i = 0; i < NumberOfVertices; i++) {
                    fromVertex = theVertexBefore[i];
                    toVertex = i;
                    if (fromVertex != toVertex) {
                        edgeIndex = findEdgeByVertex(fromVertex, toVertex);
                        if (edgeIndex != -1) {
                            edges.get(edgeIndex).setSelected(true);
                        }
                    }
                }
                for (int i = 0; i < vertices.size(); i++) {
                    vertices.get(i).setSelected(true);
                }
                for (int i = 0; i < NumberOfVertices; i++) {
                    fromVertex = theVertexBefore[i];
                    toVertex = i;
                    if (fromVertex != toVertex) {
                        edgeIndex = findEdgeByVertex(fromVertex, toVertex);
                        if (edgeIndex != -1) {
                            edges.get(edgeIndex).setSelected(false);
                        }
                    }
                }
                for (int i = 0; i < vertices.size(); i++) {
                    vertices.get(i).setSelected(false);
                }
            }
        }
        return sum;
    }

    public void Prim() {
        PrimReset();
        distance[0] = 0;
        int currentVertex;
        isGraphConnected = true;
        for (int i = 0; i < NumberOfVertices; i++) {
            currentVertex = findNearestVertex();
            if (currentVertex == -1) {
                //do thi khong lien thong -> ko tim thay cay khung co trong so nho nhat
                isGraphConnected = false;
                return;
            } else {
                isVisited[currentVertex] = true;
                for (int toVertex = 0; toVertex < NumberOfVertices; toVertex++) {
                    if (isVisited[toVertex] == false && graph[currentVertex][toVertex] > 0 && graph[currentVertex][toVertex] < distance[toVertex]) {
                        distance[toVertex] = graph[currentVertex][toVertex];
                        theVertexBefore[toVertex] = currentVertex;
                    }
                }
            }
        }
    }
}
