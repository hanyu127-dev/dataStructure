package com.hanyu.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        int[] arr = {10, 11, 7, 6, 8, 9};
        AVLTree avlTree = new AVLTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        avlTree.infixOrder();
        System.out.println("===========根节点");
        Node root = avlTree.getRoot();
        System.out.println(root);

        System.out.println("树的高度");
        System.out.println(root.height());

        System.out.println("根节点的左子树的高度");
        System.out.println(root.leftHeight());
        System.out.println("根节点的右子树的高度");
        System.out.println(root.rightHeight());

    }
}

class AVLTree {
    private Node root;

    public Node getRoot() {
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
    public int delRightMin(Node node) {
        Node temp = node;
        // 循环查找左子节点
        while (temp.left != null) {
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

                        if (parentNode.left == targetNode) {
                            // 当前节点是父节点的左子节点
                            parentNode.left = targetNode.left;
                        } else {
                            // 当前节点是父节点的右子节点
                            parentNode.right = targetNode.left;
                        }
                    } else {
                        // 右子节点不为空
                        if (parentNode.left == targetNode) {
                            // 当前节点是父节点的左子节点
                            parentNode.left = targetNode.right;
                        } else {
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

class Node {
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    // 返回左子树的高度
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    // 返回右子树的高度
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    // 返回以该节点为根节点的树的高度
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
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

        // 判断是否需要左旋转
        if (this.rightHeight() - this.leftHeight() > 1) {
            if (right != null && right.rightHeight() < right.leftHeight()) {
                right.rightRotate();
                this.leftHeight();
            } else {
                leftRotate();
            }
        }

        //判断是否需要右旋转
        if (this.leftHeight() - this.rightHeight() > 1) {
            if (left != null && left.leftHeight() < left.rightHeight()) {
                left.leftRotate();
                this.rightRotate();
            } else {
                rightRotate();
            }

        }

    }

    private void rightRotate() {
        // 1.新建一个节点，节点的值是当前节点的值
        Node temp = new Node(this.value);
        // 1. 新节点的右节点是当前节点的右节点
        temp.right = this.right;
        // 2.新节点的右节点是当前节点的右节点的左节点
        temp.left = this.left.right;
        // 3.当前节点的值为右节点的值
        this.value = this.left.value;
        // 4.当前节点的右节点是当前节点的右节点的右节点
        this.left = this.left.left;
        // 5.当前节点的左节点是新节点
        this.right = temp;
    }

    private void leftRotate() {
        // 1.新建一个节点，节点的值是当前节点的值
        Node temp = new Node(this.value);
        // 1. 新节点的左节点是当前节点的左节点
        temp.left = this.left;
        // 2.新节点的右节点是当前节点的右节点的左节点
        temp.right = this.right.left;
        // 3.当前节点的值为右节点的值
        this.value = this.right.value;
        // 4.当前节点的右节点是当前节点的右节点的右节点
        this.right = this.right.right;
        // 5.当前节点的左节点是新节点
        this.left = temp;

//        Node newLeft = new Node(value);
//        newLeft.right=right;
//        newLeft.left=left.right;
//        value=left.value;
//        left=left.left;
//        right=newLeft;

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
