import java.util.*;

public class Main {

    public static void main(String[] args) {
        Hashtable<Integer> table = new Hashtable<>();
        Scanner scan = new Scanner(System.in);

        int choice = 0;

        do {
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("0. Exit");
            System.out.println("Your choice: ");

            choice = scan.nextInt();

            switch(choice) {
                case 1:
                    int ins = scan.nextInt();
                    String key = scan.next();
                    table.insert(key, ins);
                    break;
                case 2:
                    String del = scan.next();
                    table.delete(del);
                    break;
                case 3:
                    String srch = scan.next();
                    table.search(srch);
                    break;
            }
        }while (choice != 0);
    }


}


