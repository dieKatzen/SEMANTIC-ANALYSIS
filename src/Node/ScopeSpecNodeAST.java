package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class ScopeSpecNodeAST extends TreeNode {
    public ScopeSpecNodeAST(String token) {
        super(token);
    }

    public ScopeSpecNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public ScopeSpecNodeAST(String prod, TreeNode parent, Boolean isEnd) {
        super(prod, parent, isEnd);
    }

    public ScopeSpecNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod,token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public ScopeSpecNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }
}
