package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class MembListAST extends TreeNode {
    public MembListAST(String token) {
        super(token);
    }

    public MembListAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public MembListAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public MembListAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public MembListAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
