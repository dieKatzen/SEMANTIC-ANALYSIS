package Node;

import Main.TreeNode;
import Main.Visitor;

public class StatBlockNode extends TreeNode {
    public StatBlockNode(String token) {
        super(token);
    }

    public StatBlockNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public StatBlockNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }


}
