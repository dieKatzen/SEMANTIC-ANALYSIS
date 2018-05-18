package Visitor;

import Main.TreeNode;
import Node.*;
import SymbolTables.BaseTable;
import SymbolTables.TableRow;

public class SymTabCreationVisitor extends Visitor {

    public void visit(ProgNode node){
        System.out.println("visiting ProgNode");
        // get the class name from node 0
        node.setSymTab(new BaseTable("Global"));
    }

    public void visit(ClassDeclNode node){
        System.out.println("visiting ClassNode");
        // get the class name from node 0
        String classname = node.getChildren().get(1).getToken().getLexeme();
        // create a new table with the class name
        node.setSymTab(new BaseTable(classname));
        TreeNode temp = node.getParent();
        while(!temp.getProd().equals("prog")){
            temp = temp.getParent();
        }
        temp.getSymTab().getTableRows().add(new TableRow(classname, "class",null,node.getSymTab()));
        // loop over all children of the class and migrate their symbol table entries in class table
//        for (Node member : node.getChildren()){
//            if (member.symtabentry != null)
//                node.symtab.addEntry(member.symtabentry);
//        }
    };

    public void visit(VarDeclNode node){
        System.out.println("visiting VarDeclNode");
        // get the class name from node 0
        String varName = node.getChildren().get(1).getToken().getLexeme();
        String varType = node.getChildren().get(0).getToken().getLexeme();
        // create a new table with the class name

        TreeNode temp = node.getParent();
        while(!temp.getProd().equals("class")){
            temp = temp.getParent();
        }
        temp.getSymTab().getTableRows().add(new TableRow(varName, null,varType,null));
    }
}
