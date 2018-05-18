package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class JustIDNodeAST extends TreeNode {
    public JustIDNodeAST(String token) {
        super(token);
    }

    public JustIDNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public JustIDNodeAST(String prod , TreeNode parent, Boolean isEnd) {
        super(prod, parent, isEnd);
    }

    public JustIDNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public JustIDNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
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
