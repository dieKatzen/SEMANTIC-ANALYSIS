package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class FuncHeadNode extends TreeNode {
    public FuncHeadNode(String token) {
        super(token);
    }

    public FuncHeadNode(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }

    public FuncHeadNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public FuncHeadNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }


}
