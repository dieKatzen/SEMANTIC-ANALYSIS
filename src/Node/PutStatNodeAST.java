package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class PutStatNodeAST extends TreeNode {
    public PutStatNodeAST(String token) {
        super(token);
    }

    public PutStatNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public PutStatNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public PutStatNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public PutStatNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
