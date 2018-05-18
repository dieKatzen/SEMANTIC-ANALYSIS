package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class FuncBodyNode extends TreeNode {
    public FuncBodyNode(String token) {
        super(token);
    }

    public FuncBodyNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public FuncBodyNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }


    public FuncBodyNode(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
