package Node;

import Main.TreeNode;
import Main.Visitor;

public class FunctionCallNode extends TreeNode {
    public FunctionCallNode(String token) {
        super(token);
    }

    public FunctionCallNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public FunctionCallNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }


}
