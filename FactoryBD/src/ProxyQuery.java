
public class ProxyQuery implements QueryInterface{

	private Query runQuery = new Query();
	
	public String[][] select(String query, String tableName){
		
		tableExist(tableName);
		String[][] wrongQuery = {{"Query"," is ", "wrong"}};
				
		if(query.indexOf("SELECT * FROM ")== -1){
			
			System.out.println("No SELECT * FROM in query");
			return wrongQuery;
		}
		
		if(!tableExist(tableName)){
			
			System.out.println("table dont exist");
			return wrongQuery;
		}
		
		String tableNameInQuery = query.substring(14);
		tableNameInQuery = tableNameInQuery.replaceFirst(" ORDER BY NAME ASC", "");
		tableNameInQuery = tableNameInQuery.replaceFirst(" ORDER BY NAME DESC", "");		
		if( !tableNameInQuery.equalsIgnoreCase(tableName) ){
			
			System.out.println("Table name dont equals");
			return wrongQuery;
		}
		
		return runQuery.select(query, tableName);
	}
	
	public void insert(String query){
		
		if(query.indexOf("INSERT INTO ")== -1){
			
			System.out.println("No INSERT INTO in query");
			return;
		}
		
		String table[] = query.split(" ", 4);
		
		if(!tableExist(table[2])){
			
			return;
		}
		
		runQuery.insert(query);
	}
	
	public void delete(String query){

		if(query.indexOf("DELETE FROM ")== -1){
			
			System.out.println("No DELETE FROM  in query");
			return;
		}
		
		String table[] = query.split(" ", 4);
		
		if(!tableExist(table[2])){
			
			return;
		}
		
		runQuery.delete(query);
	}
	
	private boolean tableExist(String tableName) {
		
		if(tableName.equalsIgnoreCase("Users"))
			return true;
		
		if(tableName.equalsIgnoreCase("Products"))
			return true;
		
		if(tableName.equalsIgnoreCase("Privileges"))
			return true;
		
		return false;
	}
	
}
