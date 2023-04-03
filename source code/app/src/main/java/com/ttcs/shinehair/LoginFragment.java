package com.ttcs.shinehair;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.Class.Day;
import com.ttcs.shinehair.Class.Users;

import org.jetbrains.annotations.Nullable;

public class LoginFragment extends Fragment {

    private EditText mEmail;
    private EditText mPassword;
    private Button mLoginButton;
    public static CheckBox mRememberCheckBox;

    private DatabaseHelper mDatabaseHelper;
    private SharedPreferences mSharedPreferences;
    private FragmentManager fragmentManager;

    public  static String PERFS_NAME="dangnhap";
    private boolean mIsRemembered;
    public static final String KEY_EMAIL = "email";
    public static final String IS_LOGIN = "IsLoggedIn";


    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        mEmail = view.findViewById(R.id.edtsignIn_email);
        mPassword = view.findViewById(R.id.edtsignIn_pass);
        mLoginButton = view.findViewById(R.id.btnSignIn);
        mRememberCheckBox = view.findViewById(R.id.cbAcc);

        mDatabaseHelper = new DatabaseHelper(getActivity());
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PERFS_NAME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        mDatabaseHelper = new DatabaseHelper(getActivity());
//        mDbHelper.deleteAll();
        if (mDatabaseHelper.isTableUserEmpty()) {
            mDatabaseHelper.insertUsers( new Users( "admin", "admin", 1 ) );
            mDatabaseHelper.insertUsers( new Users( "staff", "staff", 2 ) );
            mDatabaseHelper.insertUsers( new Users( "staff2", "staff", 2 ) );
            mDatabaseHelper.insertUsers( new Users( "staff3", "staff", 2 ) );
            mDatabaseHelper.insertUsers( new Users( "staff4", "staff", 2 ) );
        }
        if(mDatabaseHelper.isTableEmpty()){
            boolean insertData2 = mDatabaseHelper.insertDichVu( "Dịch vụ 1", "59", " quá trình cắt tóc, nhân viên sẽ sử dụng các kỹ thuật cắt tóc chuyên nghiệp để đảm bảo rằng kiểu tóc được cắt đều và đẹp. Sau khi hoàn thành việc cắt tóc, nhân viên sẽ sử dụng các sản phẩm làm tóc như gel hoặc sáp để tạo kiểu tóc cho khách hàng. Nếu khách hàng muốn, nhân viên cũng có thể tư vấn về cách chăm sóc tóc và cung cấp các sản phẩm làm tóc để khách hàng có thể tự chăm sóc tóc tại nhà. Dịch vụ cắt tóc thường được cung cấp trong một không gian thoải mái và thoáng mát, với nhiều ghế cắt tóc và gương lớn để khách hàng có thể xem kiểu tóc của mình trong quá trình cắt. Ngoài ra, các tiệm tóc còn cung cấp các dịch vụ khác như nhuộm tóc, uốn tóc và chăm sóc tóc để khách hàng có thể tạo ra một phong cách tóc hoàn hảo.", null);
            boolean insertData3 = mDatabaseHelper.insertDichVu( "Dịch vụ 2", "99", " quá trình cắt tóc, nhân viên sẽ sử dụng các kỹ thuật cắt tóc chuyên nghiệp để đảm bảo rằng kiểu tóc được cắt đều và đẹp. Sau khi hoàn thành việc cắt tóc, nhân viên sẽ sử dụng các sản phẩm làm tóc như gel hoặc sáp để tạo kiểu tóc cho khách hàng. Nếu khách hàng muốn, nhân viên cũng có thể tư vấn về cách chăm sóc tóc và cung cấp các sản phẩm làm tóc để khách hàng có thể tự chăm sóc tóc tại nhà. Dịch vụ cắt tóc thường được cung cấp trong một không gian thoải mái và thoáng mát, với nhiều ghế cắt tóc và gương lớn để khách hàng có thể xem kiểu tóc của mình trong quá trình cắt. Ngoài ra, các tiệm tóc còn cung cấp các dịch vụ khác như nhuộm tóc, uốn tóc và chăm sóc tóc để khách hàng có thể tạo ra một phong cách tóc hoàn hảo.", null );
            boolean insertData4 = mDatabaseHelper.insertDichVu( "Dịch vụ 3", "129", " quá trình cắt tóc, nhân viên sẽ sử dụng các kỹ thuật cắt tóc chuyên nghiệp để đảm bảo rằng kiểu tóc được cắt đều và đẹp. Sau khi hoàn thành việc cắt tóc, nhân viên sẽ sử dụng các sản phẩm làm tóc như gel hoặc sáp để tạo kiểu tóc cho khách hàng. Nếu khách hàng muốn, nhân viên cũng có thể tư vấn về cách chăm sóc tóc và cung cấp các sản phẩm làm tóc để khách hàng có thể tự chăm sóc tóc tại nhà. Dịch vụ cắt tóc thường được cung cấp trong một không gian thoải mái và thoáng mát, với nhiều ghế cắt tóc và gương lớn để khách hàng có thể xem kiểu tóc của mình trong quá trình cắt. Ngoài ra, các tiệm tóc còn cung cấp các dịch vụ khác như nhuộm tóc, uốn tóc và chăm sóc tóc để khách hàng có thể tạo ra một phong cách tóc hoàn hảo.", null );
            boolean insertData5 = mDatabaseHelper.insertDichVu( "Dịch vụ 4", "139", " quá trình cắt tóc, nhân viên sẽ sử dụng các kỹ thuật cắt tóc chuyên nghiệp để đảm bảo rằng kiểu tóc được cắt đều và đẹp. Sau khi hoàn thành việc cắt tóc, nhân viên sẽ sử dụng các sản phẩm làm tóc như gel hoặc sáp để tạo kiểu tóc cho khách hàng. Nếu khách hàng muốn, nhân viên cũng có thể tư vấn về cách chăm sóc tóc và cung cấp các sản phẩm làm tóc để khách hàng có thể tự chăm sóc tóc tại nhà. Dịch vụ cắt tóc thường được cung cấp trong một không gian thoải mái và thoáng mát, với nhiều ghế cắt tóc và gương lớn để khách hàng có thể xem kiểu tóc của mình trong quá trình cắt. Ngoài ra, các tiệm tóc còn cung cấp các dịch vụ khác như nhuộm tóc, uốn tóc và chăm sóc tóc để khách hàng có thể tạo ra một phong cách tóc hoàn hảo.", null );
            boolean insertData6 = mDatabaseHelper.insertDichVu( "Dịch vụ 5", "159", " quá trình cắt tóc, nhân viên sẽ sử dụng các kỹ thuật cắt tóc chuyên nghiệp để đảm bảo rằng kiểu tóc được cắt đều và đẹp. Sau khi hoàn thành việc cắt tóc, nhân viên sẽ sử dụng các sản phẩm làm tóc như gel hoặc sáp để tạo kiểu tóc cho khách hàng. Nếu khách hàng muốn, nhân viên cũng có thể tư vấn về cách chăm sóc tóc và cung cấp các sản phẩm làm tóc để khách hàng có thể tự chăm sóc tóc tại nhà. Dịch vụ cắt tóc thường được cung cấp trong một không gian thoải mái và thoáng mát, với nhiều ghế cắt tóc và gương lớn để khách hàng có thể xem kiểu tóc của mình trong quá trình cắt. Ngoài ra, các tiệm tóc còn cung cấp các dịch vụ khác như nhuộm tóc, uốn tóc và chăm sóc tóc để khách hàng có thể tạo ra một phong cách tóc hoàn hảo.", null );
            boolean insertData7 = mDatabaseHelper.insertDichVu( "Dịch vụ 6", "199", " quá trình cắt tóc, nhân viên sẽ sử dụng các kỹ thuật cắt tóc chuyên nghiệp để đảm bảo rằng kiểu tóc được cắt đều và đẹp. Sau khi hoàn thành việc cắt tóc, nhân viên sẽ sử dụng các sản phẩm làm tóc như gel hoặc sáp để tạo kiểu tóc cho khách hàng. Nếu khách hàng muốn, nhân viên cũng có thể tư vấn về cách chăm sóc tóc và cung cấp các sản phẩm làm tóc để khách hàng có thể tự chăm sóc tóc tại nhà. Dịch vụ cắt tóc thường được cung cấp trong một không gian thoải mái và thoáng mát, với nhiều ghế cắt tóc và gương lớn để khách hàng có thể xem kiểu tóc của mình trong quá trình cắt. Ngoài ra, các tiệm tóc còn cung cấp các dịch vụ khác như nhuộm tóc, uốn tóc và chăm sóc tóc để khách hàng có thể tạo ra một phong cách tóc hoàn hảo.", null );
            boolean insertData8 = mDatabaseHelper.insertDichVu( "Dịch vụ 7", "299", " quá trình cắt tóc, nhân viên sẽ sử dụng các kỹ thuật cắt tóc chuyên nghiệp để đảm bảo rằng kiểu tóc được cắt đều và đẹp. Sau khi hoàn thành việc cắt tóc, nhân viên sẽ sử dụng các sản phẩm làm tóc như gel hoặc sáp để tạo kiểu tóc cho khách hàng. Nếu khách hàng muốn, nhân viên cũng có thể tư vấn về cách chăm sóc tóc và cung cấp các sản phẩm làm tóc để khách hàng có thể tự chăm sóc tóc tại nhà. Dịch vụ cắt tóc thường được cung cấp trong một không gian thoải mái và thoáng mát, với nhiều ghế cắt tóc và gương lớn để khách hàng có thể xem kiểu tóc của mình trong quá trình cắt. Ngoài ra, các tiệm tóc còn cung cấp các dịch vụ khác như nhuộm tóc, uốn tóc và chăm sóc tóc để khách hàng có thể tạo ra một phong cách tóc hoàn hảo.", null );
            boolean insertData9 = mDatabaseHelper.insertDichVu( "Dịch vụ 8", "399", " quá trình cắt tóc, nhân viên sẽ sử dụng các kỹ thuật cắt tóc chuyên nghiệp để đảm bảo rằng kiểu tóc được cắt đều và đẹp. Sau khi hoàn thành việc cắt tóc, nhân viên sẽ sử dụng các sản phẩm làm tóc như gel hoặc sáp để tạo kiểu tóc cho khách hàng. Nếu khách hàng muốn, nhân viên cũng có thể tư vấn về cách chăm sóc tóc và cung cấp các sản phẩm làm tóc để khách hàng có thể tự chăm sóc tóc tại nhà. Dịch vụ cắt tóc thường được cung cấp trong một không gian thoải mái và thoáng mát, với nhiều ghế cắt tóc và gương lớn để khách hàng có thể xem kiểu tóc của mình trong quá trình cắt. Ngoài ra, các tiệm tóc còn cung cấp các dịch vụ khác như nhuộm tóc, uốn tóc và chăm sóc tóc để khách hàng có thể tạo ra một phong cách tóc hoàn hảo.", null );
            boolean insertData10 = mDatabaseHelper.insertDichVu( "Dịch vụ 9", "499", " quá trình cắt tóc, nhân viên sẽ sử dụng các kỹ thuật cắt tóc chuyên nghiệp để đảm bảo rằng kiểu tóc được cắt đều và đẹp. Sau khi hoàn thành việc cắt tóc, nhân viên sẽ sử dụng các sản phẩm làm tóc như gel hoặc sáp để tạo kiểu tóc cho khách hàng. Nếu khách hàng muốn, nhân viên cũng có thể tư vấn về cách chăm sóc tóc và cung cấp các sản phẩm làm tóc để khách hàng có thể tự chăm sóc tóc tại nhà. Dịch vụ cắt tóc thường được cung cấp trong một không gian thoải mái và thoáng mát, với nhiều ghế cắt tóc và gương lớn để khách hàng có thể xem kiểu tóc của mình trong quá trình cắt. Ngoài ra, các tiệm tóc còn cung cấp các dịch vụ khác như nhuộm tóc, uốn tóc và chăm sóc tóc để khách hàng có thể tạo ra một phong cách tóc hoàn hảo.", null );
        }
        if (mDatabaseHelper.isTableInfoEmpty()) {
            mDatabaseHelper.updateInfo( "staff", "Nguyễn Văn A", "123456", "Thái Nguyên", null );
            mDatabaseHelper.updateInfo( "staff2", "Nguyễn Văn B", "123456", "Thái Nguyên", null );
            mDatabaseHelper.updateInfo( "staff3", "Nguyễn Văn C", "123456", "Thái Nguyên", null );
            mDatabaseHelper.updateInfo( "staff4", "Nguyễn Văn D", "123456", "Thái Nguyên", null );
            mDatabaseHelper.updateInfo( "admin", "TK admin", "123456", "Thái Nguyên", null );
        }
        if (mDatabaseHelper.isTableDayEmpty()){
            boolean ThuHai = mDatabaseHelper.insertDay( new Day("Thứ hai","9:00", "17:00",0));
            boolean ThuBa = mDatabaseHelper.insertDay( new Day("Thứ ba","9:00", "17:00",0) );
            boolean ThuTu = mDatabaseHelper.insertDay( new Day("Thứ tư","9:00", "17:00",0) );
            boolean ThuNam = mDatabaseHelper.insertDay( new Day("Thứ năm","9:00", "17:00",0) );
            boolean ThuSau = mDatabaseHelper.insertDay( new Day("Thứ sáu","00:00", "00:00",1) );
            boolean thuBay = mDatabaseHelper.insertDay( new Day("Thứ bảy","00:00", "00:00",1) );
            boolean ChuNhat = mDatabaseHelper.insertDay( new Day("Chủ nhật","9:00", "17:00",0) );
        }
//        mDbHelper = new DatabaseHelper(getActivity());
////        mDbHelper.deleteAll();
//
//        mDbHelper.insertUsers(new Users("admin", "admin", 1));
//        mDbHelper.insertUsers(new Users("staff", "staff", 2));
//        mDbHelper.insertUsers(new Users("staff2", "staff", 2));
//        mDbHelper.insertUsers(new Users("staff3", "staff", 2));
//        mDbHelper.insertUsers(new Users("staff4", "staff", 2));
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                // Kiểm tra tên đăng nhập và mật khẩu có hợp lệ hay không
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    if (TextUtils.isEmpty(email)) {
                        mEmail.setError("Vui lòng nhập tên đăng nhập");
                    }
                    if (TextUtils.isEmpty(password)) {
                        mPassword.setError("Vui lòng nhập mật khẩu");
                    }
                    return;
                }

                // Kiểm tra tên đăng nhập và mật khẩu có khớp với dữ liệu trong cơ sở dữ liệu hay không
                if (mDatabaseHelper.checkEmailPassword(email, password)) {

                    // Kiểm tra xem checkbox "Nhớ tài khoản" có được đánh dấu hay không
                    boolean isRemembered = mRememberCheckBox.isChecked();
                    // Lưu trữ tên đăng nhập và mật khẩu vào SharedPreferences
                    editor.putString("username", email);
                    editor.putString("password", password);
                    editor.apply();
                    if (isRemembered) {
                        // Lấy giá trị được lưu trữ trong SharedPreferences
                        mIsRemembered = mSharedPreferences.getBoolean("dangnhap", true);

                        if (mIsRemembered) {
                            // Lấy tên đăng nhập và mật khẩu được lưu trữ trong SharedPreferences
                            String usernameRm = mSharedPreferences.getString("username", "");
                            String passwordRm = mSharedPreferences.getString("password", "");
                            // Điền tên đăng nhập và mật khẩu vào EditText tương ứng
                            mEmail.setText(usernameRm);
                            mPassword.setText(passwordRm);

                            editor.putBoolean("dangnhap", true);
                            editor.apply();
                            startActivity(new Intent(getActivity(), MainActivity.class));
                            getActivity().finish();
                        } else {
                        // Xóa dữ liệu được lưu trữ trong SharedPreferences
//                            editor.putBoolean("dangxuat", false);
//                            editor.clear();
//                            editor.apply();

                    }


        }

                    // Đăng nhập thành công, chuyển sang màn hình chính
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    // Đóng màn hình đăng nhập
                    getActivity().finish();


                    Toast.makeText(getActivity(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
        return view;
    }
}