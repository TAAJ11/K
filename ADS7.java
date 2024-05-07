import java.io.*;
import java.util.*;
public class ADS7 {
   private String[] dictionary;
   private int capacity;
   public ADS7(int capacity) {
       this.capacity = capacity;
       dictionary = new String[capacity];
   }
   public void addWord(String word) {
       int index = linearProbe(word);
       if (dictionary[index] == null || dictionary[index].equals(word)) {
           dictionary[index] = word.toLowerCase();
           System.out.println("'" + word + "' added to the dictionary using linear probing.");
       } else {
           System.out.println("Collision occurred while adding '" + word + "'. Word not added.");
       }
   }
   public void searchWord(String word, String probingType) {
       int index = 0;
       if (probingType.equalsIgnoreCase("linear")) {
           index = linearProbe(word);
       } else if (probingType.equalsIgnoreCase("quadratic")) {
           index = quadraticProbe(word);
       } else {
           System.out.println("Invalid probing type. Please choose 'linear' or 'quadratic'.");
           return;
       }
       if (dictionary[index] != null && dictionary[index].equals(word.toLowerCase())) {
           System.out.println("'" + word + "' is a valid word.");
       } else {
           System.out.println("'" + word + "' is not found in the dictionary.");
       }
   }
   // Linear Probing
   private int linearProbe(String word) {
       int hash = hashFunction(word);
       int index = hash % capacity;
       while (dictionary[index] != null && !dictionary[index].equals(word.toLowerCase())) {
           index = (index + 1) % capacity;
       }
       return index;
   }
   // Quadratic Probing
   private int quadraticProbe(String word) {
       int hash = hashFunction(word);
       int index = hash % capacity;
       int i = 1;
       while (dictionary[index] != null && !dictionary[index].equals(word.toLowerCase())) {
           index = (index + i * i) % capacity;
           i++;
       }
       return index;
   }
   private int hashFunction(String word) {
       int hash = 7;
       for (int i = 0; i < word.length(); i++) {
           hash = hash * 31 + word.charAt(i);
       }
       return hash;
   }
   public void saveToFile(String filename) {
       try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
           for (String word : dictionary) {
               if (word != null) {
                   writer.println(word);
               }
           }
           System.out.println("Dictionary saved to " + filename);
       } catch (IOException e) {
           System.out.println("Error occurred while saving the dictionary: " + e.getMessage());
       }
   }
   public void loadFromFile(String filename) {
       try (Scanner scanner = new Scanner(new File(filename))) {
           int count = 0;
           while (scanner.hasNextLine()) {
               String word = scanner.nextLine().trim();
               addWord(word);
               count++;
           }
           System.out.println(count + " words loaded from " + filename);
       } catch (FileNotFoundException e) {
           System.out.println("File not found: " + filename);
       }
   }
   public void run() {
       Scanner scanner = new Scanner(System.in);
       while (true) {
           System.out.println("\n1. Enter Word\n2. Search Word\n3. Save Dictionary\n4. Load Dictionary\n5. Exit");
           System.out.print("Enter your choice: ");
           String choice = scanner.nextLine();
           switch (choice) {
               case "1":
                   System.out.print("Enter the word to add to dictionary: ");
                   String wordToAdd = scanner.nextLine();
                   addWord(wordToAdd);
                   break;
               case "2":
                   System.out.print("Enter the word to search in dictionary: ");
                   String wordToSearch = scanner.nextLine();
                   System.out.print("Enter the probing type (linear/quadratic): ");
                   String probingTypeToSearch = scanner.nextLine();
                   searchWord(wordToSearch, probingTypeToSearch);
                   break;
               case "3":
                   System.out.print("Enter the filename to save the dictionary: ");
                   String saveFileName = scanner.nextLine();
                   saveToFile(saveFileName);
                   break;
               case "4":
                   System.out.print("Enter the filename to load the dictionary: ");
                   String loadFileName = scanner.nextLine();
                   loadFromFile(loadFileName);
                   break;
               case "5":
                   scanner.close();
                   return;
               default:
                   System.out.println("Invalid choice. Please enter a valid option.");
           }
       }
   }
   public static void main(String[] args) {
       ADS7 isSpellUtility = new ADS7(60);
       isSpellUtility.run();
   }
}