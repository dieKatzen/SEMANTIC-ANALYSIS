package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class MembDeclNodeAST extends TreeNode {
    public MembDeclNodeAST(String token) {
        super(token);
    }

    public MembDeclNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public MembDeclNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public MembDeclNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }

    }

    public MembDeclNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }
}
