package Main;

import Lexer.Lexer;
import Lexer.Token;
import Visitor.SymTabCreationVisitor;

import java.io.PrintWriter;
import java.util.*;

public class Main {
    Tree ast;

//    public static void printTree(Tree ast1) {
//
//
//
//        ast1 = setLevel(ast1);
//        System.out.println("\n Level is :0\n     " + ast1.getStartNode().getProd().toString());
//
//        HashMap<Integer, ArrayList<TreeNode>> hm = new HashMap<Integer, ArrayList<TreeNode>>();
//        hm = printTreeRecurse(ast1.getStartNode(),hm);
//        int cnt=1;
//        while(hm.get(cnt)!=null){
//            System.out.println("Level is :" + cnt);
//            Iterator hmi = hm.get(cnt).iterator();
//            while(hmi.hasNext()){
//                TreeNode next = ((TreeNode)hmi.next());
//                if(next.getToken()!= null){
//                    System.out.print("["+next.getToken().getLexeme()+"]");
//                }
//                System.out.print(next.getProd().toString()+"{"+next.val+"}" + " ("+next.returnType()+")[" + next.getLevel() + "]   ||     ");
//            }
//            System.out.println();
//            System.out.println();
//            cnt++;
//        }
//
//    }

    public static void printTreeVisual(Tree ast1) {



        ast1 = setLevel(ast1);
        TreeNode next = ((TreeNode)ast1.startNode.getChildren().get(0));
        System.out.println("\n\n\nProg");


        int cnt=1;
        while(true) {

            for(int i=0; i<next.getLevel(); i++){
//                if(i==next.getLevel()-1) {
//                    System.out.print("^ ---------");
//                }else{
                    System.out.print("     ");
//                }
            }

            if (next.getToken() != null) {
                System.out.print("[" + next.getLevel() + "]" + "[" + next.getToken().getLexeme() + "]");
            }
            System.out.print("[" + next.getLevel() + "]" + next.getProd().toString() + "{" + next.val + "}" + "(" + next.returnType() + ") ");

            if(next.getChildren()!=null && next.getChildren().size()!=0){
                next = next.getChildren().get(0);
            } else if (next.getChildren()==null || next.getChildren().size()==0){
                    while (next.getSibling() == null) {
                        next = next.getParent();
                        if (next.getProd().toString().equals("prog")) {
                            System.out.println("\n finished");
                            return;
                        }
                    }
                    next = next.getSibling();
            }
            System.out.println();
        }



    }

    public static Tree setLevel(Tree ast){
        setLevelHelper(ast.getStartNode(), 0);
        return ast;
    }

    public static void setLevelHelper(TreeNode node, int o){

        node.setLevel(o);

        if(node.getChildren() == null) return;

        for (TreeNode child : node.getChildren())
        {
            setLevelHelper(child, o+1);
        }
    }


//    public  static HashMap<Integer,  ArrayList<TreeNode>> printTreeRecurse(TreeNode n,HashMap<Integer,  ArrayList<TreeNode>> hm) {
//        for(TreeNode tn: n.getChildren()){
//            ArrayList <TreeNode> hms = hm.get(tn.getLevel());
//            if(hms == null){
//                ArrayList<TreeNode> as = new ArrayList<TreeNode>();
//                as.add(tn);
//                hm.put(tn.getLevel(),as);
//            }else{
//             hms.add(tn);
//            }
//            if(tn.getChildren()!=null){
//                printTreeRecurse(tn,hm);
//            }
//        }
//        return hm;
//    }




    public static void main(String[] args) {

        Scanner scanner = null;
        PrintWriter pw = null;
        String input = null;




        try {

            scanner = new Scanner(System.in);






        Lexer lexer;
        lexer = new Lexer();
        ParseTable pt = new ParseTable();
        Tree ast = new Tree ();
        List<Token> tokenList = new ArrayList<Token>();
        input = scanner.nextLine();
        tokenList = lexer.returnTokenList(tokenList,input);
        ast = pt.generateTree(ast, tokenList);
        System.out.println(ast.toString());

        printTreeVisual(ast);

        Tree properAST = ast.toAstProper();
//        printTree(properAST);
            System.out.println("Proper AST");

        printTreeVisual(properAST);

        SymTabCreationVisitor sTCV = new SymTabCreationVisitor();
        ast.getStartNode().accept(sTCV);
        ast.getStartNode().getSymTab();

        } catch(Exception e){

            System.out.println("what happened?"+ e.toString() + "  " );

            e.printStackTrace();
        }

    }
}
