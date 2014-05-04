package lab3;

/**
 * This is an example collection that we built to demonstrate collections and hashing.
 * 
 * @author Blake Rollins
 * 
 */
public class HashTree implements MixedSet {

    private HashTreeNode rootNode;

    /**
     * Returns the HashTree's root node
     * 
     * @return THe HashTree's root node
     */
    public HashTreeNode getRootNode() {
        return this.rootNode;
    }

    /**
     * Sets the HashTree's default or root node
     * 
     * @param newRoot The node that will be the HashTree's root node
     */
    public void setRootNode(HashTreeNode newRoot) {
        this.rootNode = newRoot;
    }

    @Override
    /**
     * Adds an item to the HashTree according to the item's hash value
     * @param item  The String to add to the HashTree
     * @return If the item was added to the HashTree
     */
    public boolean add(String item) {

        boolean returnval = false;
        // If the root node is null, make this item the root node
        if (this.rootNode == null) {
            this.rootNode = new HashTreeNode(item);
            returnval = true;
        } else {
            // If the root node is not null, figure out where to add the item
            HashTreeNode n = null;
            n = traverseTree(this.rootNode, hash(item));

            // if the response from traverse tree is null, there was a collision so do not add the item and return false
            if (n == null) {
                returnval = false;
            } else {
                // Else add the item
                n.setValue(item);
                returnval = true;
            }
        }
        return returnval;
    }

    @Override
    /**
     * Get the hash code of the String
     * @param item  The String to create a hash code from
     * @return The hash code of the item
     */
    public int hash(String item) {
        int result = 0;

        // If null, the result is 0;
        if (item == null) {
            result = 0;
        } else {

            // For each character in the string:
            for (int i = 0; i < item.length(); i++) {
                char c = item.charAt(i);
                // If upper case, add 10 to the result
                if (c >= 'A' && c <= 'Z') {
                    result += 10;
                    // if lower case add the int value of char to the result
                } else if (c >= 'a' && c <= 'z') {
                    result += (int) c;
                    // else add 1
                } else {
                    result += 1;
                }
            }
        }
        return result;
    }

    @Override
    /**
     * recursively traverses the tree and creates a new node at the location the hash code should exist.  then returns the node
     * @param node The starting node of the search
     * @param hashCode The code to find a location for
     * @return either an empty node for the new hashCode or null if there was a collision
     */
    public HashTreeNode traverseTree(HashTreeNode node, int hashCode) {
        // Get the has of the current node
        int nodeHash = hash(node.getValue());
        if (nodeHash == hashCode) {
            // if the hashes are equal then return null to indicate a collision
            return null;
        } else {
            HashTreeNode n = null;
            // if the new hash is less than this node, go left
            if (hashCode < nodeHash) {
                if (node.getLeft() != null) {
                    // if not null, search left
                    return traverseTree(node.getLeft(), hashCode);
                } else {
                    // if null, create a new node and return
                    node.setLeft(new HashTreeNode("" + hashCode));
                    return node.getLeft();
                }
                // if the new has is greater than this node, go right
            } else if (hashCode > nodeHash) {
                // if not null, search right
                if (node.getRight() != null) {
                    return traverseTree(node.getRight(), hashCode);
                } else {
                    // if null, create a new node and return
                    node.setRight(new HashTreeNode("" + hashCode));
                    return node.getRight();
                }
            }
            if (n == null) {

            }
        }
        return null;
    }

    @Override
    /**
     * Displays the tree from the specified node
     * @param node The starting point to display from
     */
    public void displayTree(HashTreeNode node) {
        
        // Print left recursively 
        if (node.getLeft() != null) {
            displayTree(node.getLeft());
        }
        
        // Print self
        System.out.println(node.getValue());
        
        // Print right recursively
        if (node.getRight() != null) {
            displayTree(node.getRight());
        }
    }

}
