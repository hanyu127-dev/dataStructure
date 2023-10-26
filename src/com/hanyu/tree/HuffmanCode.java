package com.hanyu.tree;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
        // 测试压缩文件

        String dstFile = "d://dst.zip";
//        String srcFile = "src.bmp";
//        zipFile(srcFile,dstFile);
//        System.out.println("压缩文件ok~");

        UnZipFile(dstFile,"d://dst.bmp");
        System.out.println("解压文件ok");









//        String content = "i like like like java do you like a java";
//        byte[] contentBytes = content.getBytes();
//        byte[] huffumanZip = huffumanZip(contentBytes);
////        System.out.println(Arrays.toString(huffumanZip));
//
//        byte[] decode = decode(huffmanCodes, huffumanZip);
//        String s = new String(decode);
//        System.out.println(s);


//        List<HNode> nodes = getNodes(contentBytes);
////        System.out.println(nodes);
//        //构建赫夫曼树
//
//        HNode hNode = CreateHeffumanTree(nodes);
//
//        // 前序遍历
////        preOrder(hNode);
//
//        // 测试生成的哈夫曼编码
//        getCodes(hNode);
//        System.out.println(huffmanCodes);
//
//        // 测试获取压缩数据
//        byte[] zip = zip(contentBytes, huffmanCodes);
//        System.out.println(Arrays.toString(zip));


    }
    // 编写一个方法，将文件进行解压
    public static void UnZipFile(String zipFile, String dstFile) {

        // 创建一个文件的输入流
        InputStream is = null;
        // 创建输出流
        OutputStream os = null;
        ObjectInputStream ois = null;
        try {
            // 创建文件输入流
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            byte[] huffumanBytes1 = (byte[]) ois.readObject();
            Map<Byte,String> hufumanCodes1 = (Map<Byte, String>) ois.readObject();
            // 解码
            byte[] bytes = decode(hufumanCodes1,huffumanBytes1);
            // 将文件写入目标文件
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭输入流
            try {
                os.close();
                ois.close();
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }



    // 编写一个方法，将文件进行压缩
    public static void zipFile(String srcFile, String dstFile) {

        // 创建一个文件的输入流
        FileInputStream fis = null;
        // 创建输出流
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fis = new FileInputStream(srcFile);
            // 创建一个和原文件大小一样的byte[]
            byte[] b = new byte[fis.available()];

            // 读取文件,将数据读到字节数组中
            fis.read(b);
            // 对原文件进行压缩
            byte[] huffumanBytes= huffumanZip(b);
            // 创建文件的输出流，存放压缩文件
            fos = new FileOutputStream(dstFile);
            // 创建一个和文件输出流关联的ObjectOutputStream
            oos = new ObjectOutputStream(fos);
            oos.writeObject(huffumanBytes);
            // 这里我们以对象流的方式写入赫夫曼编码表，是为了我们恢复文件时使用
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // 关闭输入流
            try {
                fis.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            // 关闭输出流
            try {
                oos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                fos.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    // 完成对压缩数据的解码
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        // 1. 先得到huffmanBytes对应的二进制的字符串
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < huffmanBytes.length; i++) {
            // 如果是最后一个字节，无需补高位
            if (i != huffmanBytes.length - 1) {
                builder.append(byteToString(true, huffmanBytes[i]));
            } else {
                builder.append(byteToString(false, huffmanBytes[i]));
            }
        }
        // 根据huffmanCodes，将对应的二进制的字符串转为byte[]
        // 将赫夫曼编码表进行调换
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        // 创建一个集合，存放byte
        List<Byte> list = new ArrayList<>();
        // 扫描二进制字符串
        for (int i = 0; i < builder.length(); ) {
            int count = 1;
            boolean flag = true;
            Byte b = null;
            while (flag) {
                // i不动，让count动
                String key = builder.substring(i, i + count);
                b = map.get(key);
                if (b == null) {
                    count++;
                } else {
                    flag = false;
                }
            }
            // 添加元素
            list.add(b);
            // 将i移动到count
            i += count;
        }

        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }


    // 完成数据的解压缩
    // 思路
    // 1.将带解压的数组先转成赫夫曼编码对应的二进制字符串10101000101111111100100010111111110010001011111111001001010011011100..
    // 2.将赫夫曼编码对应的二进制的字符串转为字符串i like like like java do you like a java

    /**
     * @param flag 标识是否需要补高位
     * @param b    字节
     * @return 返回byte对应的二进制字符串，按补码返回
     */
    private static String byteToString(boolean flag, byte b) {
        // 使用变量保存b
        int temp = b;
        if (flag) {
            // 如果是正数，我们还存在一个补高位的问题
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }


    // 使用一个方法，将前面的方法封装起来

    /**
     * @param bytes 原始字符串对应的byte数组
     * @return 压缩后的数组
     */
    private static byte[] huffumanZip(byte[] bytes) {
        List<HNode> nodes = getNodes(bytes);
        //创建赫夫曼树
        HNode root = CreateHeffumanTree(nodes);
        // 生成对应的赫夫曼编码
        getCodes(root);
        // 根据生成的赫夫曼编码压缩字节数组并返回
        return zip(bytes, huffmanCodes);
    }


    // 根据赫夫曼编码，将字符串对应的byte[]数组压缩，返回压缩后的Byte[]数组

    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 利用huffmanCodes 将 bytes 转成 赫夫曼编码对应的字符串
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
//        System.out.println("测试" + stringBuilder.toString());
        // 将10101000101111111100100010111111110010001011111111001001010011011100...转为byte[]
        int len;
        if (stringBuilder.length() % 8 == 0) {
            len = stringBuilder.length() / 8;
        } else {
            len = stringBuilder.length() / 8 + 1;
        }

        byte[] huffumanCodeBytes = new byte[len];
        int index = 0;

        for (int i = 0; i < stringBuilder.length(); i += 8) {
            String strByte;
            if (i + 8 > stringBuilder.length()) {
                strByte = stringBuilder.substring(i);
            } else {
                strByte = stringBuilder.substring(i, i + 8);
            }
            // 将strByte转成一个byte
            huffumanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index++;
        }

        return huffumanCodeBytes;
    }

    // 生成赫夫曼树对应的赫夫曼编码
    // 思路：
    // 1.将赫夫曼编码表存放在Map<Byte,String> 形式
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    // 2.在生成赫夫曼编码时，需要拼接路径，定义一个StringBuilder,存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 功能：创入的node节点的所有叶子节点的赫夫曼编码得到，并放入到huffmanCodes集合
     *
     * @param node    根节点
     * @param code    路径
     * @param builder 拼接路径
     */

    private static void getCodes(HNode node, String code, StringBuilder builder) {
        StringBuilder builder1 = new StringBuilder(builder);
        builder1.append(code);
        if (node != null) {
            // 判断当前是叶子节点还是非叶子节点
            if (node.data == null) {
                // 非叶子节点
                // 递归处理
                getCodes(node.leftNode, "0", builder1);
                getCodes(node.rightNode, "1", builder1);
            } else {
                // 叶子节点，则停止
                // 表示找到叶子节点
                huffmanCodes.put(node.data, builder1.toString());
            }
        }
    }


    // 构建赫夫曼树
    private static HNode CreateHeffumanTree(List<HNode> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);

            HNode leftNode = nodes.get(0);
            HNode rightNode = nodes.get(1);

            HNode parent = new HNode(leftNode.weight + rightNode.weight);
            parent.leftNode = leftNode;
            parent.rightNode = rightNode;

            nodes.remove(leftNode);
            nodes.remove(rightNode);

            nodes.add(parent);
        }

        return nodes.get(0);
    }


    private static void getCodes(HNode root) {
        if (root == null) {
            return;
        }
        // 处理root的左子树
        getCodes(root.leftNode, "0", stringBuilder);
        getCodes(root.rightNode, "1", stringBuilder);
    }

    private static List<HNode> getNodes(byte[] bytes) {
        ArrayList<HNode> nodes = new ArrayList<>();
        // 遍历bytes,统计每个byte出现的次数 ->map[key,value];
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            counts.merge(b, 1, Integer::sum);
        }

        // 把每个键值对转成一个HNode对象
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
            nodes.add(new HNode(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    private static void preOrder(HNode root) {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }
}


class HNode implements Comparable<HNode> {
    // 存放数据本身，即是字符
    Byte data;
    // 权值
    int weight;
    HNode leftNode;
    HNode rightNode;

    public HNode(int weight) {
        this.weight = weight;
    }

    public HNode(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public int compareTo(HNode o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "HNode{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (this.leftNode != null) {
            this.leftNode.preOrder();
        }

        if (this.rightNode != null) {
            this.rightNode.preOrder();
        }
    }
}