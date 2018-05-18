package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class FCallNodeAST extends TreeNode {
    public FCallNodeAST(String token) {
        super(token);
    }

    public FCallNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public FCallNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public FCallNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod,token, parent, isEnd);
    }

    public FCallNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

}
