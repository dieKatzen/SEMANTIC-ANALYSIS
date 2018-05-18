package Node;

import Main.TreeNode;
import Visitor.Visitor;

public class ClassDeclNode extends TreeNode {
    public ClassDeclNode(String token) {
        super(token);
    }

    public ClassDeclNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public ClassDeclNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }


    public void accept(Visitor visitor) {

        visitor.visit(this);
        if(this.getChildren()!=null) {
            for (TreeNode child : this.getChildren())
                child.accept(visitor);
        }
    }
}
