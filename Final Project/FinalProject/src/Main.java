public class Main {
    public static void main(String[] args) {

        // Before running the program, make sure to have set up the database
        // The database is set up by copying and pasting the contents of the mySQLScript.txt file into the mySQL Workbench
        // OR
        
        // Run the file marked "HenryFundenberger - DB Setup Script.sql" in the mySQL Workbench
        // Then running the script
        // You WILL need to make some changes in the databaseInfo.java file to match your database credentials such as your username and password
        // This should be the only file you actually need to change!
        new loginMenu();
    }
}