package trung.example.testapp1;

import java.util.ArrayList;
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

public class NewStudentsActivity extends Activity {
	DataDAO db;
	EditText edtTen, edtPhone, edtEmail, edtDiem;
	Button btnThem, btnHuy;
	List<Students> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.layout_add_sv);
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		db = new DataDAO(this);
		list = new ArrayList<Students>();
		list = db.loadStudents();
		
		edtTen = (EditText) findViewById(R.id.edtNewNameSV);
		edtPhone = (EditText) findViewById(R.id.edtNewPhoneSV);
		edtEmail = (EditText) findViewById(R.id.edtNewEmailSV);
		edtDiem = (EditText) findViewById(R.id.edtNewDiemSV);
		btnThem = (Button) findViewById(R.id.btnThem);
		btnHuy = (Button) findViewById(R.id.btnHuy);
		btnThem.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String ten, gioitinh, phone, email, strdiem, xeploai;
				double diem;
				RadioGroup radio = (RadioGroup) findViewById(R.id.radioAddGender);
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
					student.setName(ten);
					student.setGender(gioitinh);
					student.setPhone(phone);
					student.setEmail(email);
					student.setPoints(diem);
					student.setXeploai(xeploai);
					db.insertStudents(student);
					Toast.makeText(getApplicationContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(NewStudentsActivity.this, ListStudentActivity.class);
					startActivity(intent);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
				}
			}
		});
		btnHuy.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				
			}
		});
		
	}
	
}
