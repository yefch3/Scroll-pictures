package cn.studyou.autoscrollbanner;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.content.pm.ActivityInfo;
import java.io.File;

import android.widget.EditText;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.Activity;
import android.widget.Toast;


import cn.studyou.library.view.BannerLayout;



public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);   //横屏锁定
        setContentView(R.layout.activity_main);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);   //应用运行时，保持屏幕高亮，不锁屏
        getPermissions();  //动态获取权限

//        List<String> urls = new ArrayList<>();
//        urls.add("file:///storage/emulated/0/starbucks/p1.jpg");
//        urls.add("file:///storage/emulated/0/starbucks/p2.jpg");
//        urls.add("file:///storage/emulated/0/starbucks/p3.jpg");
//        urls.add("file:///storage/emulated/0/starbucks/p4.jpg");
//        urls.add("file:///storage/emulated/0/starbucks/p5.jpg");

//        List<Integer> viewRes = new ArrayList<Integer>() ;
//        viewRes.add(R.drawable.p1);
//        viewRes.add(R.drawable.p2);
//        viewRes.add(R.drawable.p3);
//        viewRes.add(R.drawable.p4);
//        viewRes.add(R.drawable.p5);
//        viewRes.add(R.drawable.p6);
//        viewRes.add(R.drawable.p7);
//        viewRes.add(R.drawable.p8);

        // 获取指定目录下的图片
        List <ImageView> viewRes = obtain_pictures("/storage/emulated/0/starbucks/");

        BannerLayout banner = (BannerLayout) findViewById(R.id.banner);
        //网络地址
//        banner.setViewUrls(urls);
        //本地资源
        banner.setViewRes(viewRes);
        //添加点击监听
        banner.setOnBannerItemClickListener(new BannerLayout.OnBannerItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                Toast.makeText(MainActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });

        Log.d("MainActivity", "OnCreate execute");
    }


    // 动态申请权限函数
    private void getPermissions() {
        String[] permissionLists = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ApplyForPermission.applyPermission(MainActivity.this, permissionLists, 1);
    }

    // 判断文件是否是图片的函数
    private boolean checkIsImageFile(String fName) {
        boolean isImageFile = false;
        // 获取扩展名
        String FileEnd = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase();
        if (FileEnd.equals("jpg") || FileEnd.equals("png") || FileEnd.equals("jpeg")) {
            isImageFile = true;
        } else {
            isImageFile = false;
        }
        return isImageFile;
    }

    // 获取指定目录下的图片,并存在ImageView中
    private List<ImageView> obtain_pictures(String filename) {
        List<ImageView> viewRes = new ArrayList<ImageView>();
        File folder = new File(filename);
        File[] listOfFiles = folder.listFiles();

        // 按照文件的名称排序
        List fileList = Arrays.asList(listOfFiles);
        Collections.sort(fileList, new Comparator<File>() {   // 对文件按照名称进行排序
            @Override
            public int compare(File o1, File o2) {
                if (o1.isDirectory() && o2.isFile())
                    return -1;
                if (o1.isFile() && o2.isDirectory())
                    return 1;
                return o1.getName().compareTo(o2.getName());
            }
        });

        // 遍历文件，如果是图片就转化为ImageView存储在list中
        int no_image_flag = 1;  // 判断文件夹内有无图片的参数，为1则没有图片
        for (File file : listOfFiles) {
            if (file.isFile() && checkIsImageFile(file.getName())) {
                no_image_flag = 0;
//                Bitmap bm = new Bitmap();
                // 获取图片的长宽，分辨率太大的图片会被压缩。
                int Width_Height[] = getImageWidthHeight(filename);
                if ((Width_Height[0]>Width_Height[1]?Width_Height[0]:Width_Height[1]) > 4000 || (Width_Height[0]<Width_Height[1]?Width_Height[0]:Width_Height[1]) > 2500){
                    BitmapFactory.Options op1 = new BitmapFactory.Options();
                    op1.inSampleSize = 4;  // 将图片长宽分别改变为1/4
                    Bitmap bm = BitmapFactory.decodeFile(filename + file.getName(), op1);
                    ImageView img = new ImageView(this);
                    img.setImageBitmap(bm);
                    viewRes.add(img);
                }
                else {
                    Bitmap bm = BitmapFactory.decodeFile(filename + file.getName());
                    ImageView img = new ImageView(this);
                    img.setImageBitmap(bm);
                    viewRes.add(img);
                }
            }
        }
        // 如果没有图片，则dialog弹窗显示
        if (no_image_flag == 1) {
            finish();
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        return viewRes;
    }

    public static int[] getImageWidthHeight(String path){
        BitmapFactory.Options options = new BitmapFactory.Options();
        /**
         * 最关键在此，把options.inJustDecodeBounds = true;
         * 这里再decodeFile()，返回的bitmap为空，但此时调用options.outHeight时，已经包含了图片的高了
         */
        options.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(path, options); // 此时返回的bitmap为null
        /**
         *options.outHeight为原始图片的高
         */
        return new int[]{options.outWidth,options.outHeight};
    }

}
