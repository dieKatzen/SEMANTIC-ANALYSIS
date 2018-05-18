package Node;

import Main.TreeNode;
import Visitor.Visitor;

public class AssignStatNode extends TreeNode {
    public AssignStatNode(String token) {
        super(token);
    }

    public AssignStatNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public AssignStatNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }


}
