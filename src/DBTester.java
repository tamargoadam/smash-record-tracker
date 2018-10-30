import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTester {

	public static void main(String[] args) {
		
		DataBase testDB = new DataBase();
		ResultSet rs;

		try {
			rs = testDB.displayPlayers();
			while(rs.next()){
				//System.out.println(rs.getString("tag"));
			}
			
			System.out.println(" ");
			
			rs = testDB.displayCharacters();
			while(rs.next()){
				//System.out.println(rs.getString("name"));
			}
			
			rs = testDB.displayRecord();
			while(rs.next()){
				System.out.println(rs.getString("tag1") + " vs " + rs.getString("tag2"));
				System.out.println(rs.getString("name1") + " vs " + rs.getString("name2") + "\n");
			}
			testDB.dropPlayerTable();
			testDB.dropCharacterTable();
			testDB.dropRecordTable();
			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		

	}

}
