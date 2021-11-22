package to.msn.wings.healthapplication;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.BreakIterator;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


public class MainActivity extends AppCompatActivity {

    //プリファレンス
    private SharedPreferences preference;
    private SharedPreferences.Editor editor;
    private BreakIterator editTextLoginAccount;
    private BreakIterator editTextLoginPass;
    private Button main_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //プリファレンスの準備
        preference = getSharedPreferences("Preference Name", MODE_PRIVATE);
        editor = preference.edit();

        if (preference.getBoolean("Launched", false)==false) {
            //初回起動時の処理
            //ログイン成功時処理

            // ログインIDとログインパスワードのビューオブジェクトを取得
            EditText editTextLoginAccount = (EditText)findViewById(R.id.editTextLoginAccount);
            EditText editTextLoginPass = (EditText)findViewById(R.id.editTextLoginPass);

            Button main_button = (Button)findViewById(R.id.main_button);

            // 「pref_data」という設定データファイルを読み込み
            SharedPreferences prefData = getSharedPreferences("pref_data", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefData.edit();

            // パスワードは暗号化
            SecretKeySpec keySpec = new SecretKeySpec("abcdefg098765432".getBytes(), "AES"); // キーファイル生成
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encrypted = cipher.doFinal(editTextLoginPass.toString().getBytes()); // byte配列を暗号化
            String up = Base64.encodeToString(encrypted, Base64.DEFAULT); // Stringにエンコード

            // 入力されたログインIDとログインパスワード
            editor.putString("account", editTextLoginAccount.getText().toString());
            editor.putString("pass", up);

            // 保存
            editor.commit();

            //プリファレンスの書き変え
            editor.putBoolean("Launched", true);
            editor.commit();

            // 画面遷移
            main_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(), initial_screenFragment.class);
                    startActivity(intent);
                }
            });


        } else {
            //二回目以降の処理
            // ログイン画面表示処理

            // 「pref_data」という設定データファイルを読み込み
            SharedPreferences prefData = getSharedPreferences("pref_data", MODE_PRIVATE);
            String account = prefData.getString("account", "");
            String pass = prefData.getString("pass", "");

            // パスワード複合化処理
            String up = "";
            SecretKeySpec keySpec = new SecretKeySpec("abcdefg098765432".getBytes(), "AES"); // キーファイル生成 暗号化で使った文字列と同様にする
            Cipher cipher = null;
            try {
                cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, keySpec);
                byte[] decByte = Base64.decode(pass, Base64.DEFAULT); // byte配列にデコード
                byte[] decrypted = cipher.doFinal(decByte); // 複合化
                up = new String(decrypted); // Stringに変換
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                e.printStackTrace();
            } catch (InvalidKeyException e) {
                e.printStackTrace();
            } catch (BadPaddingException e) {
                e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                e.printStackTrace();
            }

            // 空チェック
            if (account != null && account.length() > 0) {
                // 保存済の情報をログインID欄に設定
                this.editTextLoginAccount.setText(account);
            }
            if (up != null && up.length() > 0) {
                // 保存済の情報をログインパスワード欄に設定
                this.editTextLoginPass.setText(up);
            }

            // 画面遷移
            main_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplication(), initial_screenFragment.class);
                    startActivity(intent);
                }
            });
        }

    }
}