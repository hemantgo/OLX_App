package app1.olx.com.olx_app1.custom;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import app1.olx.com.olx_app1.Objects.PostDO;

/**
 * Created by IBM_ADMIN on 9/26/2015.
 */
public class DataBasehelper extends SQLiteOpenHelper {

	// database version
	private static final int database_VERSION = 1;
	// database name
	private static final String database_NAME = "postADs";
	private static final String TABLE_POST = "post";
	private static final String POST_ID = "id";
	private static final String POST_TITLE = "title";
	private static final String POST_DESCRIPTION = "description";
	private static final String POST_CATEGORY = "category";
	private static final String POST_LOCATION = "location";
	private static final String POST_NAME = "name";
	private static final String POST_EMAIL = "email";
	private static final String POST_PHONE_NUMBER = "phone_number";


	private static final String[] COLUMNS = { POST_ID, POST_TITLE, POST_DESCRIPTION, POST_CATEGORY, POST_LOCATION, POST_NAME,
			POST_EMAIL, POST_PHONE_NUMBER};

	public DataBasehelper(Context context) {
		super(context, database_NAME, null, database_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// SQL statement to create book table
		String CREATE_BOOK_TABLE = "CREATE TABLE "+TABLE_POST+" ( " + " "+POST_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, " + ""+POST_TITLE+" TEXT, "+POST_DESCRIPTION+" TEXT" +
				", "+POST_CATEGORY+" TEXT, "+POST_LOCATION+" TEXT, "+POST_NAME+" TEXT , "+POST_EMAIL+" TEXT, "+POST_PHONE_NUMBER+" TEXT)";
		db.execSQL(CREATE_BOOK_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// drop books table if already exists
		db.execSQL("DROP TABLE IF EXISTS "+TABLE_POST+"");
		this.onCreate(db);
	}

	public void postAD(PostDO postDO) {
		// get reference of the BookDB database
		SQLiteDatabase db = this.getWritableDatabase();

		// make values to be inserted
		ContentValues values = new ContentValues();
		values.put(POST_TITLE, postDO.title);
		values.put(POST_DESCRIPTION, postDO.description);
		values.put(POST_CATEGORY, postDO.category);
		values.put(POST_LOCATION, postDO.location);
		values.put(POST_NAME, postDO.name);
		values.put(POST_EMAIL, postDO.email);
		values.put(POST_PHONE_NUMBER, postDO.phone_number);


		// insert book
		db.insert(TABLE_POST, null, values);

		// close database transaction
		db.close();
	}

//	public Book readBook(int id) {
//		// get reference of the BookDB database
//		SQLiteDatabase db = this.getReadableDatabase();
//
//		// get book query
//		Cursor cursor = db.query(table_BOOKS, // a. table
//				COLUMNS, " id = ?", new String[] { String.valueOf(id) }, null, null, null, null);
//
//		// if results !=null, parse the first one
//		if (cursor != null)
//			cursor.moveToFirst();
//
//		Book book = new Book();
//		book.setId(Integer.parseInt(cursor.getString(0)));
//		book.setTitle(cursor.getString(1));
//		book.setAuthor(cursor.getString(2));
//
//		return book;
//	}

//	public List getAllBooks() {
//		List books = new LinkedList();
//
//		// select book query
//		String query = "SELECT  * FROM " + table_BOOKS;
//
//		// get reference of the BookDB database
//		SQLiteDatabase db = this.getWritableDatabase();
//		Cursor cursor = db.rawQuery(query, null);
//
//		// parse all results
//		Book book = null;
//		if (cursor.moveToFirst()) {
//			do {
//				book = new Book();
//				book.setId(Integer.parseInt(cursor.getString(0)));
//				book.setTitle(cursor.getString(1));
//				book.setAuthor(cursor.getString(2));
//
//				// Add book to books
//				books.add(book);
//			} while (cursor.moveToNext());
//		}
//		return books;
//	}

//	public int updateBook(Book book) {
//
//		// get reference of the BookDB database
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		// make values to be inserted
//		ContentValues values = new ContentValues();
//		values.put("title", book.getTitle()); // get title
//		values.put("author", book.getAuthor()); // get author
//
//		// update
//		int i = db.update(table_BOOKS, values, book_ID + " = ?", new String[] { String.valueOf(book.getId()) });
//
//		db.close();
//		return i;
//	}
//
//	// Deleting single book
//	public void deleteBook(Book book) {
//
//		// get reference of the BookDB database
//		SQLiteDatabase db = this.getWritableDatabase();
//
//		// delete book
//		db.delete(table_BOOKS, book_ID + " = ?", new String[] { String.valueOf(book.getId()) });
//		db.close();
//	}
}