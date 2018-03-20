package Node;

import Main.TreeNode;
import Main.Visitor;

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

    public static class IdNode extends TreeNode{
        public IdNode(String token) {
            super(token);
        }

        public IdNode(String token, Boolean isEnd) {
            super(token, isEnd);
        }

        public IdNode(String token, TreeNode parent, Boolean isEnd) {
            super(token, parent, isEnd);
        }

        public void accept(Visitor visitor){
            visitor.visit(this);
        }
    }
}
