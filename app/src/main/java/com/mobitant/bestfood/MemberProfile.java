package com.mobitant.bestfood;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.mobitant.bestfood.adapter.InfoListAdapter;
import com.mobitant.bestfood.custom.EndlessRecyclerViewScrollListener;
import com.mobitant.bestfood.item.FoodInfoItem;
import com.mobitant.bestfood.lib.GoLib;
import com.mobitant.bestfood.lib.MyLog;
import com.mobitant.bestfood.lib.StringLib;
import com.mobitant.bestfood.item.User;
import com.mobitant.bestfood.remote.RemoteService;
import com.mobitant.bestfood.remote.ServiceGenerator;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import customfonts.MyTextView_Roboto_Regular;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
1. 게시글에서 프로필 액티비티로 넘어왔는지?
    -> MyApp의 Seq와 게시글등록된 MemberSeq와 같다면 아래에 내프로필 화면이여야하고,내가쓴글을 보여줘야함.
2. 네비게이션 드로우에서 프로필 액티비티로 넘어왔는지?
    -> 내 프로필,내가쓴글 보여줘야하고 ,로그인상태가 아니라면 로그인화면으로 가게해야함.
3.  내가쓴글일경우.
    ->프로필 수정버튼을눌러 프로필 수정을 가능하게해야함. 설정버튼을 눌러
4. 내가쓴글이 아닐경우
    ->구경만할 수 있게해야함.
 */
public class MemberProfile extends AppCompatActivity implements View.OnClickListener {
    private final String TAG = this.getClass().getSimpleName();
    User currentUser;
    User memberProfle;//보려는 멤버 프로필
    Context context;

    //bestfood
    ImageView profileIconImage;
    ImageView profileChange;
    int mySeq;
    //bestfood

