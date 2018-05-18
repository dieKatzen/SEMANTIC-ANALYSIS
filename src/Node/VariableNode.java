package Node;

import Lexer.Token;
import Main.TreeNode;
import Visitor.Visitor;

public class VariableNode extends TreeNode {
    public VariableNode(String token) {
        super(token);
    }

    public VariableNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public VariableNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }


   public VariableNode(String prod, Token token , TreeNode parent, Boolean isEnd) {
       super(prod, token, parent, isEnd);
   }
}
