package com.example.exam09_dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button Btn_toast;
    View dialogView;
    TextView Tv_name, Tv_mail;
    EditText Edit_name, Edit_mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Btn_toast = (Button)findViewById(R.id.btn_toast);
        Btn_toast.setOnClickListener(mOnClick);
        Tv_name = (TextView)findViewById(R.id.tv_name);
        Tv_mail = (TextView)findViewById(R.id.tv_mail);
        registerForContextMenu(Tv_mail);
        registerForContextMenu(Tv_name);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater mInflater = getMenuInflater();
        if(v == Tv_mail) {
            menu.setHeaderTitle("Background ");
            menu.setHeaderIcon(R.mipmap.clear);
            mInflater.inflate(R.menu.menu1, menu);
        }
        if(v == Tv_name) {
            menu.setHeaderTitle("Background ");
            menu.setHeaderIcon(R.mipmap.clear);
            mInflater.inflate(R.menu.menu1, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_red :
                Tv_mail.setBackgroundColor(getResources().getColor(R.color.colorRed)); break;
            case R.id.item_blue :
                Tv_mail.setBackgroundColor(getResources().getColor(R.color.colorBlue)); break;
            case R.id.item_green :
                Tv_mail.setBackgroundColor(getResources().getColor(R.color.colorGreen)); break;
        }
        return false;
    }

    View.OnClickListener mOnClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            dialogView = (View)View.inflate(MainActivity.this, R.layout.dialog, null);

            /*
                final String strVerList[] = new String[] {"롤리팝", "마시멜로", "누가"};
                final boolean[] checkArr = new boolean[] {true, false, false};
            */
            AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
            dlg.setTitle("사용자 정보 입력");
           // dlg.setMessage("내용~~~");
            dlg.setIcon(R.mipmap.clear);
            dlg.setView(dialogView);
            /*
            //라디오 버튼
                dlg.setSingleChoiceItems(strVerList, 0, new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Btn_toast.setText(strVerList[which]);
                    }
                });
            */
            /*
            //체크 박스

                dlg.setMultiChoiceItems(strVerList, checkArr, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        Btn_toast.setText(strVerList[which]);
                    }
                });
            */
            dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Edit_name = (EditText)dialogView.findViewById(R.id.edit_name);
                    Edit_mail = (EditText)dialogView.findViewById(R.id.edit_mail);

                    Tv_name.setText(Edit_name.getText());
                    Tv_mail.setText(Edit_mail.getText());
                    //Toast.makeText(MainActivity.this, "확인", Toast.LENGTH_SHORT).show();
                }
             });
            dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "취소", Toast.LENGTH_SHORT).show();

                }
            });
            dlg.show();


        }
    };
}
