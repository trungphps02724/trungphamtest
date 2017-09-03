package Databasehelper;

import android.R.string;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
	private static String DATA_NAME = "Manager";
	private static int DATA_VERSION = 1;

	public static String TABLE_LOGIN = "Logins";
	public static String ID_LOGIN = "Id";
	public static String USER_LOGIN = "User";
	public static String PASS_LOGIN = "Pass";

	public static String TABLE_STUDENT = "SinhVien";
	public static String ID_STUDENT = "Masv";
	public static String NAME_STUDENT = "Tensv";
	public static String GENDER_STUDENT = "Gioitinh";
	public static String PHONE_STUDENT = "Phone";
	public static String EMAIL_STUDENT = "Email";
	public static String DIEM_STUDENT = "Diem";
	public static String XEPLOAI_STUDENT = "Xeploai";

	public DbHelper(Context context) {
		super(context, DATA_NAME, null, DATA_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String Create_Login = "CREATE TABLE " 
				+ TABLE_LOGIN 
				+ "(" 
				+ ID_LOGIN 
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ USER_LOGIN 
				+ " TEXT," 
				+ PASS_LOGIN 
				+ " TEXT" 
				+ ")";
		String Create_Students = "CREATE TABLE " 
				+ TABLE_STUDENT 
				+ "(" 
				+ ID_STUDENT
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " 
				+ NAME_STUDENT 
				+ " TEXT," 
				+ GENDER_STUDENT 
				+ " TEXT,"
				+ PHONE_STUDENT 
				+ " TEXT," 
				+ EMAIL_STUDENT 
				+ " TEXT," 
				+ DIEM_STUDENT 
				+ " DECIMA," 
				+ XEPLOAI_STUDENT
				+ " TEXT" 
				+ ")";
		db.execSQL(Create_Login);
		db.execSQL(Create_Students);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("Drop table if exists " + TABLE_LOGIN);
		db.execSQL("Drop table if exists " + TABLE_STUDENT);
		onCreate(db);

	}

}
