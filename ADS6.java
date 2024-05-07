import java.util.Scanner;

class Heap {
	public void HeapifyMax(int[] arr, int n, int i) {
		int largest = i;
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		if(left < n && arr[left] > arr[largest]) {
			largest = left;
		}
		else if(right < n && arr[right] > arr[largest]) {
			largest = right;
		}
		if( largest != i) {
			int temp = arr[i];
			arr[i] = arr[largest];
			arr[largest] = temp;
			HeapifyMax(arr, n, largest);
		}
	}
	public void HeapifyMin(int[] arr, int n, int i) {
		int smallest = i;
		int left = 2 * i + 1;
		int right = 2 * i + 2;
		if(left < n && arr[left] < arr[smallest]) {
			smallest = left;
		}
		else if(right < n && arr[right] < arr[smallest]) {
			smallest = right;
		}
		if( smallest != i) {
			int temp = arr[i];
			arr[i] = arr[smallest];
			arr[smallest] = temp;
			HeapifyMax(arr, n, smallest);
		}
	}
	public void buildMaxHeap(int[] arr, int n) {
		for(int i=n/2-1; i>=0; i--) {
			HeapifyMax(arr,n,i);
		}
	}
	public void buildMinHeap(int[] arr, int n) {
		for(int i=n/2-1; i>=0; i--) {
			HeapifyMin(arr,n,i);
		}
	}
	public int getMax(int[] arr) {
		return arr[arr.length-1];
	}
	public int getMin(int[] arr) {
		return arr[0];
	}
}
public class ADS6{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Heap heap = new Heap();
		System.out.println("Enter no. of students: ");
		int n = sc.nextInt();
		int[] marks = new int[n];
		
		System.out.println("Enter marks of students: ");
		for(int i=0; i<n; i++) {
			marks[i] = sc.nextInt();
		}
		
		heap.buildMaxHeap(marks, n);
		heap.buildMinHeap(marks, n);
		
		System.out.println("Maximum marks: " + heap.getMax(marks));
		System.out.println("Minimum marks: " + heap.getMax(marks));
		sc.close();
	}
}