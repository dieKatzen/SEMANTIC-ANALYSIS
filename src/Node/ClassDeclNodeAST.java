package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class ClassDeclNodeAST extends TreeNode {
    public ClassDeclNodeAST(String token) {
        super(token);
    }

    public ClassDeclNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public ClassDeclNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public ClassDeclNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public ClassDeclNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
