package com.hanyu.tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        // 创建一个二叉树
        BinaryTree tree = new BinaryTree();
        HeroNode node1 = new HeroNode(1,"宋江");
        HeroNode node2 = new HeroNode(2,"吴用");
        HeroNode node3 = new HeroNode(3,"卢俊义");
        HeroNode node4 = new HeroNode(4,"林冲");
        HeroNode node5 = new HeroNode(5,"关胜");
        // 说明，我们先手动创建二叉树，后面我们使用递归的方式创建二叉树
        tree.setRoot(node1);
        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        // 测试前序，中序，后序遍历
        System.out.println("前序遍历的结果：");
        // 1 2 3 5 4
        tree.preOrder();

        System.out.println("中序遍历的结果：");
        // 2 1 5 3 4
        tree.infixOrder();

        System.out.println("后序遍历的结果：");
        // 2 5 4 3 1
        tree.postOrder();

        System.out.println("=================");
        System.out.println("前序查找的结果：");
        System.out.println(tree.preSearch(6));

        System.out.println("中序查找的结果：");
        System.out.println(tree.infixSearch(6));

        System.out.println("后序序查找的结果：");
        System.out.println(tree.postSearch(6));

        System.out.println("============");
        System.out.println("删除节点");
        tree.delNode(3);
        tree.preOrder();


    }
}
// 第一二叉树
class BinaryTree{
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }
    // 前序遍历
    public void preOrder(){
        if (this.root!=null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }

    }
    // 中序遍历
    public void infixOrder(){
        if (this.root!=null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    // 后续遍历
    public void postOrder(){
        if (this.root!=null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
    // 前序查找
    public HeroNode preSearch(int id){
        if (this.root!=null){
            return this.root.preSearch(id);
        }else {
            System.out.println("二叉树为空，无法遍历");
            return null;
        }
    }

    // 中序查找
    public HeroNode infixSearch(int id){
        if (this.root!=null){
            return this.root.infixSearch(id);
        }else {
            System.out.println("二叉树为空，无法遍历");
            return null;
        }
    }

    // 后序查找
    public HeroNode postSearch(int id){
        if (this.root!=null){
            return this.root.postSearch(id);
        }else {
            System.out.println("二叉树为空，无法遍历");
            return null;
        }
    }

    public void delNode(int id){
        if (this.root!=null){
            // 判断root是不是要删除的节点
            if (root.getId()==id){
               // 左边上位
                if (root.getLeft()!=null){
                    HeroNode cur = root.getRight();
                    root = root.getLeft();
                    if (root.getLeft()!=null){
                        HeroNode temp = root.getRight();
                        root.setRight(cur);
                        root.getLeft().setRight(temp);
                    }else {
                        HeroNode right = root.getRight();
                        root.setRight(cur);
                        root.setLeft(right);
                    }

                }else {
                    root = root.getRight();
                }
            }else {
                this.root.delNode(id);
            }

        }else {
            System.out.println("二叉树为空，无法删除");
        }
    }



}

// 定义节点
class HeroNode{
    private int id;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
    // 前序遍历的方法
    public void preOrder(){
        // 先输出父节点
        System.out.println(this);
        // 递归向左子树遍历
        if (this.left!=null){
            this.left.preOrder();
        }
        // 递归向右子树遍历
        if (this.right!=null){
            this.right.preOrder();
        }
    }

    // 中序遍历的方法
    public void infixOrder(){
        // 递归向左子树遍历
        if (this.left!=null){
            this.left.infixOrder();
        }

        System.out.println(this);

        // 递归向右子树遍历
        if (this.right!=null){
            this.right.infixOrder();
        }
    }

    // 后序遍历的方法

    public void postOrder(){
        // 递归向左子树遍历
        if (this.left!=null){
            this.left.postOrder();
        }

        // 递归向右子树遍历
        if (this.right!=null){
            this.right.postOrder();
        }

        System.out.println(this);
    }

    public HeroNode preSearch(int id) {
        System.out.println("前序~");
        if (this.id==id){
            return this;
        }
        HeroNode resNode = null;
        if (this.left!=null){
            resNode = this.left.preSearch(id);
        }
        if (resNode!=null){
            return resNode;
        }
        if (this.right!=null){
            resNode = this.right.preSearch(id);
        }
        return resNode;

    }

    public HeroNode infixSearch(int id) {
        HeroNode resNode = null;
        if (this.left!=null){
            resNode = this.left.infixSearch(id);
        }
        if (resNode!=null){
            return resNode;
        }

        System.out.println("中序~");
        if (this.id==id){
            return this;
        }
        if (this.right!=null){
            resNode = this.right.infixSearch(id);
        }
        return resNode;
    }

    public HeroNode postSearch(int id) {

        HeroNode resNode = null;
        if (this.left!=null){
            resNode = this.left.postSearch(id);
        }
        if (resNode!=null){
            return resNode;
        }

        if (this.right!=null){
            resNode = this.right.postSearch(id);
        }
        if (resNode!=null){
            return resNode;
        }
        System.out.println("后序~");
        if (this.id==id){
            return this;
        }else {
            return resNode;
        }
    }

    public void delNode(int id) {
        if (this.left!=null&&this.left.id==id){
            if (this.left.getLeft()!=null){
                HeroNode cur = this.left.getLeft();
                HeroNode temp = this.left.getRight();
                if (cur.getLeft()!=null){
                    cur.getLeft().setRight(cur.getRight());
                    this.setLeft(temp);
                    return;
                }else {
                    cur.setLeft(cur.getRight());
                    return;
                }
            }else {
                this.left = this.left.getRight();
                return;
            }
        }
        if (this.right!=null&&this.right.id==id){
            if (this.right.getLeft()!=null){
                HeroNode cur = this.right.getLeft();
                HeroNode temp = this.right.getRight();
                if (cur.getLeft()!=null){
                    cur.getLeft().setRight(cur.getRight());
                    this.setLeft(temp);
                    return;
                }else {
                    cur.setLeft(temp);
                    this.setRight(cur);
                    return;
                }
            }else {
                this.left = this.left.getRight();
                return;
            }
        }
        // 递归向左删除
        if (this.left!=null){
            this.left.delNode(id);
        }
        // 递归向右删除
        if (this.right!=null){
            this.right.delNode(id);
        }
    }
}
