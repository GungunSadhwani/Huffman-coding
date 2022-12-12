import java.util.Comparator;
import java.util.PriorityQueue;

//create a class to make a nodes of tree
class HuffmanNode {
    int data;
    char ch;
    HuffmanNode right;
    HuffmanNode left;

    HuffmanNode()// node constructor that initallize the values
    {
        right = null;
        left = null;
    }
}

// Comparator compare the nodes
// if this function return
// negative value than node2 > node1
// positive value node2 < node1
class MyComparator implements Comparator<HuffmanNode> {

    public int compare(HuffmanNode node1, HuffmanNode node2) {
        return node1.data - node2.data;
    }
}

// main class of huffman algorithm implementation
class HuffmanAlgorithm {

    // method for decoding the encode string
    public static String decode(HuffmanNode root, String str) {
        // create a node which is initally equal to root
        HuffmanNode currentNode = root;

        // create a string
        String s = " ";

        // if string charcter is equal to the 0 then
        // current node is equal to left
        // else current node equal to one
        // if both left and right of current node
        // equal to null then return that node frequency
        // and charcter then current node equal to root
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }

            if (currentNode.left == null && currentNode.right == null) {
                s += currentNode.ch;
                currentNode = root;
            }
        }
        return s;
    }

    // recursive call the function to print
    // huffman code through tree traversal
    static void print(HuffmanNode node, String str) {
        // base case of function print
        if (node.left == null && node.right == null && Character.isLetter(node.ch)) {
            System.out.println(node.ch + " = " + str);
            return;
        }

        // rcursive calling of function for left
        // and right toprint the tree
        print(node.left, str + "0");
        print(node.right, str + "1");
    }

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);

        char CharArray[] = { 'z', 'y', 'r', 'k', 't', 'o' };
        int frequencyArray[] = { 5, 9, 12, 13, 16, 45 };
        int n = frequencyArray.length;

        // built in priority queue is making
        // also makes min priority queue(min heap)
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<HuffmanNode>(n, new MyComparator());

        for (int i = 0; i < frequencyArray.length; i++) {
            HuffmanNode obj = new HuffmanNode(); // object of huffmanNode

            obj.ch = CharArray[i];
            obj.data = frequencyArray[i];

            // this built in function add the character and frequency of node in the queue
            pq.add(obj);

        }

        // making the root of a tree
        HuffmanNode root = null;

        // extraction of two min values from heap
        // and also add in queue
        // in each iteration these things are done
        // until queue size is reduce to 1
        while (pq.size() > 1) {
            // extract the first min value from heap
            HuffmanNode node1 = pq.peek();
            pq.poll();

            // extract the second value from heap
            HuffmanNode node2 = pq.peek();
            pq.poll();

            // create new node
            HuffmanNode x = new HuffmanNode();

            // add the data of two min values(frequency) in new node(x)
            x.data = node1.data + node2.data;
            x.ch = '-';

            // first remove leftnode
            x.left = node1;

            // second remove rightnode
            x.right = node2;

            // new node equals to root
            root = x;

            // add the node in priority queue
            pq.add(x);
        }
        print(root, " ");
        System.out.println(decode(root, "10000111"));

    }
}