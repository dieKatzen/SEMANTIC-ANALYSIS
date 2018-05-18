package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class ForStatNodeAST extends TreeNode {
    public ForStatNodeAST(String token) {
        super(token);
    }

    public ForStatNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public ForStatNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public ForStatNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public ForStatNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
