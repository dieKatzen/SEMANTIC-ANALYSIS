package Node;

import Main.TreeNode;
import Visitor.Visitor;

public class AddOpNode extends TreeNode {
    public AddOpNode(String token) {
        super(token);
    }

    public AddOpNode(String token, Boolean isEnd) {
        super(token, isEnd);
    }

    public AddOpNode(String token, TreeNode parent, Boolean isEnd) {
        super(token, parent, isEnd);
    }

    public void accept(Visitor visitor){
        visitor.visit(this);
    }

    public static void manipulate(Integer i){
        i = i*2;
    }
    public static void manipulate(TestO i){
        i.i = i.i*2;
    }

    public static void main(String [] args) {
        Integer inti = 5;
        TestO into = new TestO (5);
        manipulate(into);
        manipulate(inti);
        System.out.println(into.i);
    }

    public static class TestO{
        int i;

        public TestO(int i) {
            this.i = i;
        }
    }

}
