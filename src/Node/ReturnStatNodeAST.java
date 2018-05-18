package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class ReturnStatNodeAST extends TreeNode {
    public ReturnStatNodeAST(String token) {
        super(token);
    }

    public ReturnStatNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public ReturnStatNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public ReturnStatNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public ReturnStatNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
