package Main;

import Lexer.Token;
import Node.*;
import SymbolTables.BaseTable;
import Visitor.Visitor;
import com.sun.org.apache.xerces.internal.util.SymbolTable;

import java.util.ArrayList;

public class TreeNode{
    Token token =null;
    String prod;
    String val;
    int level;
    Boolean isBegin=false;
    Boolean isEnd=false;
    BaseTable symTab = null;
    TreeNode sibling;
    TreeNode parent;
    ArrayList<TreeNode> children = null;

    public BaseTable getSymTab() {
        return symTab;
    }

    public void setSymTab(BaseTable symTab) {
        this.symTab = symTab;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getProd() {
        return prod;
    }

    public String returnType(){
        return this.getClass().getSimpleName();
    }

    public void setProd(String prod) {
        this.prod = prod;
    }



    public Token getToken() {
        return token;
    }

    public TreeNode(String prod,Token token, TreeNode parent, Boolean isEnd) {
        this.prod = prod;
        this.isEnd = isEnd;
        this.parent = parent;
        this.token = token;
        if(parent != null) {
            this.level = parent.getLevel() + 1;
        }else{
            this.level = 0;
        }
    }

    public TreeNode(String prod, TreeNode parent, Boolean isEnd) {
        this.prod = prod;
        this.isEnd = isEnd;
        this.parent = parent;
        if(parent != null) {
            this.level = parent.getLevel() + 1;
        }else{
            this.level = 0;
        }
    }

    public TreeNode(String prod,String val, TreeNode parent, Boolean isEnd) {
        this.prod = prod;
        this.isEnd = isEnd;
        this.parent = parent;
        this.val = val;
        if(parent != null) {
            this.level = parent.getLevel() + 1;
        }else{
            this.level = 0;
        }
    }
    public void setToken(Token token) {
        this.token = token;
    }

    public TreeNode getSibling() {
        return sibling;
    }

    public void setSibling(TreeNode sibling) {
        this.sibling = sibling;
    }

    public TreeNode getParent() {
        return parent;
    }

    public void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public ArrayList<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<TreeNode> children) {
        this.children = children;
    }

    public void setChildren(String [] sarr) {


        children = new ArrayList<TreeNode>();

        for(int i = 0; i<sarr.length;i++){
            if(i==sarr.length-1) {
                children.add(nodeFactory(sarr[i],this,true));
            }else{
                children.add(nodeFactory(sarr[i],this, false));
            }
        }

        for(int i = 0; i<sarr.length-1;i++){
                children.get(i).setSibling(children.get(i+1));
        }
    }

    public TreeNode nodeFactory(String prod, TreeNode parent, Boolean isEnd){

//        if(prod.equals("prog")){
//            return new ProgNode(prod, parent, isEnd);
//        }
//
//        if(prod.equals("id")){
//            return new IdNode(prod, parent, isEnd);
//        }
//
//        if(prod.equals("typeId")){
//            return new VarDeclNode(prod, parent, isEnd);
//        }



//        if(prod.equals("classDecl")){
//            return new ClassDeclNode(prod, parent, isEnd);
//        }
//
//
//        if(prod.equals("funcDecl")){
//            return new FuncDeclNode(prod, parent, isEnd);
//        }
//
//        if(prod.equals("varDeclPre")){
//            return new ProgNode(prod, parent, isEnd);
//        }



        return new TreeNode(prod, parent, isEnd);
    }

    public TreeNode(Token token) {
        this.token = token;
    }

    public TreeNode(Token token, Boolean isEnd) {
        this.isEnd = isEnd;
        this.token = token;
    }

    public TreeNode(String prod, Boolean isEnd) {
        this.isEnd = isEnd;
        this.prod = prod;
    }

    public TreeNode(String prod) {
        this.prod = prod;
    }

//    public Main.TreeNode(String token, Boolean isEnd, Main.TreeNode parent) {
//        setParent(parent);
//        this.isEnd = isEnd;
//        this.token = token;
//        this.sibling = sibling;
//    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }


}