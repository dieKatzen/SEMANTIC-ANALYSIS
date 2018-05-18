package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class InherListNodeAST extends TreeNode {
    public InherListNodeAST(String token) {
        super(token);
    }

    public InherListNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public InherListNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public InherListNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public InherListNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }
}
