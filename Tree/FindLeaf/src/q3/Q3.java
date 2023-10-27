/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package q3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author chiha
 */
public class Q3 {

    private String inputFile = "ex03_input.txt";
    private String outputtFile = "ex03_output.txt";

    private String fi, fo;

    public void setFile(String[] args) {
        if (args.length >= 2) {
            fi = args[0];
            fo = args[1];
        } else {
            fi = inputFile;
            fo = outputtFile;
        }
    }

    int n, m;
    ArrayList<Integer> a;
    ArrayList<Integer> leaf;
    BSTTree tree;

    String FindLeaf;
    public void readData() {
        try {
            a = new ArrayList<>();
            leaf = new ArrayList<>();
            Scanner sc = new Scanner(new File(fi));
            n = sc.nextInt();
            int value;
            for (int i = 0; i < n; i++) {
                value = sc.nextInt();
                a.add(value);
            }
            sc.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public void solve() {
        tree = new BSTTree();
        for (int i = 0; i < n; i++) {
            tree.addNode(a.get(i));
        }
        
        tree.findLeaf();
        FindLeaf = tree.getTraverSalResult();
        FindLeaf = FindLeaf.substring(0, FindLeaf.length() - 1);

    }

    public void printResult() {
        try {
            FileWriter fw = new FileWriter(fo);
            fw.write(FindLeaf);

            fw.flush();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        Q3 app = new Q3();
        app.setFile(args);
        app.readData();
        app.solve();
        app.printResult();
    }
    
}
