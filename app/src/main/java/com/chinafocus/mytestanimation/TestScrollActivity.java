package com.chinafocus.mytestanimation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;

import com.chinafocus.mytestanimation.ui.ParallaxListView;

public class TestScrollActivity extends AppCompatActivity {



    private static String[] names = {"功能1", "功能2", "功能3", "功能4", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5",
            "功能1", "功能2", "功能3", "功能4", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5",
            "功能1", "功能2", "功能3", "功能4", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5",
            "功能1", "功能2", "功能3", "功能4", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.test_list_view);

        ParallaxListView mlv_test = findViewById(R.id.lv_test_parallax);


        View inflate = View.inflate(this, R.layout.test_item_header_list_view, null);

        mlv_test.addHeaderView(inflate);

        mlv_test.setHeaderView(inflate);

        mlv_test.setAdapter(new ArrayAdapter<String>(this,R.layout.test_item_content_list_view,R.id.tv_item_content,names));


    }
}
