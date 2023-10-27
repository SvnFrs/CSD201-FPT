
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
public class Balancing {

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
    String preOrderResult;
    String postOrderResult;
    String inOrderResult;
    
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
        
        tree.blacing();
        
        tree.preOrder();
        preOrderResult = tree.getTraverSalResult();
        preOrderResult = preOrderResult.substring(0, preOrderResult.length()-1);
        
        
        tree.postOrder();
        postOrderResult = tree.getTraverSalResult();
        postOrderResult = postOrderResult.substring(0, postOrderResult.length()-1);
        
        tree.inOrder();
        inOrderResult = tree.getTraverSalResult();
        inOrderResult = inOrderResult.substring(0, inOrderResult.length()-1);
    }
    
    public void printResult(){
        try {
            FileWriter fw = new FileWriter(fo);
            
            fw.write(preOrderResult + "\n");
            fw.write(postOrderResult + "\n");
            fw.write(inOrderResult + "\n");
            
            fw.flush();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    public static void main(String[] args) {
        // TODO code application logic here
        Balancing app = new Balancing();
        app.setFile(args);
        app.readData();
        app.solve();
        app.printResult();
    }
    
}
