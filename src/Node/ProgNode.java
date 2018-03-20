package Node;

import Main.TreeNode;
import Main.Visitor;

public class ProgNode extends TreeNode {
    public ProgNode(String token) {
        super(token);
    }

    public ProgNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public ProgNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }

}
