package tree;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.junit.Test;

import com.google.common.collect.Lists;


public class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	public static List<TreeNode> getInOrderTraversal(TreeNode node)		{
		List<TreeNode> returnTree = Lists.newArrayList();

		if (node.left != null)	{
			returnTree.addAll(getInOrderTraversal(node.left));
		}

		returnTree.add(node);

		if (node.right != null)	{
			returnTree.addAll(getInOrderTraversal(node.right));
		}

		return returnTree;
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
	public static TreeNode cloneNodeRecursive(TreeNode oldNode) {
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
	
	// Manually create a tree with hard-coded values.
	private TreeNode createTree()	{
		TreeNode node2 = new TreeNode();
		node2.val = 2;
		
		TreeNode parent7 = new TreeNode();
		parent7.val = 7;
		parent7.left = node2;
		
		TreeNode newNode5 = new TreeNode();
		newNode5.val = 5;
		TreeNode parent6 = new TreeNode();
		parent6.val = 6;
		parent6.left = newNode5;
		TreeNode newNode11 = new TreeNode();
		newNode11.val = 11;
		parent6.right = newNode11;
		
		parent7.right = parent6;
		
		TreeNode node4 = new TreeNode();
		node4.val = 4;
		
		TreeNode parent9 = new TreeNode();
		parent9.val = 9;
		parent9.left = node4;
		
		TreeNode parent5 = new TreeNode();
		parent5.val = 5;
		parent5.right = parent9;
		
		TreeNode root = new TreeNode();
		root.val = 2;
		root.left = parent7;
		root.right = parent5;
		
		return root;
	}
	
	@Test
	public void testInOrderTraversal()	{
		TreeNode node = createTree();
		List<TreeNode> nodes = getInOrderTraversal(node);
		
		List<Integer> vals = Lists.newArrayList();
		for (TreeNode element : nodes)	{
			vals.add(element.val);
		}
		
		int[] array = new int[]{2, 7, 5, 6, 11, 2, 5, 4, 9};
		List<Integer> expected = Arrays.asList(ArrayUtils.toObject(array));
		
		assertEquals(expected, vals);
	}

	@Test
	public void testCloneTree()	{
		TreeNode originalTree = createTree();
		TreeNode clonedTree = cloneNodeRecursive(originalTree);
		
		List<TreeNode> originalNodes = getInOrderTraversal(originalTree);
		List<Integer> originalVals = Lists.newArrayList();
		for (TreeNode element : originalNodes)	{
			originalVals.add(element.val);
		}
		
		List<TreeNode> clonedNodes = getInOrderTraversal(clonedTree);
		List<Integer> clonedVals = Lists.newArrayList();
		for (TreeNode element : clonedNodes)	{
			clonedVals.add(element.val);
		}
		
		assertEquals(clonedVals, originalVals);
	}

}
