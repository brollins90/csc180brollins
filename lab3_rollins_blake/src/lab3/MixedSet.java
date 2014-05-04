package lab3;

public interface MixedSet
{
	abstract boolean add(String item);
	abstract int hash(String item);
	abstract HashTreeNode traverseTree(HashTreeNode node, int hashCode);
	abstract void displayTree(HashTreeNode node);
}
