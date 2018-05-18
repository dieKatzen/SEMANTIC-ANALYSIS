package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class NotNodeAST extends TreeNode {
    public NotNodeAST(String token) {
        super(token);
    }

    public NotNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public NotNodeAST(String prod , TreeNode parent, Boolean isEnd) {
        super(prod, parent, isEnd);
    }

    public NotNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public NotNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
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
