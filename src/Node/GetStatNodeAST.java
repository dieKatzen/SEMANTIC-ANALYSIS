package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class GetStatNodeAST extends TreeNode {
    public GetStatNodeAST(String token) {
        super(token);
    }

    public GetStatNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public GetStatNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public GetStatNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public GetStatNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
