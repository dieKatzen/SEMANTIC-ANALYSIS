package Node;

import Main.TreeNode;
import Main.Visitor;

public class FParamsNode extends TreeNode {
    public FParamsNode(String token) {
        super(token);
    }

    public FParamsNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public FParamsNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }


}
