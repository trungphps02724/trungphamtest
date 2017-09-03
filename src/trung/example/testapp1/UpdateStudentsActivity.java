package trung.example.testapp1;

import java.util.List;

import Databasehelper.DataDAO;
import StudentsDTO.Students;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UpdateStudentsActivity extends Activity {
	DataDAO db;
	EditText edtTen, edtPhone, edtEmail, edtDiem;
	Button btnSua, btnThoat;
	List<Students> list;
	int id;
	Students students;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.layout_update_sv);
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		edtTen = (EditText) findViewById(R.id.edtSuaNameSV);
		edtPhone = (EditText) findViewById(R.id.edtSuaPhoneSV);
		edtEmail = (EditText) findViewById(R.id.edtSuaEmailSV);
		edtDiem = (EditText) findViewById(R.id.edtSuaDiemSV);
		btnSua = (Button) findViewById(R.id.btnUpdate);
		btnThoat = (Button) findViewById(R.id.btnThoat);
		
		Intent intent = getIntent();
		id = intent.getExtras().getInt("SV");
		this.setResult(RESULT_OK);
		db = new DataDAO(this);
		students = new Students();
		students = db.loadDetailStudents(id);
		
		edtTen.setText(students.getName().toString());
		edtPhone.setText(students.getPhone().toString());
		edtEmail.setText(students.getEmail().toString());
		edtDiem.setText(String.valueOf(students.getPoints()));
		
		
		btnSua.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Update();
				Intent intent = new Intent(UpdateStudentsActivity.this, ListStudentActivity.class);
				startActivity(intent);
				
			}
		});
		btnThoat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
	}
	public void Update() {
		String ten, gioitinh, phone, email, strdiem, xeploai;
		double diem;
		RadioGroup radio = (RadioGroup) findViewById(R.id.radioSuaGender);
		int rdcheck = radio.getCheckedRadioButtonId();
		RadioButton rbt = (RadioButton) findViewById(rdcheck);
		gioitinh = rbt.getText().toString();
		ten = edtTen.getText().toString();
		phone = edtPhone.getText().toString();
		email = edtEmail.getText().toString();
		strdiem = edtDiem.getText().toString();
		diem = Double.parseDouble(strdiem.trim());
		if(diem >=0 && diem < 5){
			xeploai = "Yếu";
		}else if (diem >=5 && diem < 7) {
			xeploai = "Trung bình";
		}else if (diem >=7 && diem < 9) {
			xeploai = "Khá";
		}else {
			xeploai = "Giỏi";
		}
		try {
			Students student = new Students();
			student.setIdsv(id);
			student.setName(ten);
			student.setGender(gioitinh);
			student.setPhone(phone);
			student.setEmail(email);
			student.setPoints(diem);
			student.setXeploai(xeploai);
			if(db.updateStudents(student) != -1){
				Toast.makeText(getApplicationContext(), "Cập nhật thành công", Toast.LENGTH_SHORT).show();
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
		}
	}
}
