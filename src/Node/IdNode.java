package Node;

import Main.TreeNode;
import Main.Visitor;

public class IdNode extends TreeNode {
    public IdNode(String token) {
        super(token);
    }

    public IdNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public IdNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }

}
