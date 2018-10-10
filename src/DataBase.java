import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBase {

	private static Connection con;
	public boolean hasData = false;
	
	private void initialize() throws SQLException {
		
		if(!hasData){
			
			hasData = true;
			Statement state = con.createStatement();
			
			//Check that player table exists
			ResultSet res = state.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table' AND name = 'player';");
			if(!res.next()){
				System.out.println("Building player table...");
				Statement state2 = con.createStatement();
				state2.execute("CREATE TABLE player(tag TEXT PRIMARY KEY);");
			}
			
			//Check that character table exists
			res = state.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table' AND name = 'character';");
			if(!res.next()){
				System.out.println("Building character table...");
				Statement state2 = con.createStatement();
				state2.execute("CREATE TABLE character(tag TEXT, name TEXT, is_main BOOLEAN,"
						+ "PRIMARY KEY(tag, name), FOREIGN KEY(tag) REFERENCES player(tag));");
			}
			
			//Check that record table exists
			res = state.executeQuery("SELECT name FROM sqlite_master WHERE type = 'table' AND name = 'record';");
			if(!res.next()){
				System.out.println("Building record table...");
				Statement state2 = con.createStatement();
				state2.execute("CREATE TABLE record(tag1 TEXT, name1 TEXT, tag2 TEXT, name2 TEXT,"
						+ " wins INTEGER, losses INTEGER,"
						+ "PRIMARY KEY(tag1, name1, tag2, name2),"
						+ "FOREIGN KEY(tag1, name1, tag2, name2) REFERENCES character(tag, name, tag, name));");
			}
			
			
		}
	}	
	
	private void getConnection() throws ClassNotFoundException, SQLException {
		
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:SmashSQL.db");
		initialize();
	}
	
	public void addPlayer(String tag){
		
		try{
			if(con == null) getConnection();
			
			//add player with tag info
			PreparedStatement prep = con.prepareStatement("INSERT INTO player values(?);");
			prep.setString(1, tag);
			prep.execute();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addCharacter(String tag, String name, Boolean ismain) {
		
		try{
			if(con == null) getConnection();
			
			//add character with tag(fk), name, and ismain info
			PreparedStatement prep = con.prepareStatement("INSERT INTO character values(?,?,?);");
			prep.setString(1, tag);
			prep.setString(2, name);
			prep.setBoolean(3, ismain);
			prep.execute();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void addRecord(String tag1, String name1, String tag2, String name2, int wins, int losses) {

		try{
			if(con == null) getConnection();
			
			//add record with tag1(fk), name1(fk), tag2(fk), name2(fk), wins, and losses info
			PreparedStatement prep = con.prepareStatement("INSERT INTO record values(?,?,?,?,?,?);");
			prep.setString(1, tag1);
			prep.setString(2, name1);
			prep.setString(3, tag2);
			prep.setString(4, name2);
			prep.setInt(5, wins);
			prep.setInt(6, losses);
			prep.execute();
		}catch (ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ResultSet displayPlayers() {
		
		ResultSet res = null;
		try{
			if(con == null) getConnection();
			
			Statement state = con.createStatement();
			res = state.executeQuery("SELECT tag FROM player;");
			return res;
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return res;
	}
	
	public boolean hasTwoPlayers(){
		ResultSet rs = displayPlayers();
		try {
			rs.next();
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public ResultSet displayCharacters() {
		
		ResultSet res = null;	
		
		try {
			if(con == null) getConnection();
			
			Statement state = con.createStatement();
			res = state.executeQuery("SELECT name FROM character;");
			return res;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	public ResultSet displayCharactersByPlayer() {
		
		ResultSet res = null;	
		
		try {
			if(con == null) getConnection();
			
			Statement state = con.createStatement();
			res = state.executeQuery("SELECT character.name, player.tag FROM character LEFT JOIN player ON player.tag = character.tag;");
			return res;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	public String findMain(String myTag){
		
		ResultSet res = null;
		
		try {
			if(con == null) getConnection();
			
			Statement state = con.createStatement();
			res = state.executeQuery("SELECT player.tag, character.name FROM character LEFT OUTER JOIN player"
					+ " ON player.tag = character.tag WHERE character.is_main;");
			
			while(res.next()){
				if(res.getString("tag").equals(myTag)) return res.getString("name");
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "";
		
	}
	
	public Boolean playerExists(String tag) {
		
			try {
				
				if(con == null)
				getConnection();
				
				Statement state = con.createStatement();
				ResultSet res = state.executeQuery("SELECT tag FROM player;");
				while(res.next()){
					if(res.getString("tag").equals(tag)) return true;
				}
				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		return false;
	}
	
	public ResultSet displayRecord(){
		
		ResultSet res = null;	
		
		try {
			if(con == null) getConnection();
			
			Statement state = con.createStatement();
			res = state.executeQuery("SELECT tag1, name1, tag2, name2, wins, losses FROM record;");
			return res;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	public int getWins(String winner, String wChar, String looser, String lChar){
		//use displayRecord to find number of wins and if not found, create new record
		ResultSet rs = displayRecord();
		try{
			while(rs.next()){
				
				if(rs.getString("tag1").equals(winner) && rs.getString("tag2").equals(looser) &&
						rs.getString("name1").equals(wChar) && rs.getString("name2").equals(lChar)){
					return rs.getInt("wins");
				}
				if(rs.getString("tag2").equals(winner) && rs.getString("tag1").equals(looser) &&
						rs.getString("name2").equals(wChar) && rs.getString("name1").equals(lChar)){
					return rs.getInt("losses");
				}
				
			}
			addRecord(winner, wChar, looser, lChar, 0, 0);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public boolean recordExists(String winner, String wChar, String looser, String lChar){
		
		try {
			
			if(con == null)
			getConnection();
			
			ResultSet rs = displayRecord();
			while(rs.next()){
				if(rs.getString("tag1").equals(winner) && rs.getString("name1").equals(wChar) &&
						rs.getString("tag2").equals(looser) && rs.getString("name2").equals(lChar)) return true;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	return false;
	}
	
	public void addWin(String winner, String wChar, String looser, String lChar){
		//Add win it specified player
		try {
			
			if(con == null)
			getConnection();
			
			if(recordExists(winner, wChar, looser, lChar)){
				PreparedStatement ps = con.prepareStatement("UPDATE record SET wins = wins + 1 "
						+ "WHERE tag1 = ? AND name1 = ? AND tag2 = ? AND name2 = ?");
				ps.setString(1, winner);
				ps.setString(2, wChar);
				ps.setString(3, looser);
				ps.setString(4, lChar);
				ps.executeUpdate();
			}
			if(recordExists(looser, lChar, winner, wChar)){
				PreparedStatement ps = con.prepareStatement("UPDATE record SET losses = losses + 1 "
						+ "WHERE tag1 = ? AND name1 = ? AND tag2 = ? AND name2 = ?");
				ps.setString(1, looser);
				ps.setString(2, lChar);
				ps.setString(3, winner);
				ps.setString(4, wChar);
				ps.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void subtractWin(String winner, String wChar, String looser, String lChar){
		//Subtract win it specified player
		try {
			
			if(con == null)
			getConnection();
			
			if(recordExists(winner, wChar, looser, lChar) && getWins(winner, wChar, looser, lChar) > 0){
				PreparedStatement ps = con.prepareStatement("UPDATE record SET wins = wins - 1 "
						+ "WHERE tag1 = ? AND name1 = ? AND tag2 = ? AND name2 = ?");
				ps.setString(1, winner);
				ps.setString(2, wChar);
				ps.setString(3, looser);
				ps.setString(4, lChar);
				ps.executeUpdate();
			}
			if(recordExists(looser, lChar, winner, wChar) && getWins(winner, wChar, looser, lChar) > 0){
				PreparedStatement ps = con.prepareStatement("UPDATE record SET losses = losses - 1 "
						+ "WHERE tag1 = ? AND name1 = ? AND tag2 = ? AND name2 = ?");
				ps.setString(1, looser);
				ps.setString(2, lChar);
				ps.setString(3, winner);
				ps.setString(4, wChar);
				ps.executeUpdate();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void dropPlayerTable() {
	
		try {
			if(con != null){	
				con.close();
				getConnection();
				Statement state = con.createStatement();
				state.executeUpdate("DROP TABLE IF EXISTS'player';");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void dropCharacterTable() {
		try{
			if(con != null){
				con.close();
				getConnection();
				Statement state = con.createStatement();
				state.executeUpdate("DROP TABLE IF EXISTS'character';");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void dropRecordTable() {
		
		try{
			if(con != null){
				con.close();
				getConnection();
				Statement state = con.createStatement();
				state.executeUpdate("DROP TABLE IF EXISTS'record';");
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
