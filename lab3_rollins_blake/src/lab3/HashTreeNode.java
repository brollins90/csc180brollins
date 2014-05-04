package lab3;

/**
 * A node object for the HashTree.
 * @author Blake Rollins
 *
 */
public class HashTreeNode {

    private HashTreeNode leftNode, rightNode;
    private String value;

    public HashTreeNode() {

    }

    public HashTreeNode(String value) {
        this.value = value;
    }

    public HashTreeNode getLeft() {
        return this.leftNode;
    }

    public HashTreeNode getRight() {
        return this.rightNode;
    }

    public String getValue() {
        return this.value;
    }

    public void setLeft(HashTreeNode newLeft) {
        this.leftNode = newLeft;
    }

    public void setRight(HashTreeNode newRight) {
        this.rightNode = newRight;
    }

    public void setValue(String newValue) {
        this.value = newValue;
    }
}
