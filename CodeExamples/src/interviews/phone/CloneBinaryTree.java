package interviews.phone;

/**
 * Given a binary tree, do a mirror clone.
 * You cannot change the original tree
 * Return is the root of the cloned tree.
 */

public class CloneBinaryTree {
	
	static class TreeNode{
	    int val;
	    TreeNode left;
	    TreeNode right;
	}

	public TreeNode mirrorClone(TreeNode root)	{
		TreeNode newRoot = cloneNodeRecursive(root);
		//TreeNode newRoot = cloneNodeNonRecursive(root);
		
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
	
	private TreeNode createTree1()	{
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
	
	private void inOrderTraversal(TreeNode node)	{
		if(node.left != null)	{
			inOrderTraversal(node.left);
		}
		
		write(node.val);
		
		if(node.right != null)	{
			inOrderTraversal(node.right);
		}
	}

	private void preOrderTraversal(TreeNode node)	{
		write(node.val);
		
		if(node.left != null)	{
			inOrderTraversal(node.left);
		}
		
		if(node.right != null)	{
			inOrderTraversal(node.right);
		}
	}

	private void postOrderTraversal(TreeNode node)	{
		if(node.left != null)	{
			inOrderTraversal(node.left);
		}
		
		if(node.right != null)	{
			inOrderTraversal(node.right);
		}

		write(node.val);		
	}

	private void write(int in)	{
		System.out.println(in);
	}
	
	// This method doesn't work.
	private void inOrderNonRecursiveTraversal(TreeNode root) {
		TreeNode currNode = root;
		TreeNode pre;
		while(currNode != null)	{
			if(currNode.left == null)	{
				write(currNode.val);
				currNode = currNode.right;
			}
			else	{
				pre = currNode.left;
				while(pre.right != null && pre.right != currNode)	{
					pre = pre.right;
					
					if(pre.right == null)	{
						pre.right = currNode;
						currNode = currNode.left;
					}
					else	{
						pre.right = null;
						write(currNode.val);
						currNode = currNode.right;
					}
				}
			}
		}
	}



	public static void main(String[] args) {
		CloneBinaryTree cbt = new CloneBinaryTree();
		TreeNode node = cbt.createTree1();
		
		System.out.println("Printing inorder traversal of original:");
		cbt.inOrderTraversal(node);
		
		TreeNode newNode = cbt.cloneNodeRecursive(node);

		System.out.println("Printing inorder traversal of copy:");
		cbt.inOrderTraversal(newNode);

		System.out.println("Printing inorder non-recursive traversal of copy:");
		cbt.inOrderNonRecursiveTraversal(newNode);
	}
	
}
