package Node;

import Main.TreeNode;
import Main.Visitor;

public class StatementNode extends TreeNode {
    public StatementNode(String token) {
        super(token);
    }

    public StatementNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public StatementNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }


}
