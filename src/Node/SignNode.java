package Node;

import Main.TreeNode;
import Visitor.Visitor;

public class SignNode extends TreeNode {
    public SignNode(String token) {
        super(token);
    }

    public SignNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public SignNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }


}
