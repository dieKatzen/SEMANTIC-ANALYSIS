package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class SignNodeAST extends TreeNode {
    public SignNodeAST(String token) {
        super(token);
    }

    public SignNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public SignNodeAST(String prod , TreeNode parent, Boolean isEnd) {
        super(prod, parent, isEnd);
    }

    public SignNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public SignNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod,token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

}
