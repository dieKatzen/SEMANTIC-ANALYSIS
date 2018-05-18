package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class RelOpNodeAST extends TreeNode {
    public RelOpNodeAST(String token) {
        super(token);
    }

    public RelOpNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public RelOpNodeAST(String prod, TreeNode parent, Boolean isEnd) {
        super(prod, parent, isEnd);
    }

    public RelOpNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod,token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public RelOpNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }
}