    TextView userNickName;
    MyTextView_Roboto_Regular profileViewOneLineDescription;
    EndlessRecyclerViewScrollListener scrollListener;
    InfoListAdapter infoListAdapter;
    TextView noDataText;
    RecyclerView bestFoodList;
    LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.user_profile);
        currentUser = ((MyApp) getApplication()).getUserItem();//로그인한 사용자..
        bestFoodList = (RecyclerView) findViewById(R.id.list);
        noDataText = (TextView) findViewById(R.id.no_data);
        profileIconImage = (ImageView) findViewById(R.id.profile_icon);
        profileChange = (ImageView) findViewById(R.id.profile_change);
        userNickName = (TextView) findViewById(R.id.user_profile_nickname);
        profileViewOneLineDescription = (MyTextView_Roboto_Regular) findViewById(R.id.profile_view_one_line_description);
        mySeq = ((MyApp)getApplicationContext()).getMemberSeq();
        setToolbar();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle.getString("callActivity").equals("BestFoodInfoActivity")
                || bundle.getString("callActivity").equals("SupportersActivity")) { // info에서 상대방 프로필보기 누른경우
            int wantMemberSeq = (int) bundle.getInt("data");
            int mySeq = (int) bundle.getInt("MySeq");
            //로그인한 상태이고 내가쓴 게시글에있는 프로필을 누른경우
            if (((MyApp) getApplication()).isLogin() == true && wantMemberSeq== ((MyApp) getApplication()).getMemberSeq()) {
                setViewMyProfile();
                setMyProfileImage();
                setRecyclerView(((MyApp) getApplication()).getMemberSeq());
                listInfo(((MyApp) getApplication()).getMemberSeq(), mySeq,0);
            } else { //내가아닌 사람의 프로필을 누른경우
                setMemberProfileView();
                selectUserInfo(wantMemberSeq);
            }
        } else { // 내 프로필설정 누른경우
            setViewMyProfile();
            setMyProfileImage();
            setRecyclerView(((MyApp) getApplication()).getMemberSeq());
            listInfo(((MyApp) getApplication()).getMemberSeq(),mySeq, 0);
        }

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle.getString("callActivity").equals("BestFoodInfoActivity")
                || bundle.getString("callActivity").equals("SupportersActivity")) { // info에서 상대방 프로필보기 누른경우
            int wantMemberSeq = (int) bundle.getInt("data");
            int mySeq = (int) bundle.getInt("MySeq");
            //로그인한 상태이고 내가쓴 게시글에있는 프로필을 누른경우
            MyLog.d("원트시큐 : " + wantMemberSeq);
            MyLog.d("마이시큐" + mySeq);
            MyLog.d("내시큐 : " + ((MyApp) getApplication()).getMemberSeq());
            if (wantMemberSeq == ((MyApp) getApplication()).getMemberSeq()) {
                setViewMyProfile();
                setMyProfileImage();
            } else { //내가아닌 사람의 프로필을 누른경우
                setMemberProfileView();
            }
        } else { // 내 프로필설정 누른경우
            setViewMyProfile();
            setMyProfileImage();

        }
    }

    public void setMemberProfileView() {
        profileChange.setVisibility(View.GONE);
    }

    /*
    뷰화면 구현
     */
    public void setViewMyProfile() {
        profileChange.setVisibility(View.VISIBLE);
        profileChange.setOnClickListener(this);
        userNickName.setText(currentUser.nickname);
        profileViewOneLineDescription.setText(currentUser.getOneLineDescription());
    }

    /**
     * 리사이클러뷰를 설정하고 스크롤 리스너를 추가한다.
     */
    private void setRecyclerView(int wantMemberSeq) {
        layoutManager = new LinearLayoutManager(this);
        bestFoodList.setLayoutManager(layoutManager);

        infoListAdapter = new InfoListAdapter(this,
                R.layout.row_bestfood_list, new ArrayList<FoodInfoItem>());
        bestFoodList.setAdapter(infoListAdapter);

        scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                listInfo(wantMemberSeq,mySeq,  page);
            }
        };
        bestFoodList.addOnScrollListener(scrollListener);
    }

    /**
     * 서버에서 맛집 정보를 조회한다.
     *
     * @param wantMemberSeq 사용자 시퀀스
     * @param currentPage   현재 페이지
     */
    private void listInfo(int wantMemberSeq, int mySeq,final int currentPage) {
        RemoteService remoteService = ServiceGenerator.createService(RemoteService.class);
        Call<ArrayList<FoodInfoItem>> call = remoteService.postedProfileListSoftwareInfo(wantMemberSeq,mySeq ,currentPage);
        call.enqueue(new Callback<ArrayList<FoodInfoItem>>() {
            @Override
            public void onResponse(Call<ArrayList<FoodInfoItem>> call,
                                   Response<ArrayList<FoodInfoItem>> response) {
                ArrayList<FoodInfoItem> list = response.body();

                if (response.isSuccessful() && list != null) {
                    infoListAdapter.addItemList(list);

                    if (infoListAdapter.getItemCount() == 0) {
                        noDataText.setVisibility(View.VISIBLE);
                    } else {
                        noDataText.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<FoodInfoItem>> call, Throwable t) {
                MyLog.d(TAG, "no internet connectivity");
                MyLog.d(TAG, t.toString());
            }
        });
    }


    private void setMemberProfileImage() {
        if (StringLib.getInstance().isBlank(memberProfle.memberIconFilename)) {
            Picasso.with(this).load(R.drawable.ic_person).into(profileIconImage);
        }
        else if (memberProfle.memberIconFilename.length()>=30){
            Picasso.with(this).load(memberProfle.memberIconFilename).into(profileIconImage);
        }
        else {
            Picasso.with(this)
                    .load(RemoteService.MEMBER_ICON_URL + memberProfle.memberIconFilename)
                    .into(profileIconImage);
        }
    }


    /**
     * 사용자 정보를 기반으로 프로필 아이콘을 설정한다.
     */
    private void setMyProfileImage() {
        if (StringLib.getInstance().isBlank(currentUser.memberIconFilename)) {
            Picasso.with(this).load(R.drawable.ic_person).into(profileIconImage);
        }    else if (currentUser.memberIconFilename.length()>=30){
            Picasso.with(this).load(currentUser.memberIconFilename).into(profileIconImage);
        }

        else {
            Picasso.with(this)
                    .load(RemoteService.MEMBER_ICON_URL + currentUser.memberIconFilename)
                    .into(profileIconImage);
        }
    }

    /**
     * 액티비티 툴바를 설정한다.
     */
    private void setToolbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("프로필");
        }
    }

    /**
     * 서버에서 유저 정보를 조회한다.
     *
     * @param wantMemberSeq 유저정보시퀀스
     */
    private void selectUserInfo(int wantMemberSeq) {
        RemoteService remoteService = ServiceGenerator.createService(RemoteService.class);
        Call<User> call = remoteService.selectUserInfo(wantMemberSeq);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {

                memberProfle = response.body();
                userNickName.setText(memberProfle.nickname);
                setMemberProfileImage();
                setRecyclerView(wantMemberSeq);
                listInfo(wantMemberSeq, mySeq, 0);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                MyLog.d(TAG, "no internet connectivity");
                MyLog.d(TAG, t.toString());
            }

        });
    }

    /**
     * 오른쪽 상단 메뉴를 구성한다.
     * 닫기 메뉴만이 설정되어 있는 menu_close.xml를 지정한다.
     *
     * @param menu 메뉴 객체
     * @return 메뉴를 보여준다면 true, 보여주지 않는다면 false
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_profile_menu, menu);

        return true;
    }

    /**
     * 왼쪽 화살표 메뉴(android.R.id.home)를 클릭했을 때와
     * 오른쪽 상단 메뉴를 클릭했을 때의 동작을 지정한다.
     * 여기서는 모든 버튼이 액티비티를 종료한다.
     *
     * @param item 메뉴 아이템 객체
     * @return 메뉴를 처리했다면 true, 그렇지 않다면 false
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.user_profile_send_message:
                break;
        }

        return true;
    }


    /**
     * 프로필 아이콘이나 프로필 아이콘 변경 뷰를 클릭했을 때, 프로필 아이콘을 변경할 수 있도록
     * startProfileIconChange() 메소드를 호출한다.
     *
     * @param v 클릭한 뷰 객체
     */
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.profile_change) {
            GoLib.getInstance().goProfileChangeActivity(this);
        }


    }
}

