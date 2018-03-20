package Node;

import Main.TreeNode;
import Main.Visitor;

public class AddOpNode extends TreeNode {
    public AddOpNode(String token) {
        super(token);
    }

    public AddOpNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public AddOpNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }

}
