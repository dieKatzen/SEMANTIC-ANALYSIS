package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class StatBlockNodeAST extends TreeNode {
    public StatBlockNodeAST(String token) {
        super(token);
    }

    public StatBlockNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public StatBlockNodeAST(String prod, TreeNode parent, Boolean isEnd) {
        super(prod, parent, isEnd);
    }

    public StatBlockNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod,token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public StatBlockNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }
}
