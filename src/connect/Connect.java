package connect;
import java.sql.*;
public final class Connect {
	
	private final String USERNAME = "root"; 
	private final String PASSWORD = ""; 
	private final String DATABASE = "moware"; 
	private final String HOST = "localhost:3306"; 
	private final String CONECTION = String.format("jdbc:mysql://%s/%s", HOST, DATABASE);
	
	public ResultSet rs;
	public ResultSetMetaData rsm;
	private Connection con;
	private Statement st;
	private static Connect connect;
	
	/**
	* Constructor for Connect class
	* <br>
	* This class is used singleton design pattern, so this class only have one instance
	*/
    private Connect() {
    	try {  
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(CONECTION, USERNAME, PASSWORD);  
            st = con.createStatement(); 
        } catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("Failed to connect the database, the system is terminated!");
        	System.exit(0);
        }  
    }
    
	/**
	* This method is used for get instance from Connect class
	* @return Connect This returns instance from Connect class
	*/
    public static synchronized Connect getConnection() {
		/**
		* If the connect is null then:
		*   - Create the instance from Connect class
		*   - Otherwise, just assign the previous instance of this class
		*/
		return connect = (connect == null) ? new Connect() : connect;
    }

    /**
	* This method is used for SELECT SQL statements.
	* @param String This is the query statement
	* @return ResultSet This returns result data from the database
	*/
    public ResultSet executeQuery(String query) {
        rs = null;
    	try {
            rs = st.executeQuery(query);
            rsm = rs.getMetaData();
        } catch(Exception e) {
        	e.printStackTrace();
        }
        return rs;
    }

	/**
	* This method is used for INSERT, UPDATE, or DELETE SQL statements.
	* @param String This is the query statement
	*/
    public void executeUpdate(String query) {
    	try {
			st.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
	/**
	* This method is used for SELECT, INSERT, UPDATE, or DELETE SQL statements using prepare statement.
	* @param String This is the query statement
	*/
    public PreparedStatement prepareStatement(String query) {
    	PreparedStatement ps = null;
    	try {
			ps = con.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return ps;
    }
}
