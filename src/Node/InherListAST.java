package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class InherListAST extends TreeNode {
    public InherListAST(String token) {
        super(token);
    }

    public InherListAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public InherListAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public InherListAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public InherListAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }
}
