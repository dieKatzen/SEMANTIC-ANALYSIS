package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class VarNodeAST extends TreeNode {
    public VarNodeAST(String token) {
        super(token);
    }

    public VarNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public VarNodeAST(String prod, TreeNode parent, Boolean isEnd) {
        super(prod, parent, isEnd);
    }

    public VarNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod,token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public VarNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }
}
