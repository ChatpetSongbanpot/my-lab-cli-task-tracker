public class CommandParser {

    // ดึงคำสั่ง เช่น add, update, delete
    public static String getCommand(String input) {
        return input.split(" ")[0];
    }

    public static int getId(String input) {
        String[] parts = input.split(" ");
        if (parts.length >= 2) {
            return Integer.parseInt(parts[1]);
        }
        throw new IllegalArgumentException("Invalid ID input");
    }

    // ดึง description ที่อยู่ในเครื่องหมาย "..."
    public static String getDescription(String input) { //ex. update 1 "Do homework"  = [update 2 , Do homework]
        String[] parts = input.split("\"");
        if (parts.length >= 2) {
            return parts[1].trim();
        }
        return "";
    }
}
