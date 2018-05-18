package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class VarDeclNodeAST extends TreeNode {
    public VarDeclNodeAST(String token) {
        super(token);
    }

    public VarDeclNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public VarDeclNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public VarDeclNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod,token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public VarDeclNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }
}
