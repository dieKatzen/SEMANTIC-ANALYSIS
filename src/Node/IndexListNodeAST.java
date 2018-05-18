package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class IndexListNodeAST extends TreeNode {
    public IndexListNodeAST(String token) {
        super(token);
    }

    public IndexListNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public IndexListNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public IndexListNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public IndexListNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
