package Node;

import Main.TreeNode;
import Main.Visitor;

public class VarDeclNode extends TreeNode {
    public VarDeclNode(String token) {
        super(token);
    }

    public VarDeclNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public VarDeclNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }

}
