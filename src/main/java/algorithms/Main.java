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

class RBTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    Node root;

    static class Node {
        int value;
        Node left;
        Node right;
        boolean color;

        Node(int value) {
            this.value = value;
            this.color = RED; // Новые ноды всегда красные
        }
    }

    public boolean isRed(Node node) {
        if (node == null) {
            return false;
        }
        return node.color;
    }

    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void push(int value) {
        root = push(root, value);
        root.color = BLACK; // Корень всегда черный
    }

    private Node push(Node h, int value) {
        if (h == null) return new Node(value);

        if (value < h.value) {
            h.left = push(h.left, value);
        } else if (value > h.value) {
            h.right = push(h.right, value);
        } else {
            // значение уже существует, ничего не делаем
            return h;
        }

        // Балансировка дерева
        if (isRed(h.right) && !isRed(h.left)) {
            h = rotateLeft(h);
        }
        if (isRed(h.left) && isRed(h.left.left)) {
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)) {
            flipColors(h);
        }

        return h;
    }

    public boolean find(int value) {
        return find(root, value);
    }

    private boolean find(Node node, int value) {
        while (node != null) {
            if (value < node.value) {
                node = node.left;
            } else if (value > node.value) {
                node = node.right;
            } else {
                return true;
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

        RBTree tree = new RBTree();

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
