package Node;

import Main.TreeNode;
import Main.Visitor;

public class MultOpNode extends TreeNode {
    public MultOpNode(String token) {
        super(token);
    }

    public MultOpNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public MultOpNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }

}
