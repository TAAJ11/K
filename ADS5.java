import java.util.*;
import java.util.List;
class User {
   private String name;
   private Calendar dateOfBirth;
   private int numOfComments;
   public User(String name, Calendar dateOfBirth) {
       this.name = name;
       this.dateOfBirth = dateOfBirth;
       this.numOfComments = 0;
   }
   public String getName() {
       return name;
   }
   public Calendar getDateOfBirth() {
       return dateOfBirth;
   }
   public int getNumOfComments() {
       return numOfComments;
   }
   public void incrementComments() {
       numOfComments++;
   }
}
class FriendNetwork {
   private Map<User, List<User>> network;
   public FriendNetwork() {
       network = new HashMap<>();
   }
   public void addUser(User user) {
       network.put(user, new ArrayList<>());
   }
   public void addFriendship(User user1, User user2) {
       network.get(user1).add(user2);
       network.get(user2).add(user1);
   }
   public User findMaxFriends() {
       int maxFriends = Integer.MIN_VALUE;
       User maxFriendUser = null;
       for (Map.Entry<User, List<User>> entry : network.entrySet()) {
           if (entry.getValue().size() > maxFriends) {
               maxFriends = entry.getValue().size();
               maxFriendUser = entry.getKey();
           }
       }
       return maxFriendUser;
   }
   public User findMaxCommentsUser() {
       int maxComments = Integer.MIN_VALUE;
       User maxCommentsUser = null;
       for (Map.Entry<User, List<User>> entry : network.entrySet()) {
           User user = entry.getKey();
           if (user.getNumOfComments() > maxComments) {
               maxComments = user.getNumOfComments();
               maxCommentsUser = user;
           }
       }
       return maxCommentsUser;
   }
   public User findMinCommentsUser() {
       int minComments = Integer.MAX_VALUE;
       User minCommentsUser = null;
       for (Map.Entry<User, List<User>> entry : network.entrySet()) {
           User user = entry.getKey();
           if (user.getNumOfComments() < minComments) {
               minComments = user.getNumOfComments();
               minCommentsUser = user;
           }
       }
       return minCommentsUser;
   }
   public List<User> findUsersWithBirthdayThisMonth(Date currentDate) {
       List<User> usersWithBirthday = new ArrayList<>();
       Calendar calendar = Calendar.getInstance();
       calendar.setTime(currentDate);
       int currentMonth = calendar.get(Calendar.MONTH);
       for (Map.Entry<User, List<User>> entry : network.entrySet()) {
           Calendar dob = entry.getKey().getDateOfBirth();
           int birthMonth = dob.get(Calendar.MONTH);
           if (birthMonth == currentMonth) {
               usersWithBirthday.add(entry.getKey());
           }
       }
       return usersWithBirthday;
   }
}
public class ADS5 {
   public static void main(String[] args) {
       Scanner scanner = new Scanner(System.in);
       FriendNetwork friendNetwork = new FriendNetwork();
       // Adding users
       Calendar dobUser1 = Calendar.getInstance();
       dobUser1.set(2004, Calendar.APRIL, 28); // Sample birthdate
       User user1 = new User("Harita", dobUser1);
       Calendar dobUser2 = Calendar.getInstance();
       dobUser2.set(2009, Calendar.MAY, 30);
       User user2 = new User("Manas", dobUser2);
       Calendar dobUser3 = Calendar.getInstance();
       dobUser3.set(2007, Calendar.MAY, 15);
       User user3 = new User("Lalita", dobUser3);
       friendNetwork.addUser(user1);
       friendNetwork.addUser(user2);
       friendNetwork.addUser(user3);
       // Adding friendships
       friendNetwork.addFriendship(user1, user2);
       friendNetwork.addFriendship(user1, user3);
       user1.incrementComments(); // Sample increment
       user2.incrementComments();
       user2.incrementComments();
       user3.incrementComments();
       // Task 1: Find user with maximum friends
       User maxFriendsUser = friendNetwork.findMaxFriends();
       System.out.println("User with maximum friends: " + maxFriendsUser.getName());
       // Task 2: Find user with maximum comments
       User maxCommentsUser = friendNetwork.findMaxCommentsUser();
       System.out.println("User with maximum comments: " + maxCommentsUser.getName());
       User minCommentsUser = friendNetwork.findMinCommentsUser();
       System.out.println("User with minimum comments: " + minCommentsUser.getName());
       // Task 3: Find users with birthday in this month (Assuming current date)
       Calendar calendar = Calendar.getInstance();
       Date currentDate = calendar.getTime();
       List<User> usersWithBirthdayThisMonth = friendNetwork.findUsersWithBirthdayThisMonth(currentDate);
       System.out.println("Users with birthday in this month:");
       for (User user : usersWithBirthdayThisMonth) {
           System.out.println(user.getName() + " - " + user.getDateOfBirth().getTime());
       }
   }
}