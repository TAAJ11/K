import java.util.Scanner;
import java.util.Stack;

//prefix = +A*BC
//postfix =

class Node
{
    char data;
    Node left;
    Node right;
    Node(char val){
        this.data = val;
        left = right = null;
    }
    Node(char val, Node left, Node right) {
        this.data = val;
        this.left = left;
        this.right = right;
    }
}

class expConvert
{
    public Node root;

    public expConvert() {
        root = null;
    }
    public Node insert(Node root, char val)
    {
        Node newNode = new Node(val);
        if(root == null){
            root = newNode;
            return root;
        }
        if(root.data > val){
            root.left = insert(root.left, val);
        }
        else{
            root.right = insert(root.right,val);
        }
        return root;
    }
    public void postFix(String exp)
    {
        Stack<Node> stack = new Stack<>();
        for(char ch : exp.toCharArray()) {
            if(Character.isLetterOrDigit(ch)){
                stack.push(new Node(ch));
            }
            else {
                if(!stack.empty()) {
                    Node right = stack.pop();
                    Node left = stack.pop();
                    stack.push(new Node(ch,left,right));
                }
            }
        }
        root = stack.pop();
    }
    public void preFix(String exp) 
    {
        Stack<Node> s = new Stack<>();
        for(int i=exp.length()-1;i>=0;i--) {
            char curr = exp.charAt(i);
            if(Character.isLetterOrDigit(curr)) {
                s.push(new Node(curr));
            }
            else {
                Node op1 = s.pop();
                Node op2 = s.pop();
                Node res = new Node(curr);
                res.left = op1;
                res.right = op2;
                s.push(res);
            }
        }
        root = s.pop();
    }
    public void inOrder(Node root) 
    {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }
    public void preOrder(Node node) 
    {
        
        if (node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    public void postOrder(Node node) 
    {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " ");
        }
    }
    public void nonRcurrInOrder(Node root)
    {
        if (root == null)
            return;
 
        Stack<Node> s = new Stack<Node>();
        Node curr = root;
        while (curr != null || s.size() > 0)
        {
            while (curr !=  null)
            {
                s.push(curr);
                curr = curr.left;
            }
            curr = s.pop();
            System.out.print(curr.data + " ");
            curr = curr.right;
        }
    }
    public void nonRcurrPreOrder(Node root)
    {
        Stack<Node> s = new Stack<Node>();
		while (true) {
			while (root != null) {
				System.out.print(root.data + " ");
				s.push(root);
				root = root.left;
			}
			if (s.isEmpty()) {
				return;
			}
			root = s.pop();
			root = root.right;
		}
    }
    public void nonRcurrPostOrder(Node root)
    {
        if (root == null)
            return;

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        s1.push(root);

        while (!s1.isEmpty()) {
            Node current = s1.pop();
            s2.push(current);

            if (current.left != null)
                s1.push(current.left);
            if (current.right != null)
                s1.push(current.right);
        }
        while (!s2.isEmpty()) {
            Node node = s2.pop();
            System.out.print(node.data + " ");
        }
    }
}

public class ADS2
{
    public static void main(String[] args)
    {
        expConvert e = new expConvert();
        Scanner sc = new Scanner(System.in);
        boolean work = true;
        do {
            System.out.println();
            System.out.println("Choose expression type: ");
            System.out.println("1. Postfix expression \n2. Prefix expression");
            String ans = sc.nextLine();
            switch (ans) {
                case "1":
                    System.out.println("Enter a postfix expression: ");
                    String post = sc.nextLine();
                    e.postFix(post);
                    work = false;
                    break;
                case "2":
                    System.out.println("Enter a prefix expression: ");
                    String pre = sc.nextLine();
                    e.preFix(pre);
                    work = false;
                    break;
                default:
                    System.out.println("Invalid input!!");
                    break;
            }
        }while(work);
        
        work = true;
        do {
            System.out.println();
            System.out.println("Choose display order type: ");
            System.out.println("1.InOrder Recursive \n2.InOrder Non-recursive \n3.PreOrder recursive \n4.PreOrder Non-recursive \n5.PostOrder recursive \n6.PostOrder recursive \n7.Exit");
            String order = sc.nextLine();
            switch (order) {
                case "1":
                    System.out.println();
                    System.out.println("In-Order traversal(recursive): ");
                    e.inOrder(e.root);
                    System.out.println();
                    break;
                case "2": 
                    System.out.println();
                    System.out.println("In-Order traversal(Non-recursive): ");
                    e.nonRcurrInOrder(e.root);
                    System.out.println();
                    break;
                case "3":
                    System.out.println();
                    System.out.println("Pre-Order traversal(recursive): ");
                    e.preOrder(e.root);
                    System.out.println();
                    break;
                case "4":
                    System.out.println();
                    System.out.println("Pre-Order traversal(Non-recursive): ");
                    e.nonRcurrPreOrder(e.root);
                    System.out.println();
                    break;
                case "5":
                    System.out.println();
                    System.out.println("Post-Order traversal(recursive): ");
                    e.postOrder(e.root);
                    System.out.println();
                    break;
                case "6":
                    System.out.println();
                    System.out.println("Post-Order traversal(Non-recursive): ");
                    e.nonRcurrPostOrder(e.root);
                    System.out.println();
                    break;
                case "7":
                    System.out.println();
                    System.out.println("Thank you!");
                    work = false;
                    break;
                default:
                    System.out.println("Invalid input!!");
                    break;
            }
        }while(work);
        sc.close();
    }
}