package Node;

import Main.TreeNode;
import Main.Visitor;

public class ClassNode extends TreeNode {
    public ClassNode(String token) {
        super(token);
    }

    public ClassNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public ClassNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }
    
}
