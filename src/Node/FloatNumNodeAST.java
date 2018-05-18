package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class FloatNumNodeAST extends TreeNode {
    public FloatNumNodeAST(String token) {
        super(token);
    }

    public FloatNumNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public FloatNumNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public FloatNumNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public FloatNumNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
