package Node;

import Main.TreeNode;
import Main.Visitor;

public class FuncDeclNode extends TreeNode {
    public FuncDeclNode(String token) {
        super(token);
    }

    public FuncDeclNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public FuncDeclNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }


}
