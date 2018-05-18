package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class FunctionCallNode extends TreeNode {
    public FunctionCallNode(String token) {
        super(token);
    }

    public FunctionCallNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public FunctionCallNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public FunctionCallNode(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }


}
