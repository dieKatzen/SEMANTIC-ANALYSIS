package Node;

import Main.TreeNode;
import Visitor.Visitor;

public class ExprNode extends TreeNode {
    public ExprNode(String token) {
        super(token);
    }

    public ExprNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public ExprNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }


}
