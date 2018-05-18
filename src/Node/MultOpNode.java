package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class MultOpNode extends TreeNode {
    public MultOpNode(String token) {
        super(token);
    }

    public MultOpNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public MultOpNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }

    public MultOpNode(String prod, String val, TreeNode parent, Boolean isEnd) {
        super(prod, val, parent, isEnd);
    }

    public MultOpNode(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
