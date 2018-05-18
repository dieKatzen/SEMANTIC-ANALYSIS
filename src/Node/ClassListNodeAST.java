package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class ClassListNodeAST extends TreeNode {
    public ClassListNodeAST(String token) {
        super(token);
    }

    public ClassListNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public ClassListNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public ClassListNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public ClassListNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
