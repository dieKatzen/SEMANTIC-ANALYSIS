package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Tree {

    public TreeNode getStartNode() {
        return startNode;
    }

    public void setStartNode(TreeNode startNode) {
        this.startNode = startNode;
        startNode.setLevel(0);
    }

    public Tree() {
    }

    TreeNode startNode;



}
