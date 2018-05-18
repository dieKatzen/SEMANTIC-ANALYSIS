package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class FuncDefNodeAST extends TreeNode {
    public FuncDefNodeAST(String token) {
        super(token);
    }

    public FuncDefNodeAST(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public FuncDefNodeAST(String prod, TreeNode parent, Boolean isEnd) {
        super(prod, parent, isEnd);
    }

    public FuncDefNodeAST(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod,token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

    public FuncDefNodeAST(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }
}
