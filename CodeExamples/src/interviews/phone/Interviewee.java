package interviews.phone;

import java.util.List;

public class Interviewee {
	
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    public TreeNode cloneTree(TreeNode root)    {
    
        if(root == null)
            throw new NullPointerException();
        
        return copyNode(root);
    }
    
    private TreeNode copyNode(TreeNode node){
        // left or right nodes
        TreeNode copiedNode = new TreeNode();
        copiedNode.val = node.val;
        
        if(node.left != null)
            copiedNode.left = copyNode(node.left);
            
        if(node.right != null)
            copiedNode.right = copyNode(node.right);
        
        return copiedNode;
    }
	

}
