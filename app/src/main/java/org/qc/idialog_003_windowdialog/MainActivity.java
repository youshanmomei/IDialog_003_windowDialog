package org.qc.idialog_003_windowdialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btn_main;
    private View customView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_main = (Button) findViewById(R.id.btn_main);
        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = myBuilder(MainActivity.this);
                final AlertDialog dialog = builder.show();
                dialog.setCanceledOnTouchOutside(false);

                Button btn_sendemail = (Button) customView.findViewById(R.id.btn_sendemail);
                btn_sendemail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "send email", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Intent.ACTION_SEND);
                        intent.setType("message/rfc822");
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"1234567@163.com"});
                        intent.putExtra(Intent.EXTRA_SUBJECT, "windows style dialog");
                        intent.putExtra(Intent.EXTRA_TEXT, "content");
                        startActivity(Intent.createChooser(intent, "choose application"));
                        dialog.dismiss();
                    }
                });

                Button btn_web = (Button) customView.findViewById(R.id.btn_web);
                btn_web.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "go to web", Toast.LENGTH_SHORT).show();
                        Uri uri = Uri.parse("http://www.baidu.com");
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));
                        dialog.dismiss();
                    }
                });

                ImageButton ib_cus_imgcancel = (ImageButton) customView.findViewById(R.id.ib_cus_imgcancel);
                ib_cus_imgcancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }

    protected AlertDialog.Builder myBuilder(Activity activity) {
        LayoutInflater inflater = this.getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        customView = inflater.inflate(R.layout.customview, null);

        return builder.setView(customView);
    }
}
