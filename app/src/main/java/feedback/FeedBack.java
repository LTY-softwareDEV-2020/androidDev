/**
 * 用户反馈Activity
 */
package feedback;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.LightingShare.R;


public class FeedBack extends Activity {

    /**
     * 反馈提交的按钮
     */
    private Button feedback_submit_bt;
    //private TextView feedback_name_tv,feedback_email_tv,feedback_suggestion_tv;
    /**
     * 反馈信息的三个EditText控件
     */
    private EditText feedback_name_et, feedback_email_et, feedback_suggestion_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_main);

        /**对控件进行定义*/
        feedback_submit_bt = (Button) findViewById(R.id.feedback_submit_bt);
        feedback_name_et = (EditText) findViewById(R.id.feedback_name_et);
        feedback_email_et = (EditText) findViewById(R.id.feedback_email_et);
        feedback_suggestion_et = (EditText) findViewById(R.id.feedback_suggestion_et);

        feedback_submit_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = feedback_name_et.getText().toString().trim();
                String useremail = feedback_email_et.getText().toString().trim();
                String usersuggestion = feedback_suggestion_et.getText().toString();
                //直接告诉用户反馈成功了  但是其实并没有反馈
                {
                    //当前有可用网络,判断usersuggestion字符串是否为空
                    if (TextUtils.isEmpty(usersuggestion)) {
                        Toast.makeText(FeedBack.this, "建议栏不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        //向数据库中写入数据
                        Toast.makeText(FeedBack.this, "提交成功，感谢你的反馈", Toast.LENGTH_SHORT).show();
                        finish();//结束当前Activity自动跳转到上一个Activity
                    }
                }


            }
        });


    }


}
