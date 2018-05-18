package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class FuncDefListNodeAST extends TreeNode {
    public FuncDefListNodeAST(String token) {
        super(token);
    }

    public FuncDefListNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public FuncDefListNodeAST(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public FuncDefListNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())

                child.accept(visitor);
        }
    }

    public FuncDefListNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }
}
