package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class IfStatNodeAST extends TreeNode {
    public IfStatNodeAST(String token) {
        super(token);
    }

    public IfStatNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public IfStatNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public IfStatNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public IfStatNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
