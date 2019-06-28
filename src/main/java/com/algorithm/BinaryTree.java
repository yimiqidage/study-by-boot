package com.algorithm;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 二叉树遍历
 * @author weishi8
 * @create 2019-05-28
 * @description
 */
public class BinaryTree {
    public static int count = 0;
    private static class TreeNode{
        int data;
        TreeNode leftNode;
        TreeNode rightNode;
        public TreeNode(int data){
            this.data=data;
        }
    }

    /**
     * 加载所有节点
     * @param list
     * @return
     */
    public static TreeNode createTree(LinkedList<Integer> list){
        if(list!=null && list.size()>0){
            System.out.println("第【"+count+"】次，开始循环:"+list.get(0));
            count++;
        }
        TreeNode node = null;
        if(list ==null || list.size()==0){
            return null;
        }
        Integer data = list.removeFirst();
        if(data != null){
            node = new TreeNode(data);
            node.leftNode = createTree(list);
            node.rightNode = createTree(list);
        }

        return node;
    }

    /**
     * 前序遍历：根节点->左子树->右子树
     * @param node
     */
    public static void preTraveral(TreeNode node){

        if(node == null){
            return ;
        }
        System.out.println(node.data);
        preTraveral(node.leftNode);
        preTraveral(node.rightNode);
    }

    public static void main(String[] args) {
        LinkedList<Integer>list = new LinkedList<>(Arrays.asList(new Integer[]{3,2,9,null,null,10,null,null,8,null,4}));
        TreeNode node = createTree(list);
        System.out.println("前序遍历：");
        preTraveral(node);
    }
}
