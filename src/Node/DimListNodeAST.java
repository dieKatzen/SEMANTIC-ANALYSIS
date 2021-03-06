package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class DimListNodeAST extends TreeNode {
    public DimListNodeAST(String token) {
        super(token);
    }

    public DimListNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public DimListNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public DimListNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public DimListNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
