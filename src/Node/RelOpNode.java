package Node;

import Main.TreeNode;
import Main.Visitor;

public class RelOpNode extends TreeNode {
    public RelOpNode(String token) {
        super(token);
    }

    public RelOpNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public RelOpNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }

}
