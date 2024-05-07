import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class TreeNode {
    int key;
    TreeNode left, right;

    public TreeNode(int item) {
        key = item;
        left = right = null;
    }
}

class BinarySearchTree {
    TreeNode root;

    BinarySearchTree() {
        root = null;
    }

    void insert(int key) {
        root = insertRec(root, key);
    }

    TreeNode insertRec(TreeNode root, int key) {
        if (root == null) {
            root = new TreeNode(key);
            return root;
        }

        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        return root;
    }

    void delete(int key) {
        root = deleteRec(root, key);
    }

    TreeNode deleteRec(TreeNode root, int key) {
        if (root == null)
            return root;

        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            root.key = minValue(root.right);
            root.right = deleteRec(root.right, root.key);
        }

        return root;
    }

    int minValue(TreeNode root) {
        int minv = root.key;
        while (root.left != null) {
            minv = root.left.key;
            root = root.left;
        }
        return minv;
    }

    void inorder() {
        inorderRec(root);
    }

    void inorderRec(TreeNode root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    TreeNode search(int key) {
        return searchRec(root, key);
    }

    TreeNode searchRec(TreeNode root, int key) {
        if (root == null || root.key == key)
            return root;

        if (root.key < key)
            return searchRec(root.right, key);

        return searchRec(root.left, key);
    }

    void bfs() {
        if (root == null)
            return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode tempNode = queue.poll();
            System.out.print(tempNode.key + " ");

            if (tempNode.left != null)
                queue.add(tempNode.left);

            if (tempNode.right != null)
                queue.add(tempNode.right);
        }
    }
}

public class ADS1 {
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        Scanner scanner = new Scanner(System.in);
        int choice, key;

        do {
            System.out.println("Binary Search Tree Operations:");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Display (Inorder)");
            System.out.println("4. Search");
            System.out.println("5. BFS (Level-wise print)");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter key to insert: ");
                    key = scanner.nextInt();
                    tree.insert(key);
                    System.out.println(key + " inserted into BST");
                    break;
                case 2:
                    System.out.print("Enter key to delete: ");
                    key = scanner.nextInt();
                    tree.delete(key);
                    System.out.println(key + " deleted from BST");
                    break;
                case 3:
                    System.out.print("Inorder traversal: ");
                    tree.inorder();
                    System.out.println();
                    break;
                case 4:
                    System.out.print("Enter key to search: ");
                    key = scanner.nextInt();
                    TreeNode result = tree.search(key);
                    if (result != null)
                        System.out.println("Key " + key + " found in BST");
                    else
                        System.out.println("Key " + key + " not found in BST");
                    break;
                case 5:
                    System.out.print("BFS (Level-wise print): ");
                    tree.bfs();
                    System.out.println();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 6);

        scanner.close();
    }
}