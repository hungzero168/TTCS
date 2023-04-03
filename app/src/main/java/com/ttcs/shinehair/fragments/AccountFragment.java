package com.ttcs.shinehair.fragments;

import static com.ttcs.shinehair.LoginFragment.PERFS_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ttcs.shinehair.Activity.ChangePassActivity;
import com.ttcs.shinehair.Activity.EditProfileActivity;
import com.ttcs.shinehair.Activity.InfoUserActivity;
import com.ttcs.shinehair.Activity.LichSuDatActivity;
import com.ttcs.shinehair.Class.DatabaseHelper;
import com.ttcs.shinehair.MainActivity2;
import com.ttcs.shinehair.R;
import com.ttcs.shinehair.admin.HomeAdminActivity;
import com.ttcs.shinehair.staff.StaffHomeActivity;

public class AccountFragment extends Fragment {
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference mDatabase;
    private Button btnEdit;
    LinearLayout lnAd,ln_nhanVien, ln_admin;
    private TextView txtLogout, txtEmail, txtname,txtChangePass;
    TextView txtTT, txtAdmin, txtStaff;
    DatabaseHelper databaseHelper;
    ImageView img;

    private TextView txtLichSu;
    int ad;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        txtLogout = view.findViewById(R.id.txtLogout);
        txtEmail = view.findViewById(R.id.txtEmail);
        txtname = view.findViewById(R.id.txtname);
        btnEdit = view.findViewById(R.id.btnEdit);
        txtChangePass = view.findViewById(R.id.txtChangePass);
        txtTT = view.findViewById(R.id.txtTT);
        txtAdmin = view.findViewById(R.id.txtAdmin);
        txtStaff = view.findViewById(R.id.txtStaff);
        lnAd = view.findViewById(R.id.bgad);
        ln_admin = view.findViewById(R.id.ln_admin);
        ln_nhanVien = view.findViewById(R.id.ln_nhanVien);
        img = view.findViewById( R.id.img );
        txtLichSu = view.findViewById( R.id.txtLichSu );



        // lấy tài khoản từ Preference
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("dangnhap", 0);
        DatabaseHelper databaseHelper;
        databaseHelper = new DatabaseHelper(getActivity());
        String email = sharedPreferences.getString( "username", "");
        String pass = sharedPreferences.getString( "pass"," " );
        int chucVu = databaseHelper.getUserType( email );


        byte[] hinhanh = databaseHelper.getHinhAnh(email);
        if (hinhanh != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);
            img.setImageBitmap(bitmap);
        }
        boolean kt = databaseHelper.checkEmailPassword(email,pass);

        String name = databaseHelper.getName(email);
        if (name != null){
            txtname.setText(name);
        }
        txtEmail.setText(email);
//        if (kt){
            if (chucVu == 1) {
                ln_admin.setVisibility(View.VISIBLE);
            }
            if (chucVu == 2 ) {
                ln_nhanVien.setVisibility(View.VISIBLE);
            }
//        }
        txtAdmin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getActivity(), HomeAdminActivity.class ) );
            }
        } );
        txtStaff.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getActivity(), StaffHomeActivity.class ) );
            }
        } );
        txtLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // xóa thông tin shared preferences
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences(PERFS_NAME, 0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();

                Intent intent = new Intent(getActivity(), MainActivity2.class);
                startActivity(intent);

                getActivity().finish();
            }
        });
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });
        txtChangePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePassActivity.class);
                startActivity(intent);
            }
        });
        txtTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //, InfoUserFragment.class);
                Intent intent = new Intent(getActivity(), InfoUserActivity.class);
                startActivity(intent);
                    // chuyen fragment
//                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,
//                        new InfoUserFragment()).commit();
            }
        });

        txtLichSu.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(getActivity(), LichSuDatActivity.class ) );
            }
        } );



        return view;
    }

}