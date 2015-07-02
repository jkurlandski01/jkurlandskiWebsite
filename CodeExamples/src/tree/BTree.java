package tree;

import static org.junit.Assert.*;

import org.junit.Test;

public class BTree extends TreeNode {
	static class TreeNode{
	    int val;
	    TreeNode left;
	    TreeNode right;
	}

	public TreeNode cloneTree(TreeNode root)	{
		TreeNode newRoot = cloneNodeRecursive(root);
		
		return newRoot;		
	}

	/**
	 * 
	 * @param root
	 * @param newRoot
	 */
	private TreeNode cloneNodeRecursive(TreeNode oldNode) {
		TreeNode newNode = new TreeNode();
		newNode.val = oldNode.val;

		if(oldNode.left != null)	{
			newNode.left = cloneNodeRecursive(oldNode.left);
		}
		
		if(oldNode.right != null)	{
			newNode.right = cloneNodeRecursive(oldNode.right);
		}
		
		return newNode;
	}
	

	TreeNode merge(TreeNode tree1, TreeNode tree2)    {

	    // Check preconditions.
		
	    readRoot(tree1, tree2);
	    return tree1;

	}

	public void readRoot(TreeNode tree1, TreeNode tree2) {

	    // read the root value

	    int val = tree2.val;

	    if(tree2.left != null)    {

	        readRoot(tree1, tree2.left);

	    }

	    if(tree2.right != null)    {

	    	readRoot(tree1, tree2.right);

	    }

	    insert(tree1, val);

	}
	
	public void insert(TreeNode tree, int val)    {
		if (tree.val == val)	{
			return;
		}

	    TreeNode newNode = new TreeNode();
	    newNode.val = val;

	    if(tree.left == null && val < tree.val)	{
	        tree.left = newNode;
	        return;
	    }
	    else if (tree.right == null && val > tree.val)	{
	        tree.right = newNode;
	        return;
	    }

	    if (val < tree.val)    {
	        insert(tree.left, val);
	        return;
	    }

	    insert(tree.right, val);
	}
	
	@Test
	public void testInsert0()	{
		// Creates a tree whose val is 0.
		TreeNode actualTree = new TreeNode();
		assertEquals(0, actualTree.val);
		assertNull(actualTree.left);
		assertNull(actualTree.right);
	}
	
	@Test
	public void testInsert1()	{
		// Creates a tree whose val is 0, then inserts 5.
		TreeNode actualTree = new TreeNode();
		insert(actualTree, 5);
		
		assertEquals(0, actualTree.val);
		assertNull(actualTree.left);
		assertEquals(5, actualTree.right.val);
		assertNull(actualTree.right.left);
		assertNull(actualTree.right.right);
	}
	
	@Test
	public void testInsert2()	{
		TreeNode actualTree = new TreeNode();
		actualTree.val = 5;
		insert(actualTree, 3);
		
		assertEquals(5, actualTree.val);
		assertNull(actualTree.right);
		assertEquals(3, actualTree.left.val);
		assertNull(actualTree.left.left);
		assertNull(actualTree.left.left);
	}
	
	@Test
	public void testInsert3()	{
		TreeNode actualTree = new TreeNode();
		actualTree.val = 5;
		insert(actualTree, 3);
		insert(actualTree, 4);
		
		assertEquals(5, actualTree.val);
		assertEquals(3, actualTree.left.val);
		assertEquals(4, actualTree.left.right.val);
	}
}
