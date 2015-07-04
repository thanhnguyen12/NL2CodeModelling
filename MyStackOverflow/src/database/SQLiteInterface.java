/**
 * 
 */
package database;

import java.io.File;
import java.util.ArrayList;

import utils.Logger;

import com.almworks.sqlite4java.SQLite;
import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

import config.Config;

/**
 * @author anhnt
 *
 */
public class SQLiteInterface {

	private SQLiteConnection _db;

	public static void main(String[] args) {
		SQLiteInterface sqLiteInterface = new SQLiteInterface(Config.pathToDBLibs, Config.pathToDatabase);
		long questionId = 10370731l;
		 ArrayList<Long> allAnswers = sqLiteInterface.getAllAnswerIdxs(questionId);
		 Logger.log("allAnswers: " + allAnswers);
		PostRowData postRowData = sqLiteInterface.selectSinglePost(questionId);
		 Logger.log("postRowData: " + postRowData);

		sqLiteInterface.dispose();

	}
	public SQLiteInterface(){
		this(Config.pathToDBLibs, Config.pathToDatabase);
	}

	public SQLiteInterface(String pathToDBLibs,String pathToDatabase) {
		SQLite.setLibraryPath(pathToDBLibs);
		_db = new SQLiteConnection(new File(pathToDatabase));
		try {
			_db.open(true);

		} catch (SQLiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void dispose(){
		_db.dispose();
	}


	//	const column_spec posts_columns[] =
	//		{{"Id",             CT_INT},
	//		 {"PostTypeId",     CT_INT},
	//		 {"AcceptedAnswerId", CT_INT},
	//		 {"ParentId",       CT_INT},
	//		 {"CreationDate",   CT_DATE},
	//		 {"Score",          CT_INT},
	//		 {"ViewCount",      CT_INT},
	//		 {"Body",           CT_TEXT},
	//		 {"OwnerUserId",    CT_INT},
	//		 {"OwnerDisplayName",    CT_TEXT},
	//		 {"LastEditorUserId", CT_INT},
	//		 {"LastEditorDisplayName", CT_VCHR64},
	//		 {"LastEditDate",   CT_DATE},
	//		 {"LastActivityDate", CT_DATE},
	//		 {"Title",          CT_TEXT},
	//		 {"Tags",           CT_TEXT},
	//		 {"AnswerCount",    CT_INT},
	//		 {"CommentCount",   CT_INT},
	//		 {"FavoriteCount",  CT_INT},
	//		 {"ClosedDate",     CT_DATE},
	//		 {"CommunityOwnedDate", CT_DATE},
	//		 {0}};
	//
	//const table_spec posts_table = {"posts", posts_columns};
	
	public ArrayList<Long> getAllAnswerIdxs(long questionId){
		
		ArrayList<Long> allAnswerIdxs = new ArrayList<Long>();
		String query = "SELECT Id FROM posts where ParentId = " + questionId ;
		try {
			SQLiteStatement st = _db.prepare(query);
			
			 while (st.step())  {
				 
				 long tmp = st.columnLong(0);		
				 allAnswerIdxs.add(tmp);
			 }
		} catch (SQLiteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allAnswerIdxs;
	}
	
	public UserRowData getSingleUser(long userId){
		try {
			String query = "SELECT * FROM users where Id = " + userId ;
			SQLiteStatement st = _db.prepare(query);
		
			if(st.step()){
				UserRowData userData = new UserRowData(
						st.columnLong(0), 
						st.columnLong(1), 
						st.columnString(2), 
						st.columnString(3), 
						st.columnString(4), 
						st.columnString(5),
						st.columnString(6),
						st.columnString(7), 
						st.columnString(8), 
						st.columnLong(9),
						st.columnLong(10), 
						st.columnLong(11), 
						st.columnString(12),
						st.columnLong(13), 
						st.columnLong(14));
				return userData;
			}
			return null;

		}
		catch (SQLiteException e) {
			e.printStackTrace();
		}	
		return null;
	}

	public PostRowData selectSinglePost(long postId)  { 
		
		try {
			String query = "SELECT * FROM posts where Id = " + postId ;
			SQLiteStatement st = _db.prepare(query);
		
			if(st.step()){
		
			PostRowData postData = new PostRowData
					(
							st.columnLong(0), 
							st.columnLong(1),
							st.columnLong(2), 
							st.columnLong(3), 
							st.columnString(4),
							st.columnLong(5), 
							st.columnLong(6),
							st.columnString(7), 
							st.columnLong(8), 
							st.columnString(9), 
							st.columnLong(10), 
							st.columnString(11), 
							st.columnString(12), 
							st.columnString(13), 
							st.columnString(14), 
							st.columnString(15), 
							st.columnLong(16),
							st.columnLong(17),
							st.columnLong(18),
							st.columnString(19), 
							st.columnString(20) 
							);


			return postData;
			}
			return null;
		} catch (SQLiteException e) {
			e.printStackTrace();
		}
		return null;
	}
}
