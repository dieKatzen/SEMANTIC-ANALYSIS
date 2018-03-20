package Node;

import Main.TreeNode;
import Main.Visitor;

public class IfNode extends TreeNode {
    public IfNode(String token) {
        super(token);
    }

    public IfNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public IfNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }
    
}
