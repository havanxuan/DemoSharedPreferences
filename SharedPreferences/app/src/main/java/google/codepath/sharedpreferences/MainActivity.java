package google.codepath.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etUser,etPass;
    CheckBox checkBox;
    String fileName = "data.share";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUser = findViewById(R.id.etUser);
        etPass = findViewById(R.id.etPass);
        checkBox = findViewById(R.id.checkBox);
    }

    public void login(View view) {
        // tạo đối tượng getSharedPreferences với chỉ định một tệp Tùy chọn
        SharedPreferences sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE);
        // tạo đối tượng SharedPreferences  với sử dụng 1 tệp tùy chọn Mặc định (k khuyến khích dùng)
        //SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        // tạo đối tượng Editor để lưu thay đổi
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String user = etUser.getText().toString();
        String pass = etPass.getText().toString();
        boolean checked = checkBox.isChecked();
        if (!TextUtils.isEmpty(user) && !TextUtils.isEmpty(pass) && pass.length() >=6){
            Toast.makeText(this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
            // nếu người dùng check k check vào ô checkbox
            if (!checked){
                editor.clear();// xóa mọi dữ liệu trước đó
            }else {
                // click vào ô checkbox
                editor.putString("user",user);
                editor.putString("pass",pass);
                editor.putBoolean("checked",true);// lưu trạng thái của checkbox
            }
            // lưu xuống file
            editor.commit();
            Intent intent = new Intent(this,SecondActivity.class);
            intent.putExtra("user",user);
            intent.putExtra("pass",pass);
            intent.putExtra("checked",checked);
            startActivity(intent);
        }else {
            Toast.makeText(this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
        }
    }

    // load giá trị
    public void loadFile(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences(fileName,MODE_PRIVATE);
        boolean checked = sharedPreferences.getBoolean("checked",false);
        String user = sharedPreferences.getString("user","");
        String pass = sharedPreferences.getString("pass","");
        etUser.setText(user);
        etPass.setText(pass);
        checkBox.setChecked(checked);
    }
}