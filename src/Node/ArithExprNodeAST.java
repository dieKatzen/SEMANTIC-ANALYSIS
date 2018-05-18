package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class ArithExprNodeAST extends TreeNode {
    public ArithExprNodeAST(String token) {
        super(token);
    }

    public ArithExprNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public ArithExprNodeAST(String prod, TreeNode parent, Boolean isEnd) {
        super(prod, parent, isEnd);
    }

    public ArithExprNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod,token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public ArithExprNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }
}
