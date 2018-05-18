package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class StatementNode extends TreeNode {
    public StatementNode(String token) {
        super(token);
    }

    public StatementNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public StatementNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }
    public StatementNode(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod,token, parent, isEnd);
    }

}
