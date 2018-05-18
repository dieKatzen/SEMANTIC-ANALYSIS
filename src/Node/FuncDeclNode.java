package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class FuncDeclNode extends TreeNode {
    public FuncDeclNode(String token) {
        super(token);
    }

    public FuncDeclNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public FuncDeclNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public FuncDeclNode(String prod, Token token, TreeNode parent, Boolean isEnd) {
        super(prod, token, parent, isEnd);
    }
}
