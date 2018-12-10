package com.chinafocus.mytestanimation;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookShelfMetaActivity extends AppCompatActivity {

    @BindView(R.id.iv_bg)
    ImageView mIv_bg;
    @BindView(R.id.rv_home)
    RecyclerView mRv_home;

    private static String[] names = {"功能1", "功能2", "功能3", "功能4", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5", "功能5"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bookshelf_activity_book_metadata_test);
        ButterKnife.bind(this);

        initBgTest01();
//        initBgTest02();
        initListView();

    }

    @SuppressLint("ResourceAsColor")
    private void initListView() {



        mRv_home.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter();
        mRv_home.setAdapter(myRecyclerViewAdapter);

        View view = new View(this);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, 900);
        view.setBackgroundColor(android.R.color.transparent);
        view.setLayoutParams(layoutParams);
        myRecyclerViewAdapter.setHeaderView(view);
//        mRv_home.addView(view, 0);

    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyHolder> {

        private View mHeaderView;

        public void setHeaderView(View headerView) {
            mHeaderView = headerView;
            notifyItemInserted(0);
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = View.inflate(getApplicationContext(), R.layout.bookshelf_listview_item_simple, null);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder myHolder, int i) {
            myHolder.mTextView.setText(names[i]);
        }

        @Override
        public int getItemCount() {
            return names.length;
        }

        class MyHolder extends RecyclerView.ViewHolder {

            TextView mTextView;

            MyHolder(@NonNull View itemView) {
                super(itemView);

                mTextView = itemView.findViewById(R.id.tv_item_home);
            }
        }
    }

    private void initBgTest02() {
        BitmapFactory.Options oFirst = new BitmapFactory.Options();
        oFirst.inSampleSize = 2;
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.metadata_bg, oFirst);
        mIv_bg.setImageBitmap(bitmap);
    }

    private void initBgTest01() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.metadata_bg);
//        BitmapFactory.Options oFirst = new BitmapFactory.Options();
//        oFirst.inJustDecodeBounds = true;

        int widthRaw = bitmap.getWidth();
        Log.i("MyLog", "widthRaw -- >" + widthRaw);
        int width = getWindowManager().getDefaultDisplay().getWidth();
        Log.i("MyLog", "width -- >" + width);
        float scale = (float) width / widthRaw;
        Log.i("MyLog", "scale -- >" + scale);
        /**
         * 需动态计算imageView高度
         */
        Bitmap blankBitmap = Bitmap.createBitmap(width, 900, bitmap.getConfig());

        Canvas canvas = new Canvas(blankBitmap);
        Matrix matrix = new Matrix();

        matrix.setScale(scale, scale);

        canvas.drawBitmap(bitmap, matrix, new Paint());

        mIv_bg.setImageBitmap(blankBitmap);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        int intrinsicWidth = mIv_bg.getDrawable().getIntrinsicWidth();
        Log.i("MyLog", "mIv_bg   intrinsicWidth -- >" + intrinsicWidth);

        int mIv_bgWidth = mIv_bg.getWidth();
        Log.i("MyLog", "mIv_bgWidth   -- >" + mIv_bgWidth);
    }
}
