package Node;

import Main.TreeNode;
import Main.Visitor;

public class FuncHeadNode extends TreeNode {
    public FuncHeadNode(String token) {
        super(token);
    }

    public FuncHeadNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public FuncHeadNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }


}
