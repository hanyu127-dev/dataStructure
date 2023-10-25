package com.hanyu.tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        // 测试中序线索二叉树的功能

        HeroNode root = new HeroNode(1,"tom");
        HeroNode node2 = new HeroNode(3,"jack");
        HeroNode node3 = new HeroNode(6,"smith");
        HeroNode node4 = new HeroNode(8,"mary");
        HeroNode node5 = new HeroNode(10,"king");
        HeroNode node6 = new HeroNode(14,"dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree tree = new ThreadedBinaryTree();
        tree.setRoot(root);
        // 线索化

        tree.threadedNodes();

        // 线索化后遍历（线性遍历）
        tree.threadedList();


    }
}

class ThreadedBinaryTree {
    private HeroNode root;
    private HeroNode pre = null;

    ThreadedBinaryTree() {
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    public void threadedNodes() {
        this.threadedNodes(this.root);
    }


    public void threadedNodes(HeroNode node) {
        if (node == null) {
            return;
        }
        // 线索化左子树
        threadedNodes(node.getLeft());
        if (node.getLeft() == null) {
            // 让当前节点的左指针指向前驱节点
            node.setLeft(pre);
            // 修改当前节点的左指针的类型，指向前驱节点
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

        // 线索化右子树
        threadedNodes(node.getRight());
    }

    // 遍历线索化二叉树的方法
    public void threadedList(){
        HeroNode node = root;
        while (node!=null){
            // 循环找到leftType == 1的节点，第一个找到就是8
            while (node.getLeftType() ==0){
                node = node.getLeft();
            }
            System.out.println(node);
            // 如果当前节点右指针指向的是后继节点
            while (node.getRightType()==1){
                node = node.getRight();
                System.out.println(node);
            }

            // 替换这个遍历的节点
            node = node.getRight();
        }
    }

    public void delNode(int no) {
        if (this.root != null) {
            if (this.root.getNo() == no) {
                this.root = null;
            } else {
                this.root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除~");
        }

    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }

    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }

    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }

    }

    public HeroNode preOrderSearch(int no) {
        return this.root != null ? this.root.preOrderSearch(no) : null;
    }

    public HeroNode infixOrderSearch(int no) {
        return this.root != null ? this.root.infixOrderSearch(no) : null;
    }

    public HeroNode postOrderSearch(int no) {
        return this.root != null ? this.root.postOrderSearch(no) : null;
    }
}

class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;
    private int leftType;
    private int rightType;

    public int getLeftType() {
        return this.leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return this.rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return this.no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return this.left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return this.right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    public String toString() {
        return "HeroNode [no=" + this.no + ", name=" + this.name + "]";
    }

    public void delNode(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
        } else if (this.right != null && this.right.no == no) {
            this.right = null;
        } else {
            if (this.left != null) {
                this.left.delNode(no);
            }

            if (this.right != null) {
                this.right.delNode(no);
            }

        }
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }

        if (this.right != null) {
            this.right.preOrder();
        }

    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }

    }

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }

        if (this.right != null) {
            this.right.postOrder();
        }

        System.out.println(this);
    }

    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历");
        if (this.no == no) {
            return this;
        } else {
            HeroNode resNode = null;
            if (this.left != null) {
                resNode = this.left.preOrderSearch(no);
            }

            if (resNode != null) {
                return resNode;
            } else {
                if (this.right != null) {
                    resNode = this.right.preOrderSearch(no);
                }

                return resNode;
            }
        }
    }

    public HeroNode infixOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        } else {
            System.out.println("进入中序查找");
            if (this.no == no) {
                return this;
            } else {
                if (this.right != null) {
                    resNode = this.right.infixOrderSearch(no);
                }

                return resNode;
            }
        }
    }

    public HeroNode postOrderSearch(int no) {
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }

        if (resNode != null) {
            return resNode;
        } else {
            if (this.right != null) {
                resNode = this.right.postOrderSearch(no);
            }

            if (resNode != null) {
                return resNode;
            } else {
                System.out.println("进入后序查找");
                return this.no == no ? this : resNode;
            }
        }
    }
}
