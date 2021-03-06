package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class RelExprNodeAST extends TreeNode {
    public RelExprNodeAST(String token) {
        super(token);
    }

    public RelExprNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public RelExprNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public RelExprNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public RelExprNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
