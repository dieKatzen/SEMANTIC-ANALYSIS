package Node;

import Main.TreeNode;
import Main.Visitor;

public class VariableNode extends TreeNode {
    public VariableNode(String token) {
        super(token);
    }

    public VariableNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public VariableNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }

}
