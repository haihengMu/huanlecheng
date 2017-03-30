package activity.huanlecheng;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * Created by Administrator on 2016/12/7.
 */

public class ActivityMap extends BaseActivity {

    private Button title_left_btn;
    private ImageView iv_image;
    private TextView tv_text;
    private DisplayImageOptions options;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        title_left_btn = (Button) findViewById(R.id.title_left_btn);
        iv_image = (ImageView) findViewById(R.id.iv_image);
        tv_text = (TextView) findViewById(R.id.tv_text);

        title_left_btn.setBackgroundResource(R.drawable.aar);
        title_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        String img = getIntent().getStringExtra("img");
        String msg = getIntent().getStringExtra("msg");
        tv_text.setText(msg);
        initView(img);
    }

    private void initView(String img) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray;
            bitmapArray = Base64.decode(img, Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        iv_image.setImageBitmap(bitmap);
    }
}
