package com.mobitant.bestfood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.mobitant.bestfood.fragments.BestFoodRegisterInputFragment;
import com.mobitant.bestfood.item.FoodInfoItem;
import com.mobitant.bestfood.lib.GoLib;
import com.mobitant.bestfood.lib.MyLog;

/**
 * 맛집 등록 액티비티이다.
 * 액티비티의 기본적인 화면 구성을 하며 실제 사용자 화면은 프래그먼트로 구성한다.
 */
public class BestFoodRegisterActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();
    public static FoodInfoItem currentItem = null;
    int from;
    Context context;

    /**
     * BestFoodRegisterLocationFragment를 실행하기 위한 기본적인 정보를 설정하고
     * 프래그먼트를 실행한다.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bestfood_register);

        context = this;
        from = (int) getIntent().getExtras().getInt("from");
        MyLog.d("프롬값!!!!!!!!!!!!!!!!!!!!!!!" + from);
        int memberSeq = ((MyApp)getApplication()).getMemberSeq();
        //BestFoodRegisterLocationFragment로 넘길 기본적인 정보를 저장한다.
        FoodInfoItem infoItem = new FoodInfoItem();
        infoItem.post_category = from;
        infoItem.memberSeq = memberSeq;
        infoItem.post_nickname = ((MyApp)getApplication()).getMemberNickName();
        setToolbar();
        GoLib.getInstance().goFragment(getSupportFragmentManager(),
                R.id.content_main, BestFoodRegisterInputFragment.newInstance(infoItem));
    }

    /**
     * 툴바를 설정한다.
     */
    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null &&from==1002) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.bestfood_register);
        }else if(actionBar != null &&from==1001){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("모집 등록");
        }
        else{
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("등록");
        }
    }

    /**
     * 오른쪽 상단 메뉴를 구성한다.
     * 닫기 메뉴만이 설정되어 있는 menu_close.xml를 지정한다.
     * @param menu 메뉴 객체
     * @return 메뉴를 보여준다면 true, 보여주지 않는다면 false
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_close, menu);
        return true;
    }

    /**
     * 왼쪽 화살표 메뉴(android.R.id.home)를 클릭했을 때와
     * 오른쪽 상단 닫기 메뉴를 클릭했을 때의 동작을 지정한다.
     * 여기서는 모든 버튼이 액티비티를 종료한다.
     * @param item 메뉴 아이템 객체
     * @return 메뉴를 처리했다면 true, 그렇지 않다면 false
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            case R.id.action_close:
                finish();
                break;
        }

        return true;
    }

    /**
     * 다른 액티비티를 실행한 결과를 처리하는 메소드
     * (실제로는 프래그먼트로 onActivityResult 호출을 전달하기 위한 목적으로 작성)
     * @param requestCode 액티비티를 실행하면서 전달한 요청 코드
     * @param resultCode 실행한 액티비티가 설정한 결과 코드
     * @param data 결과 데이터
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}