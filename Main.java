import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("========================================");
        System.out.println("      📦 INVENTORY MANAGER (JDBC)");
        System.out.println("========================================");

        boolean running = true;
        while (running) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Add Item");
            System.out.println("2. View All Items");
            System.out.println("3. Update Item Quantity");
            System.out.println("4. Delete Item");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        System.out.print("Enter Item Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Quantity: ");
                        int quantity = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter Price: ");
                        double price = Double.parseDouble(scanner.nextLine());
                        manager.addItem(name, quantity, price);
                        break;

                    case "2":
                        manager.viewItems();
                        break;

                    case "3":
                        System.out.print("Enter Item ID to update: ");
                        int updateId = Integer.parseInt(scanner.nextLine());
                        System.out.print("Enter New Quantity: ");
                        int newQty = Integer.parseInt(scanner.nextLine());
                        manager.updateItem(updateId, newQty);
                        break;

                    case "4":
                        System.out.print("Enter Item ID to delete: ");
                        int deleteId = Integer.parseInt(scanner.nextLine());
                        manager.deleteItem(deleteId);
                        break;

                    case "5":
                        running = false;
                        System.out.println("👋 Goodbye!");
                        break;

                    default:
                        System.out.println("❌ Invalid choice.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Please enter numbers where required.");
            }
        }
        scanner.close();
    }
}
