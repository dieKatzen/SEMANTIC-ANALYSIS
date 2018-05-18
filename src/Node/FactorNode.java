package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class FactorNode extends TreeNode {
    public FactorNode(String token) {
        super(token);
    }

    public FactorNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public FactorNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }

    public FactorNode(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
