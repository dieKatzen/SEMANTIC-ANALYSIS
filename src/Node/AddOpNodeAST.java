package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class AddOpNodeAST extends TreeNode {
    public AddOpNodeAST(String token) {
        super(token);
    }

    public AddOpNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public AddOpNodeAST(String prod, TreeNode parent, Boolean isEnd) {
        super(prod, parent, isEnd);
    }

    public AddOpNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod,token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public AddOpNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }
}
