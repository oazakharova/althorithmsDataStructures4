package algorithms;

class HashMap{
    class Entity{
        int key;
        int value;
    }
    class Basket{
        Node head;
        class Node{
            Entity entity;
            Node next;
        }

        int find(int key){
            Node node = head;
            while(node != null){
                if(node.entity.key == key){
                    return node.entity.value;
                }
                node = node.next;
            }
            return -1;
        }

        boolean push(Entity entity){
            Node node = new Node();
            node.entity = entity;

            if(head == null){
                head = node;
            }else {
                Node cur = head;
                while (cur.next != null){
                    if(cur.entity.key == entity.key){
                        return false;
                    }
                    cur = cur.next;
                }
                cur.next = node;
            }
            return true;
        }
    }

    static final int INIT_SIZE = 16;
    Basket[] baskets;

    public HashMap(){
        this(INIT_SIZE);
    }
    public HashMap(int size){
        baskets = new Basket[size];
    }

    int getIndex(int key){
        //key.hashCode();
        return key % baskets.length;
    }

    int find(int key){
        int index = getIndex(key);
        Basket basket = baskets[index];
        if(basket != null){
            return basket.find(key);
        }
        return -1;
    }

    boolean push(int key, int value){
        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;

        int index = getIndex(key);
        Basket basket = baskets[index];
        if(basket == null){
            basket = new Basket();
            baskets[index] = basket;
        }
        return basket.push(entity);
    }
}

class BinaryTree{
    Node root;
    class Node{
        int value;
        Node left;
        Node right;
    }

    boolean push(int value){
        if(root == null){
            root = new Node();
            root.value = value;
            return true;
        }else {
            Node node = root;
            while (node != null) {
                if (node.value == value) {
                    return false;
                }
                if (node.value < value) {
                    if (node.right == null) {
                        node.right = new Node();
                        node.right.value = value;
                        //rebalance()
                        return true;
                    } else {
                        node = node.right;
                    }
                } else {
                    if (node.left == null) {
                        node.left = new Node();
                        node.left.value = value;
                        return true;
                    } else {
                        node = node.left;
                    }
                }
            }
            return false;
        }
    }

    boolean find(int value){
        Node node = root;
        while(node != null){
            if(node.value == value){
                return true;
            }
            if(node.value < value){
                node = node.right;
            }else{
                node = node.left;
            }
        }
        return false;
    }

}
public class Main {
    public static void main(String[] args) {
//        HashMap map = new HashMap();
//        map.push(1, 2);
//        map.push(3, 4);
//        map.push(5, 6);
//
//        System.out.println(map.find(3));
//        System.out.println(map.find(2));
//
//        map.push(17, 8);
//
//        System.out.println(map.find(17));

        BinaryTree tree = new BinaryTree();

        tree.push(5);
        tree.push(3);
        tree.push(7);
        tree.push(2);
        tree.push(4);
        tree.push(6);
        tree.push(8);

        System.out.println(tree.find(8));
        System.out.println(tree.find(10));

    }
}
