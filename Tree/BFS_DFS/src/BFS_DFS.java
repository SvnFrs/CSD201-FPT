
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
public class BFS_DFS {

    private String inputFile = "ex01_input.txt";
    private String outputtFile = "ex01_output.txt";
    
    private String fi,fo;
    
    public void setFile(String[] args){
        if(args.length>=2){
            fi = args[0];
            fo =args[1];
        }else{
            fi = inputFile;
            fo = outputtFile;
        }
    }
    
    int n;
    ArrayList<Integer> a;
    BSTTree tree;
    String BFS;
    String DFS;
    
    public void readData(){
        try {
            a = new ArrayList<>();
            Scanner sc = new Scanner(new File(fi));
            n = sc.nextInt();
            int value;
            for (int i = 0; i < n; i++) {
                value = sc.nextInt();
                a.add(value);
            }
            sc.close();
        }catch (IOException e) {
            System.err.println(e);
        }
    }
    
    public void solve(){
        tree = new BSTTree();
        for (int i = 0; i < n; i++) {
            tree.addNode(a.get(i));
        }
        
        tree.BFS();
        BFS = tree.getTraverSalResult();
        BFS = BFS.substring(0, BFS.length()-1);
        
        
        tree.DFS();
        DFS = tree.getTraverSalResult();
        DFS = DFS.substring(0, DFS.length()-1);
    }
    
    public void printResult(){
        try {
            FileWriter fw = new FileWriter(fo);
            
            fw.write(BFS + "\n");
            fw.write(DFS + "\n");
            
            fw.flush();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        BFS_DFS app = new BFS_DFS();
        app.setFile(args);
        app.readData();
        app.solve();
        app.printResult();
    }
    
}
