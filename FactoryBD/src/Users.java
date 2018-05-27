//autor Mateusz Mrzyg³ód

class Users implements DatabaseInterface {

	private String tableName = "USERS";
	
	private ProxyQuery runProxyQuery = new ProxyQuery();
	
	@Override
	public String[][] sortedList(int order){

		String query = "";
		switch (order) {
		case 0:	query ="SELECT * FROM " + tableName; 
			break;
		case 1: query ="SELECT * FROM " + tableName + " ORDER BY NAME ASC"; 
			break;
		case 2: query ="SELECT * FROM " + tableName +  " ORDER BY NAME DESC"; 
			break;
		}		
	
		return runProxyQuery.select(query, tableName);
	}

	@Override
	public void addItem( String name){
		
		runProxyQuery.insert("INSERT INTO " + tableName + " (name, description) VALUES('"+ name +"', 'null' )");
	}
	
	@Override
	public void deleteItem(String id){

		runProxyQuery.delete("DELETE FROM " + tableName +"  WHERE ID = '" + id + "'");
	}
	
	@Override
	public void restoreOldVersion(String[] line, String operation){
		
		if(operation == "add")
			runProxyQuery.delete("DELETE FROM " + tableName + "  WHERE ID = '" + line[0] + "'");
		
		if(operation == "del")
			runProxyQuery.insert("INSERT INTO " + tableName + " VALUES(" + line[0] + ",'" + line[1] + "','" + line[2] + "')");		
	}
}
