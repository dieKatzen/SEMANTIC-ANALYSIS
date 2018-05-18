package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class AssignStatNodeAST extends TreeNode {
    public AssignStatNodeAST(String token) {
        super(token);
    }

    public AssignStatNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public AssignStatNodeAST(String prod, TreeNode parent, Boolean isEnd) {
        super(prod, parent, isEnd);
    }

    public AssignStatNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod,token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public AssignStatNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }
}
