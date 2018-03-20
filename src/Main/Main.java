package Main;

import Lexer.Lexer;
import Lexer.Token;

import java.util.*;

public class Main {
    Tree ast;

    public static void printTree(Tree ast1) {
        System.out.println("\n Level is :0\n     " + ast1.getStartNode().getProd().toString());

        HashMap<Integer, ArrayList<TreeNode>> hm = new HashMap<Integer, ArrayList<TreeNode>>();
        hm = printTreeRecurse(ast1.getStartNode(),hm);
        int cnt=1;
        while(hm.get(cnt)!=null){
            System.out.println("Level is :" + cnt);
            Iterator hmi = hm.get(cnt).iterator();
            while(hmi.hasNext()){
                System.out.print("     " + ((TreeNode)hmi.next()).getProd().toString());
            }
            System.out.println();
            System.out.println();
            cnt++;
        }
    }

    public  static HashMap<Integer,  ArrayList<TreeNode>> printTreeRecurse(TreeNode n,HashMap<Integer,  ArrayList<TreeNode>> hm) {
        for(TreeNode tn: n.getChildren()){
            ArrayList <TreeNode> hms = hm.get(tn.getLevel());
            if(hms == null){
                ArrayList<TreeNode> as = new ArrayList<TreeNode>();
                as.add(tn);
                hm.put(tn.getLevel(),as);
            }else{
             hms.add(tn);
            }
            if(tn.getChildren()!=null){
                printTreeRecurse(tn,hm);
            }
        }
        return hm;
    }




    public static void main(String[] args) {
        Lexer lexer;
        lexer = new Lexer();
        ParseTable pt = new ParseTable();
        Tree ast = new Tree ();
        List<Token> tokenList = new ArrayList<Token>();
        tokenList = lexer.returnTokenList(tokenList);
        ast = pt.generateTree(ast, tokenList);
        System.out.println(ast.toString());
        Tree ProperlyNodedTree = new Tree ();
        ProperlyNodedTree   = ast.reNode();

        printTree(ast);

    }
}
