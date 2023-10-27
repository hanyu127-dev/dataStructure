package com.hanyu.binarysorttree;

public class BinarySortTreeDemo {
    public static void main(String[] args) {
        BinarySortTree binarySortTree = new BinarySortTree();
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};

        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        // 中序遍历二叉排序树(1 3 5 7 9 10 12)
        binarySortTree.infixOrder();
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        System.out.println(binarySortTree.getRoot());
        System.out.println("删除节点后");
        binarySortTree.infixOrder();


    }
}

//创建二叉排序树
class BinarySortTree {
    private Node root;

    public Node getRoot(){
        return this.root;
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }


    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }
    //
    public int delRightMin(Node node){
        Node temp = node;
        // 循环查找左子节点
        while (temp.left!=null){
            temp = temp.left;
        }
        // 这时temp就指向了最小节点
        delNode(temp.value);
        return temp.value;
    }

    // 查找要删除的节点
    public Node search(int value) {
        if (root != null) {
            return root.search(value);
        } else {
            return null;
        }
    }

    // 查找要删除节点的父节点
    public Node searchParent(int value) {
        if (root != null) {
            return root.searchParent(value);
        } else {
            return null;
        }
    }

    public void delNode(int value) {
        if (root != null) {
//            // 要删除的节点是根节点
//            if (root.value == value &&root.left==null&&root.right==null){
//                root = null;
//                return;
//            }
            Node targetNode = search(value);
            if (targetNode == null) {
                return;
            }
            Node parentNode = searchParent(value);
            // 判断父节点是否为空，如果为空，则要删除的节点是根节点
            if (parentNode == null) {
                // 判断要删除的节点的左子树和右字树是否为空
                if (root.left == null && root.right == null) {
                    root = null;
                } else if (root.left != null && root.right == null) {
                    // 左子树不为空，右子树为空
                    root = root.left;
                } else if (root.left == null) {
                    root = root.right;
                } else {
                    // 左子树和右子树都不为空
                    root.value = delRightMin(root.right);

                }
            } else {
                // 判断targetNode是不是叶子节点
                if (targetNode.left == null && targetNode.right == null) {
                    // 如果是，则继续判断目标节点是父节点的左子节点还是右子节点
                    if (targetNode == parentNode.left) {
                        parentNode.left = null;
                        return;
                    }
                    if (targetNode == parentNode.right) {
                        parentNode.right = null;
                    }
                } else if (targetNode.left != null && targetNode.right != null) {
                    // 都不为空
                    targetNode.value = delRightMin(targetNode.right);
                } else {
                    // 只有一个子树不为空
                    // 左子树不为空
                    if (targetNode.left != null) {

                        if (parentNode.left==targetNode){
                            // 当前节点是父节点的左子节点
                            parentNode.left = targetNode.left;
                        }else {
                            // 当前节点是父节点的右子节点
                            parentNode.right = targetNode.left;
                        }
                    }else {
                        // 右子节点不为空
                        if (parentNode.left==targetNode){
                            // 当前节点是父节点的左子节点
                            parentNode.left = targetNode.right;
                        }else {
                            // 当前节点是父节点的右子节点
                            parentNode.right = targetNode.right;
                        }
                    }
                }
            }
        } else {
            return;
        }
    }
}

// 创建Node节点
class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    // 添加节点的方法
    // 递归的形式添加
    public void add(Node node) {
        if (node == null) {
            return;
        }
        // 判断传入节点的值和当前子树根节点的值关系
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }
    // 查找要删除节点的父节点

    public Node searchParent(int value) {
        if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
            return this;
        } else {

            if (value < this.value && this.left != null) {
                // 如果查找的值小于当前节点的值，并且当前节点左子节点不为空
                // 递归向左查找
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null) {
                // 如果查找的值不小于当前节点的值，并且当前节点右子节点不为空
                // 递归向右查找
                return this.right.searchParent(value);
            } else {
                return null;
            }
        }

    }


    // 查找要删除的节点
    public Node search(int value) {
        if (value == this.value) {
            return this;
        } else if (value < this.value) {
            //向左子树递归查找
            if (this.left != null) {
                return this.left.search(value);
            } else {
                return null;
            }
        } else {
            if (this.right != null) {
                return this.right.search(value);
            } else {
                return null;
            }
        }

    }

    // 中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }


    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}

