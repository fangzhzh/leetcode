/*
 * @lc app=leetcode id=297 lang=java
 *
 * [297] Serialize and Deserialize Binary Tree
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

/**
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Clarification: The input/output format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
  */ 


/**
 * This is an iterative way, too many edge case to handle
 *  */  
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<String> list = new ArrayList<>();
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node == null) {
                list.add("null,");
                continue;
            } else {
                list.add(node.val+",");
            }
            queue.offer(node.left);
            queue.offer(node.right);
        }
        int i = list.size()-1;
        for(; i >= 0; i--) {
            if(list.get(i).compareTo("null,") != 0) {
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j <= i; j++) {
            sb.append(list.get(j));
        }
        String ans =sb.toString();
        return ans;
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data == null || data.length() == 0) {
            return null;
        }
        String[] strNodes = data.split(",");
        Queue<TreeNode> queue = new LinkedList<>();
        if(strNodes.length == 0) {
            return null;
        }
        TreeNode root = makeNode(strNodes[0]);
        queue.offer(root);
        int idx = 1;
        while(!queue.isEmpty() && idx < strNodes.length) {
            TreeNode node = queue.poll();
            if(node == null) {
                continue;
            }
            node.left = makeNode(strNodes[idx]);
            queue.offer(node.left);
            idx++;
            if(idx < strNodes.length) {
                node.right = makeNode(strNodes[idx]);
                queue.offer(node.right);
                idx++;
            }
        }
        return root;
        
    }
    TreeNode makeNode(String val) {        TreeNode root = null;
        if(val.compareTo("null") != 0 && val.compareTo("") != 0) {
            root = new TreeNode();
            root.val = Integer.valueOf(val);
        }
        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec ser = new Codec();
// Codec deser = new Codec();
// TreeNode ans = deser.deserialize(ser.serialize(root));
// @lc code=end


/**
 * meta recursive:
 * core issue is find the root, then build left and right 
 */

/**
 * Pre order
 */

public class Codec {
    private static final String NULL = "X";


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
       StringBuilder sb = new StringBuilder();
       buildString(root, sb);
       return sb.toString();
    }
    private void buildString(TreeNode node, StringBuilder sb) {
        if(node == null) {
            sb.append(NULL).append(",");
        } else {
            sb.append(node.val).append(",");
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        // convert array to queue, 
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(nodes));
        return buidlTree(queue);
    }
    TreeNode buidlTree(Queue<String> queue) {
        String val = queue.poll();
        if(val.equals(NULL)) {
            return null;
        } 
        TreeNode node = new TreeNode(Integer.valueOf(val));
        node.left = buidlTree(queue);
        node.right = buidlTree(queue);
        return node;
    }
}


/**
 * in order is not able to deserialise
 */
 public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append("#").append(",");
            return;
        }
        serialize(root.left, sb);
        sb.append(root.val).append(",");
        serialize(root.right, sb);
    }

    // Decodes your encoded data to tree.
    /** 
     * No doable for in order

     */
    public TreeNode deserialize(String data) {

    }

}


/**
 * post order, find the root and build left and right.
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    private void serialize(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append("#").append(",");
            return;
        }
        serialize(root.left, sb);
        serialize(root.right, sb);
        sb.append(root.val).append(",");
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] tree = data.split(",");
        Stack<String> stack = new Stack<> ();
        stack.addAll(Arrays.asList(tree));
        return buildTree(stack);
    }

    private TreeNode buildTree(Stack<String> stack) {
        if(stack.isEmpty()) {
            return null;
        }
        String nodeValue = stack.pop();
        if(nodeValue.equals("#")) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.valueOf(nodeValue));
        node.right = buildTree(stack);
        node.left = buildTree(stack);
        return node;
    }
}


/**
 * level traversal
 *
 */
 public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode node;
        while(!queue.isEmpty()) {
            node = queue.poll();
            if(node == null) {
                sb.append("#").append(",");
            } else {
                sb.append(node.val).append(",");
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }
        return sb.toString();
    }

   
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if(data.length() == 0) return null;
        String[]tree = data.split(",");
        if(tree[0].equals("#")) return null;
        Queue<TreeNode> queue = new LinkedList<> ();
        TreeNode root = new TreeNode(Integer.valueOf(tree[0]));
        queue.offer(root);
        for(int i = 1; i < tree.length; i++) {
            TreeNode node = queue.poll();
            String c = tree[i];
            if(c.equals("#")) {
                node.left = null;
            } else {
                node.left = new TreeNode(Integer.valueOf(c));
                queue.offer(node.left);
            }

            i++;
            c = tree[i];
            if(c.equals("#")) {
                node.right = null;
            } else {
                node.right = new TreeNode(Integer.valueOf(c));
                queue.offer(node.right);
            }
        }
        return  root;
    }
}