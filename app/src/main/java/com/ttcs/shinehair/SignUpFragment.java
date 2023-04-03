package com.ttcs.shinehair;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.ttcs.shinehair.Class.DatabaseHelper;


public class SignUpFragment extends Fragment {

    private EditText mEmail;
    private EditText mPassword;
    private EditText mPassword2;
    private Button mRegisterButton;

    private DatabaseHelper mDbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        mEmail = view.findViewById(R.id.edtsignUp_email);
        mPassword = view.findViewById(R.id.edtsignUp_pass);
        mPassword2 = view.findViewById(R.id.edtsignUp_pass2);
        mRegisterButton = view.findViewById(R.id.btnSignUp);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Lỗi đăng ký: Tên đăng nhập đã tồn tại hoặc mật khẩu không trùng khớp.")
                .setTitle("Đăng ký thất bại")
                .setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                String password2 = mPassword2.getText().toString();


                // kiểm tra editText có rỗng không
                if (email.equals("") || password.equals("") || password2.equals("")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Vui lòng nhập đầy đủ thông tin.")
                            .setTitle("Đăng ký thất bại")
                            .setPositiveButton("OK", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return;
                }
                // kiểm tra email có khoảng trắng không
                if (email.contains(" ")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Tên đăng nhập không được chứa khoảng trắng.")
                            .setTitle("Đăng ký thất bại")
                            .setPositiveButton("OK", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return;
                }

                // kiểm tra password có nhỏ hơn 6 kí tự không
                if (password.length() < 6) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Mật khẩu phải có ít nhất 6 kí tự.")
                            .setTitle("Đăng ký thất bại")
                            .setPositiveButton("OK", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                    return;
                }

                mDbHelper = new DatabaseHelper(getActivity());
                if (mDbHelper.checkEmail(email)) {
//                     Tên đăng nhập đã tồn tại, hiển thị thông báo lỗi
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("Tên đăng nhập đã tồn tại.")
                            .setTitle("Đăng ký thất bại")
                            .setPositiveButton("OK", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
//                    mEmail.setError( "Trùng tài khoản email" );


                } else {
                    // kiểm tra password và password2 có trùng nhau không
                    if (!password.equals(password2)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setMessage("Mật khẩu không trùng khớp.")
                                .setTitle("Đăng ký thất bại")
                                .setPositiveButton("OK", null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        return;
                    } else {
                        // thêm tài khoản vào database
                        mDbHelper.insertUsers(email, password, 0);
                        if (mDbHelper.checkEmail(email)) {
                            // Tên đăng nhập đã tồn tại, hiển thị thông báo lỗi
                            Toast.makeText(getActivity(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();

                            // set lại editText
                            mEmail.setText("");
                            mPassword.setText("");
                            mPassword2.setText("");
                            
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            builder.setMessage("Đăng ký thất bại.")
                                    .setTitle("Đăng ký thất bại")
                                    .setPositiveButton("OK", null);
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    }
                }

            }
        });
        return view;
    }
}

