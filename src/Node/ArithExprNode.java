package Node;

import Main.TreeNode;
import Main.Visitor;

public class ArithExprNode extends TreeNode {
    public ArithExprNode(String token) {
        super(token);
    }

    public ArithExprNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public ArithExprNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }


}
