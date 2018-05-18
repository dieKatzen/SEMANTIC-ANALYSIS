package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class MultOpNodeAST extends TreeNode {
    public MultOpNodeAST(String token) {
        super(token);
    }

    public MultOpNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public MultOpNodeAST(String prod, TreeNode parent, Boolean isEnd) {
        super(prod, parent, isEnd);
    }

    public MultOpNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod,token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public MultOpNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }
}
