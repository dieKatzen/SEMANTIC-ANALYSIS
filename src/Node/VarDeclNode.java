package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class VarDeclNode extends TreeNode {
    public VarDeclNode(String token) {
        super(token);
    }

    public VarDeclNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public VarDeclNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }


    public VarDeclNode(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod,token, parent, isEnd);
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }

}
