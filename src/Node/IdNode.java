package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

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

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public IdNode(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
