package trung.example.testapp1;

import java.util.List;

import Databasehelper.DataDAO;
import StudentsDTO.Students;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DetailStudentsActivity extends Activity {
	TextView txtId,txtTen,txtGT,txtPHone,txtEmail,txtDiem,txtXL;
	DataDAO db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.layout_detail_sv);
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		txtTen = (TextView)findViewById(R.id.txtHTName);
		txtGT = (TextView)findViewById(R.id.txtHTGender);
		txtPHone = (TextView)findViewById(R.id.txtHTPhone);
		txtEmail = (TextView)findViewById(R.id.txtHTEmailSV);
		txtDiem = (TextView)findViewById(R.id.txtHTDiem);
		txtXL = (TextView)findViewById(R.id.txtHTXepLoai);
		txtId = (TextView) findViewById(R.id.txtHTMaSV);
		
		db = new DataDAO(this);
		int id = getIntent().getExtras().getInt("SV");		
		Students students = new Students();
		students = db.loadDetailStudents(id);
		
		txtId.setText(String.valueOf(students.getIdsv()));
		txtTen.setText(students.getName().toString());
		txtGT.setText(students.getGender().toString());
		txtPHone.setText(students.getPhone().toString());
		txtEmail.setText(students.getEmail().toString());
		txtDiem.setText(String.valueOf(students.getPoints()));
		txtXL.setText(students.getXeploai().toString());
		
	}
}
