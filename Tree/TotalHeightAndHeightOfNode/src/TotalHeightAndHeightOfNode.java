
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
public class TotalHeightAndHeightOfNode {

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
    
    int n,m;
    ArrayList<Integer> a;
    ArrayList<Integer> node;
    BSTTree tree;
    String HeightOfNode="";
    int total_Height;
    String totalHeight ="";
    
    public void readData(){
        try {
            a = new ArrayList<>();
            node = new ArrayList<>();
            Scanner sc = new Scanner(new File(fi));
            n = sc.nextInt();
            int value;
            for (int i = 0; i < n; i++) {
                value = sc.nextInt();
                a.add(value);
            }
            
            m = sc.nextInt();
            for (int i = 0; i < m; i++) {
                value = sc.nextInt();
                node.add(value);
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
        
        //totalHeight
        total_Height=tree.getHeight();
        totalHeight=totalHeight+total_Height;
        
        //height of node
        for (Integer integer : node) {
            HeightOfNode+=tree.getNodeHeight(integer)+" ";
        }
        HeightOfNode = HeightOfNode.substring(0,HeightOfNode.length()-1);
    }
    
    public void printResult(){
        try {
            FileWriter fw = new FileWriter(fo);
            
            fw.write(totalHeight + "\n");
            fw.write(HeightOfNode + "\n");
            
            fw.flush();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        TotalHeightAndHeightOfNode app = new TotalHeightAndHeightOfNode();
        app.setFile(args);
        app.readData();
        app.solve();
        app.printResult();
    }
    
}
