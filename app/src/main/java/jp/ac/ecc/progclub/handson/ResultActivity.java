package jp.ac.ecc.progclub.handson;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static jp.ac.ecc.progclub.handson.countdown.CountDownActivity.COUNT_KEY;

public class ResultActivity extends AppCompatActivity {

    static final String save_resultName = "result";                   // 保存オブジェクト名
    static final String save_resultKey_name = "resultName";          // 入力した名前の保存キー名
    static final String save_resultKey_clickNum = "resultClickNum"; // 　クリック数の保存キー名

    TextView nameText;
    TextView countText;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        nameText = findViewById(R.id.NameEditText);    // 名前入力用テキストボックス
        countText = findViewById(R.id.NumTextView);    // カウント回数を表示したラベル
        nextBtn = findViewById(R.id.DecButton);        // [決定]ボタン

        // 前画面でクリックした数を取得
        final int count = getIntent().getIntExtra(COUNT_KEY,0);

        // カウントテキストにセット
        countText.setText(String.valueOf(count));

        // データ保存してランキング画面へ
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = countText.getText().toString();   // 名前

                // クリック数と名前を保存
                SharedPreferences sharedPreferences =
                        getSharedPreferences(save_resultName, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(save_resultKey_name, name);
                editor.putInt(save_resultKey_clickNum, count);
                editor.apply();
            }
        });
    }
}
