package Node;

import Main.TreeNode;
import Visitor.Visitor;

public class AssignOpNode extends TreeNode {
    public AssignOpNode(String token) {
        super(token);
    }

    public AssignOpNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public AssignOpNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }




}
