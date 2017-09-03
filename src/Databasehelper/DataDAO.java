package Databasehelper;

import java.util.ArrayList;
import java.util.List;

import StudentsDTO.Logins;
import StudentsDTO.Students;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DataDAO {
	Context context;
	DbHelper dbhelper;
	SQLiteDatabase db;

	public DataDAO(Context context) {
		this.context = context;
		dbhelper = new DbHelper(context);
	}

	public void insertLogins(Logins login) {
		db = dbhelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DbHelper.USER_LOGIN, login.getUser());
		values.put(DbHelper.PASS_LOGIN, login.getPass());
		db.insert(DbHelper.TABLE_LOGIN, null, values);
		db.close();
	}

	public Boolean Logins(String user, String pass) {
		db = dbhelper.getWritableDatabase();
		String sql = "Select * from " + DbHelper.TABLE_LOGIN + " where " + DbHelper.USER_LOGIN + " = '" + user
				+ "' and " + DbHelper.PASS_LOGIN + " = '" + pass + "'";
		Cursor c = db.rawQuery(sql, null);
		if (c.moveToFirst()) {
			return true;
		}
		return false;
	}

	public List<Students> loadStudents() {
		List<Students> list = new ArrayList<Students>();
		db = dbhelper.getWritableDatabase();
		String sql = "Select * from " + DbHelper.TABLE_STUDENT;
		Cursor c = db.rawQuery(sql, null);
		c.moveToFirst();
		while (!c.isAfterLast()) {
			Students student = new Students();
			student.setIdsv(Integer.parseInt(c.getString(c.getColumnIndex(DbHelper.ID_STUDENT))));
			student.setName(c.getString(c.getColumnIndex(DbHelper.NAME_STUDENT)));
			student.setGender(c.getString(c.getColumnIndex(DbHelper.GENDER_STUDENT)));
			student.setPhone(c.getString(c.getColumnIndex(DbHelper.PHONE_STUDENT)));
			student.setEmail(c.getString(c.getColumnIndex(DbHelper.EMAIL_STUDENT)));
			student.setPoints(Double.parseDouble(c.getString(c.getColumnIndex(DbHelper.DIEM_STUDENT))));
			student.setXeploai(c.getString(c.getColumnIndex(DbHelper.XEPLOAI_STUDENT)));
			list.add(student);
			c.moveToNext();
		}
		return list;

	}

	public List<Students> searchStudents(String query) {
		List<Students> list = new ArrayList<Students>();
		db = dbhelper.getWritableDatabase();
		String sql = "Select * from " + DbHelper.TABLE_STUDENT + " where " + DbHelper.NAME_STUDENT + " like '%" + query
				+ "%'";
		Cursor c = db.rawQuery(sql, null);
		c.moveToFirst();
		while (!c.isAfterLast()) {
			Students student = new Students();
			student.setIdsv(Integer.parseInt(c.getString(c.getColumnIndex(DbHelper.ID_STUDENT))));
			student.setName(c.getString(c.getColumnIndex(DbHelper.NAME_STUDENT)));
			student.setGender(c.getString(c.getColumnIndex(DbHelper.GENDER_STUDENT)));
			student.setPhone(c.getString(c.getColumnIndex(DbHelper.PHONE_STUDENT)));
			student.setEmail(c.getString(c.getColumnIndex(DbHelper.EMAIL_STUDENT)));
			student.setPoints(Double.parseDouble(c.getString(c.getColumnIndex(DbHelper.DIEM_STUDENT))));
			student.setXeploai(c.getString(c.getColumnIndex(DbHelper.XEPLOAI_STUDENT)));
			list.add(student);
			c.moveToNext();
		}
		return list;

	}

	public Students loadDetailStudents(int id) {
		db = dbhelper.getWritableDatabase();
		String sql = "Select * from " + DbHelper.TABLE_STUDENT + " where " + DbHelper.ID_STUDENT + " = " + id;
		Cursor c = db.rawQuery(sql, null);
		c.moveToFirst();
		Students student = new Students();
		while (!c.isAfterLast()) {
			student.setIdsv(Integer.parseInt(c.getString(c.getColumnIndex(DbHelper.ID_STUDENT))));
			student.setName(c.getString(c.getColumnIndex(DbHelper.NAME_STUDENT)));
			student.setGender(c.getString(c.getColumnIndex(DbHelper.GENDER_STUDENT)));
			student.setPhone(c.getString(c.getColumnIndex(DbHelper.PHONE_STUDENT)));
			student.setEmail(c.getString(c.getColumnIndex(DbHelper.EMAIL_STUDENT)));
			student.setPoints(Double.parseDouble(c.getString(c.getColumnIndex(DbHelper.DIEM_STUDENT))));
			student.setXeploai(c.getString(c.getColumnIndex(DbHelper.XEPLOAI_STUDENT)));
			c.moveToNext();
		}
		return student;

	}

	public void insertStudents(Students student) {
		db = dbhelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DbHelper.NAME_STUDENT, student.getName());
		values.put(DbHelper.GENDER_STUDENT, student.getGender());
		values.put(DbHelper.PHONE_STUDENT, student.getPhone());
		values.put(DbHelper.EMAIL_STUDENT, student.getEmail());
		values.put(DbHelper.DIEM_STUDENT, student.getPoints());
		values.put(DbHelper.XEPLOAI_STUDENT, student.getXeploai());
		db.insert(DbHelper.TABLE_STUDENT, null, values);
		db.close();

	}

	public int deleteStudents(int id) {
		db = dbhelper.getWritableDatabase();
		return db.delete(DbHelper.TABLE_STUDENT, DbHelper.ID_STUDENT + "=?", new String[] { String.valueOf(id) });
	}

	public int updateStudents(Students student) {
		db = dbhelper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(DbHelper.NAME_STUDENT, student.getName());
		values.put(DbHelper.GENDER_STUDENT, student.getGender());
		values.put(DbHelper.PHONE_STUDENT, student.getPhone());
		values.put(DbHelper.EMAIL_STUDENT, student.getEmail());
		values.put(DbHelper.DIEM_STUDENT, student.getPoints());
		values.put(DbHelper.XEPLOAI_STUDENT, student.getXeploai());
		return db.update(DbHelper.TABLE_STUDENT, values, DbHelper.ID_STUDENT + "=?",
				new String[] { String.valueOf(student.getIdsv()) });

	}
}
