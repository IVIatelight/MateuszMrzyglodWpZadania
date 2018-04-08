//autor Mateusz Mrzyg³ód
public class DatabaseFactory {

    public DatabaseInterface getDatabase(String databaseType) {
        
    	if (databaseType == null) {
            return null;
        }
        databaseType = databaseType.toLowerCase();

        switch (databaseType) {
            case "products":
                return new Products();
            case "users":
                return new Users();
            case "privileges":
                return new Privileges();
        }

        return null;	
    }
}
