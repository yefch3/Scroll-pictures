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


import cn.studyou.library.view.BannerLayout;


public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button button1 = (Button) findViewById(R.id.cancel_action);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }

//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder( this )
////                .setIcon( R.drawable.apple)
//                .setTitle( "确认对话框" )
//                .setMessage( "你确定要退出？" )
//                .setNegativeButton( "取消",null )
//                .setPositiveButton( "确定", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        // finish();
//                        System.exit( 0 );
//                    }
//                } )
//                .show();
//    }
//    public void showdialog(View view) {
//        //定义一个新的对话框对象
//        AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
//        //设置对话框提示内容
//        alertdialogbuilder.setMessage("确定要退出程序吗？");
//        //定义对话框2个按钮标题及接受事件的函数
//        alertdialogbuilder.setPositiveButton("确定",click1);
//        alertdialogbuilder.setNegativeButton("取消",click2);
//        //创建并显示对话框
//        AlertDialog alertdialog1=alertdialogbuilder.create();
//        alertdialog1.show();
//    }
//
//    private DialogInterface.OnClickListener click1=new DialogInterface.OnClickListener() {
//        //使用该标记是为了增强程序在编译时候的检查，如果该方法并不是一个覆盖父类的方法，在编译时编译器就会报告错误。
//        @Override
//        public void onClick(DialogInterface arg0,int arg1) {
//            //当按钮click1被按下时执行结束进程
//            android.os.Process.killProcess(android.os.Process.myPid());
//        }
//    };
//    private DialogInterface.OnClickListener click2=new DialogInterface.OnClickListener() {
//        @Override
//        public void onClick(DialogInterface arg0,int arg1) {
//            //当按钮click2被按下时则取消操作
//            arg0.cancel();
//        }
//    };
}

