package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class DataMemberNodeAST extends TreeNode {
    public DataMemberNodeAST(String token) {
        super(token);
    }

    public DataMemberNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public DataMemberNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public DataMemberNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public DataMemberNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
