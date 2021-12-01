package to.msn.wings.healthapplication;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import javax.mail.*;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.AddressException;

import androidx.appcompat.app.AppCompatActivity;



public class SettingHelp extends AppCompatActivity {

    private Button mSetting_help_button1;
    private Button mSetting_help_button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_main_screen_height);

        mSetting_help_button1 = (Button) findViewById(R.id.setting_help_button1);
        mSetting_help_button2 = (Button) findViewById(R.id.setting_help_button2);


        // 画面遷移 前に戻る
        mSetting_help_button1.setOnClickListener(v -> {
            Intent intent_mib = new Intent(getApplication(), SettingMain.class);
            startActivity(intent_mib);
        });


        findViewById(R.id.setting_help_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText ed = (EditText)findViewById(R.id.setting_help_txt);
                String tex = ed.getText().toString(); //EditTextで入力したテキストを文字列として取得する
                asyncTask a = new asyncTask();
                a.execute((Object)tex,(Object)"gmailアカウント名",(Object)"gmailパスワード",new Object()) ;
            }
        });

    }


    private class asyncTask extends android.os.AsyncTask{

        protected String text;
        protected String account;
        protected String password;

        @Override
        protected Object doInBackground(Object... obj){
            text=(String)obj[0];
            account=(String)obj[1];
            password=(String)obj[2];

            java.util.Properties properties = new java.util.Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.socketFactory.post", "465");
            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            final javax.mail.Message msg = new javax.mail.internet.MimeMessage(javax.mail.Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
                @Override
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new javax.mail.PasswordAuthentication(account,password);
                }
            }));

            try {
                msg.setFrom(new javax.mail.internet.InternetAddress(account + "@gmail.com"));
                //自分自身にメールを送信
                msg.setRecipients(javax.mail.Message.RecipientType.TO, javax.mail.internet.InternetAddress.parse(account + "@gmail.com"));
                msg.setSubject("お問い合わせ");
                msg.setText(text);

                javax.mail.Transport.send(msg);

            } catch (Exception e) {
                return (Object)e.toString();
            }

            return (Object)"OK";

        }
        @Override
        protected void onPostExecute(Object obj) {
            //画面にメッセージを表示する
            android.content.Context context = getApplicationContext();
            android.widget.Toast t=android.widget.Toast.makeText(context,(String)obj, android.widget.Toast.LENGTH_LONG);
            t.show();
        }
    }
}


