package com.avocarrot.json2view.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.apkfuns.logutils.LogUtils;
import com.arthas.yiew.Main;
import com.arthas.yiew.bean.Yiew;
import com.arthas.yiew.bean.YiewResp;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;

public class VirtualVNodeActivity extends AppCompatActivity {


    private VirtualVNodeActivity context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        context = this;


        load();
    }

    private void load() {
        OkGo.get("http://193.0.1.157:8080/virtual.json")     // 请求方式和请求url

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {

                        YiewResp yiew_1 = Util.gson.fromJson(s, YiewResp.class);

                        View contentView = Main.startProcess(context,  yiew_1 );
                        setContentView(contentView);


                    }
                });
    }


    public void refresh() {

        load();

    }

    public void changeSelf(Yiew yiew) {
        LogUtils.d("changeSelf");
        LogUtils.d("changeSelf"+yiew);
        TextView view = (TextView) yiew.getView();
        view.setText("asd");


        yiew.text = "azxczxczxc";
        yiew.invalid();
    }

    public void changeOther(Yiew yiew) {
        Yiew otherText = yiew.getYiewStore().getYiewByName("otherText");
        TextView view = otherText.getView();
        view.setText("124233434");
    }


}
