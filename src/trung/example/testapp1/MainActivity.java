package trung.example.testapp1;

import Databasehelper.DataDAO;
import Databasehelper.DbHelper;
import StudentsDTO.Logins;
import StudentsDTO.Students;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	DataDAO db;
	final Context context = this;
	EditText edtUer, edtPass;
	Button btnRegis, btnLog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		db = new DataDAO(this);

		edtUer = (EditText) findViewById(R.id.edtUserName);
		edtPass = (EditText) findViewById(R.id.edtPassWord);
		btnLog = (Button) findViewById(R.id.btnLogin);
		btnRegis = (Button) findViewById(R.id.btnRegister);
		btnLog.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String user, pass;
				user = edtUer.getText().toString();
				pass = edtPass.getText().toString();
				if (user.trim().equals("") || pass.trim().equals("")) {
					Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
					return;
				}
				if (db.Logins(user, pass)) {
					Intent intent = new Intent(MainActivity.this, ListStudentActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(getApplicationContext(), "Tài khoản hoặc mật khẩu không đúng", Toast.LENGTH_SHORT)
							.show();
				}

			}
		});
		btnRegis.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				final Dialog dal = new Dialog(context);
				dal.setTitle("Đăng ký tài khoản");
				dal.setContentView(R.layout.dialog_register);
				dal.show();
				final EditText edtDKUser = (EditText) dal.findViewById(R.id.edtDKUser);
				final EditText edtDKPass = (EditText) dal.findViewById(R.id.edtDKPass);
				final Button btnDK = (Button) dal.findViewById(R.id.btnDangKy);

				btnDK.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View v) {
						String tk, mk;
						tk = edtDKUser.getText().toString();
						mk = edtDKPass.getText().toString();
						if (tk.trim().equals("") || mk.trim().equals("")) {
							Toast.makeText(getApplicationContext(), "Không được để trống", Toast.LENGTH_SHORT).show();
							return;
						} else if (tk.trim().length() < 6 || tk.trim().length() > 12) {
							Toast.makeText(getApplicationContext(), "Tài khoản phải từ 6 - 12 kí tự",
									Toast.LENGTH_SHORT).show();
							return;
						} else if (mk.trim().length() < 6 || mk.trim().length() > 15) {
							Toast.makeText(getApplicationContext(), "Mật khẩu phải từ 6 - 15 kí tự", Toast.LENGTH_SHORT)
									.show();
							return;
						}
						try {
							Logins login = new Logins();
							login.setUser(tk);
							login.setPass(mk);
							db.insertLogins(login);
							Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
							dal.dismiss();
						} catch (Exception e) {
							Toast.makeText(getApplicationContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
						}

					}
				});

			}
		});

	}

}
