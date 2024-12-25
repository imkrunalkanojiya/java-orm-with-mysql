package orm.db;

public class QueryBuilder {

	private StringBuilder query;
	
	public QueryBuilder() {
		this.query = new StringBuilder();
	}
	
	public QueryBuilder select(String columns) {
		query.append("SELECT ").append(columns).append(" ");
		return this;
	}
	
	public QueryBuilder from(String tableName) {
        query.append("FROM ").append(tableName).append(" ");
        return this;
    }

    public QueryBuilder where(String condition) {
        query.append("WHERE ").append(condition).append(" ");
        return this;
    }

    public QueryBuilder insertInto(String tableName, String columns) {
        query.append("INSERT INTO ").append(tableName).append(" (").append(columns).append(") ");
        return this;
    }

    public QueryBuilder values(String values) {
        query.append("VALUES (").append(values).append(") ");
        return this;
    }

    public QueryBuilder update(String tableName) {
        query.append("UPDATE ").append(tableName).append(" ");
        return this;
    }

    public QueryBuilder set(String setStatement) {
        query.append("SET ").append(setStatement).append(" ");
        return this;
    }

    public QueryBuilder deleteFrom(String tableName) {
        query.append("DELETE FROM ").append(tableName).append(" ");
        return this;
    }
    
    public String build() {
    	return query.toString().trim() + ";";
    }
	
}
