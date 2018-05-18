package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class IntNumNodeAST extends TreeNode {
    public IntNumNodeAST(String token) {
        super(token);
    }

    public IntNumNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public IntNumNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public IntNumNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public IntNumNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
