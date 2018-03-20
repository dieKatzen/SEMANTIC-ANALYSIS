package Main;

import Lexer.Token;
import Node.*;
import jdk.internal.org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;

public class TreeNode{
    Token token;
    String prod;
    int level;

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
        return this.getClass().toString();
    }

    public void setProd(String prod) {
        this.prod = prod;
    }

    Boolean isBegin=false;
    Boolean isEnd=false;

    TreeNode sibling;
    TreeNode parent;
    ArrayList<TreeNode> children = null;

    public Token getToken() {
        return token;
    }

    public TreeNode(String prod, TreeNode parent, Boolean isEnd) {
        this.prod = prod;
        this.isEnd = isEnd;
        this.parent = parent;
        this.level = parent.getLevel()+1;
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


        if(prod.equals("id")){
            return new IdNode(prod, parent, isEnd);
        }

        if(prod.equals("classDecl")){
            return new ClassDeclNode(prod, parent, isEnd);
        }

        if(prod.equals("varDeclPre")){
            return new VarDeclNode(prod, parent, isEnd);
        }

        if(prod.equals("funcDecl")){
            return new FuncDeclNode(prod, parent, isEnd);
        }

        if(prod.equals("varDeclPre")){
            return new ProgNode(prod, parent, isEnd);
        }

        if(prod.equals("class")){
            return new ClassNode(prod, parent, isEnd);
        }



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

    public TreeNode(Token token, TreeNode parent, Boolean isEnd) {
        setParent(parent);
        this.isEnd = isEnd;
        this.token = token;
        this.level = parent.getLevel()+1;
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

}