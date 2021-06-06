package com.serviingo.serviingo.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.irozon.sneaker.Sneaker;
import com.rjesture.startupkit.AppTools;
import com.serviingo.serviingo.Common.Preferences;
import com.serviingo.serviingo.Common.SimpleHTTPConnection;
import com.serviingo.serviingo.CommonControlerApi.DoLogoutPresenter;
import com.serviingo.serviingo.Database.AppSettings;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.storage.SharedPrefManager;
import com.serviingo.serviingo.utils.AppUrls;
import com.serviingo.serviingo.utils.AppUtils;
import com.serviingo.serviingo.view.BaseActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.ResponseBody;

import static com.serviingo.serviingo.R.drawable.globalloader;
import static com.serviingo.serviingo.utils.AppUtils.onBackPress;

public class MainActivity extends BaseActivity implements View.OnClickListener, SearchView.OnQueryTextListener, DoLogoutPresenter.DoLogoutView {
    public static ScrollView scrollView;
    int count = 0;
    String[] City;
    int sp_position;
    String selected;
    SearchView etsearch;
    Spinner spinner_state;
    TabLayout tabLayout, mtabLayout;
    ImageView right_moveiv, left_moveiv;
    ViewPager viewPager, mViewPager;
    ViewPager viewPagerMidUpper, viewPagerService, viewPagerSpotlight;
    LinearLayout sliderDotspanelMidUpper, sliderDotspanelService, sliderDotspanelSpotlight;
    private int dotscountMidUpper, dotscountService, dotscountSpolight;
    private ImageView[] dotMidUpper, dotservice, dotspolight;
    LinearLayoutManager linearLayoutManager;
    RecyclerView box1_recyclerview, recyclerView, rcv;

    Preferences pref;
    TextView aboutHeading, short_descriptionn,
            aboutDesc, tvserviceprovider, tvreferdesc;

    RelativeLayout rlDashBoard, rr_home, rr_category, rr_recharge, rrInsurance,
            rr_About, rr_Contact, rr_register, rrsignin;
    ImageView referImageview, iv_Imageview, iv_logo,
            iv_menu, ivMiddleslider;
    String pinterest_linklink, facebook_linklink, StateId, State,
            linkedin_linklink, youtube_linklink, insta_linklink;


    //Arraylists
    ArrayList<String> Statelist = new ArrayList<>();
    ArrayList<String> StatelistID = new ArrayList<>();
    ArrayList<HashMap<String, String>> state = new ArrayList<>();
    ArrayList<HashMap<String, String>> recent_data = new ArrayList();
    ArrayList<HashMap<String, String>> Categorydata = new ArrayList();
    ArrayList<HashMap<String, String>> TopBannerList = new ArrayList();
    ArrayList<HashMap<String, String>> middleSliderList = new ArrayList();
    ArrayList<HashMap<String, String>> HorrizontalCategorydata = new ArrayList();
    TextView checkout, terms, cart, Success, Profile, wllet, ratingreviews, ratereviewsmodel, myAddressBook, addaddress, addonService,
            chat, financialcategory, financialservice, financialserviceDetail, vendorregister, pp, faq, cancellationPolicy;


    private  DoLogoutPresenter doLogoutPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
        findViewById();
        Log.e("user", SharedPrefManager.getInstans(getApplicationContext()).getNumber() +
                SharedPrefManager.getInstans(getApplicationContext()).getemail() +
                SharedPrefManager.getInstans(getApplicationContext()).getfullname() +
                SharedPrefManager.getInstans(getApplicationContext()).getrole() +
                SharedPrefManager.getInstans(getApplicationContext()).getaccess_token() +
                SharedPrefManager.getInstans(getApplicationContext()).getUserId()


        );
        if (SimpleHTTPConnection.isNetworkAvailable(mActivity)) {
            getHomeApi();
        } else {
            AppUtils.showErrorMessage(tvreferdesc, "Please Check Your Internet Connection", mActivity);
        }
    }

    private void findViewById() {
        pref = new Preferences(mActivity);
        doLogoutPresenter=new DoLogoutPresenter(this);
        rcv = findViewById(R.id.rcv);
        rlDashBoard = findViewById(R.id.rlDashBoard);
        rr_home = findViewById(R.id.rr_home);
        rr_category = findViewById(R.id.rr_category);
        rr_recharge = findViewById(R.id.rr_recharge);
        rrInsurance = findViewById(R.id.rrInsurance);
        rr_About = findViewById(R.id.rr_About);
        rr_Contact = findViewById(R.id.rr_Contact);
        rr_register = findViewById(R.id.rr_register);
        rrsignin = findViewById(R.id.rrsignin);

        iv_logo = findViewById(R.id.iv_logo);
        aboutDesc = findViewById(R.id.aboutDesc);
        etsearch = findViewById(R.id.etsearch);
        iv_menu = findViewById(R.id.iv_menu);
        viewPager = findViewById(R.id.viewPager);

        mViewPager = findViewById(R.id.mviewPager);
        right_moveiv = findViewById(R.id.right_moveiv);
        left_moveiv = findViewById(R.id.left_moveiv);
        tabLayout = findViewById(R.id.tabLayout);
        mtabLayout = findViewById(R.id.mtabLayout);
        tvreferdesc = findViewById(R.id.tvreferdesc);

        recyclerView = findViewById(R.id.recyclerView);
        iv_Imageview = findViewById(R.id.iv_Imageview);
        spinner_state = findViewById(R.id.spinner_state);
        aboutHeading = findViewById(R.id.aboutHeading);
//        referImageview = findViewById(R.id.referImageview);
        ivMiddleslider = findViewById(R.id.ivMiddleslider);
        scrollView = findViewById(R.id.scroll_side_menu);
        box1_recyclerview = findViewById(R.id.box1_recyclerview);
        tvserviceprovider = findViewById(R.id.tvserviceprovider);
        short_descriptionn = findViewById(R.id.short_description);
        etsearch.setOnQueryTextListener(this);

        int searchSrcTextId = getResources().getIdentifier("android:id/search_src_text", null, null);
        EditText searchEditText = etsearch.findViewById(searchSrcTextId);
        searchEditText.setTextColor(Color.BLACK); // set the text color
        searchEditText.setHintTextColor(Color.BLACK);
        setLayoutManager();
        setOnClickListener();

        hideFindViewBYyId();


        City = getResources().getStringArray(R.array.spinner);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_spinner_dropdown_item, City);
        // sp_position = ad.getPosition(myString);
        spinner_state.setAdapter(ad);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                //StateId = StatelistID.get(spinner_state.getSelectedItemPosition());
                // State = Statelist.get(spinner_state.getSelectedItemPosition());
                spinner_state.setPrompt("Lucknow");
                selected = spinner_state.getSelectedItem().toString();
                System.out.println(selected);
                setid();
            }

            private void setid() {
                spinner_state.setSelection(sp_position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void hideFindViewBYyId() {
        checkout = findViewById(R.id.checkout);
        terms = findViewById(R.id.terms);
        cart = findViewById(R.id.cart);
        Success = findViewById(R.id.Success);
        Profile = findViewById(R.id.Profile);
        wllet = findViewById(R.id.wllet);
        ratingreviews = findViewById(R.id.ratingreviews);
        ratereviewsmodel = findViewById(R.id.ratereviewsmodel);
        myAddressBook = findViewById(R.id.myAddressBook);
        addaddress = findViewById(R.id.addaddress);
        chat = findViewById(R.id.chat);
        addonService = findViewById(R.id.addonService);
        financialcategory = findViewById(R.id.financialcategory);
        financialservice = findViewById(R.id.financialservice);
        financialserviceDetail = findViewById(R.id.financialserviceDetail);
        vendorregister = findViewById(R.id.vendorregister);
        pp = findViewById(R.id.pp);
        faq = findViewById(R.id.faq);
        cancellationPolicy = findViewById(R.id.cancellationPolicy);

        checkout.setOnClickListener(this);
        terms.setOnClickListener(this);
        cart.setOnClickListener(this);
        Success.setOnClickListener(this);
        Profile.setOnClickListener(this);
        wllet.setOnClickListener(this);
        ratingreviews.setOnClickListener(this);
        ratereviewsmodel.setOnClickListener(this);
        myAddressBook.setOnClickListener(this);
        addaddress.setOnClickListener(this);
        addonService.setOnClickListener(this);
        financialcategory.setOnClickListener(this);
        financialservice.setOnClickListener(this);
        financialserviceDetail.setOnClickListener(this);
        vendorregister.setOnClickListener(this);
        pp.setOnClickListener(this);
        faq.setOnClickListener(this);
        chat.setOnClickListener(this);
        cancellationPolicy.setOnClickListener(this);


    }

    private void setLayoutManager() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        box1_recyclerview.setLayoutManager(gridLayoutManager);

        linearLayoutManager = (new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this, 3);
        rcv.setLayoutManager(gridLayoutManager2);
        rcv.setHasFixedSize(true);

        showDotsforMidUpperSlider();
        showDotsforService();
        showDotsforSpotlight();
    }

    private void setOnClickListener() {
        etsearch.setOnClickListener(this);
        iv_menu.setOnClickListener(this);

        rlDashBoard.setOnClickListener(this);
        rr_home.setOnClickListener(this);
        rr_category.setOnClickListener(this);
        rr_recharge.setOnClickListener(this);
        rrInsurance.setOnClickListener(this);
        rr_About.setOnClickListener(this);
        rr_Contact.setOnClickListener(this);
        rr_register.setOnClickListener(this);
        rrsignin.setOnClickListener(this);

    }

    private void showDotsforMidUpperSlider() {
        viewPagerMidUpper = (ViewPager) findViewById(R.id.viewPagermiddleUpper);

        sliderDotspanelMidUpper = (LinearLayout) findViewById(R.id.SliderDotsmiddleUpper);

        ViewPagerAdapterMiddleUpper viewPagerAdapter = new ViewPagerAdapterMiddleUpper(this);

        viewPagerMidUpper.setAdapter(viewPagerAdapter);

        dotscountMidUpper = viewPagerAdapter.getCount();
        dotMidUpper = new ImageView[dotscountMidUpper];

        for (int i = 0; i < dotscountMidUpper; i++) {

            dotMidUpper[i] = new ImageView(this);
            dotMidUpper[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanelMidUpper.addView(dotMidUpper[i], params);

        }

        dotMidUpper[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPagerMidUpper.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscountMidUpper; i++) {
                    dotMidUpper[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dotMidUpper[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void showDotsforService() {
        viewPagerService = (ViewPager) findViewById(R.id.viewPagerService);

        sliderDotspanelService = (LinearLayout) findViewById(R.id.SliderDots);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this);

        viewPagerService.setAdapter(viewPagerAdapter);

        dotscountService = viewPagerAdapter.getCount();
        dotservice = new ImageView[dotscountService];

        for (int i = 0; i < dotscountService; i++) {

            dotservice[i] = new ImageView(this);
            dotservice[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanelService.addView(dotservice[i], params);

        }

        dotservice[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPagerService.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscountService; i++) {
                    dotservice[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dotservice[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void showDotsforSpotlight() {
        viewPagerSpotlight = (ViewPager) findViewById(R.id.viewPagerSpotlight);

        sliderDotspanelSpotlight = (LinearLayout) findViewById(R.id.SliderDotsSpotlight);

        ViewPagerAdapterSpolight viewPagerAdapter = new ViewPagerAdapterSpolight(this);

        viewPagerSpotlight.setAdapter(viewPagerAdapter);

        dotscountSpolight = viewPagerAdapter.getCount();
        dotspolight = new ImageView[dotscountSpolight];

        for (int i = 0; i < dotscountSpolight; i++) {

            dotspolight[i] = new ImageView(this);
            dotspolight[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanelSpotlight.addView(dotspolight[i], params);

        }

        dotspolight[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        viewPagerSpotlight.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < dotscountSpolight; i++) {
                    dotspolight[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dotspolight[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }


    private void PostSearchApi() {
        String url = AppUrls.PostSearch;
        AndroidNetworking.post(url)
                .addBodyParameter("search_key", String.valueOf(etsearch.getQuery()))
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseSearchJsonResponse(response);
                    }

                    @Override
                    public void onError(ANError error) {
                        // handle error
                        if (error.getErrorCode() != 0) {

                            Log.d("onError errorCode ", "onError errorCode : " + error.getErrorCode());
                            Log.d("onError errorBody", "onError errorBody : " + error.getErrorBody());
                            Log.d("onError errorDetail", "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            Toast.makeText(mActivity, String.valueOf(error.getErrorDetail()), Toast.LENGTH_SHORT).show();
                            //  AppUtils.showErrorMessage(etNewpassword, String.valueOf(error.getErrorDetail()), mActivity);
                        }
                    }
                });
    }

    private void parseSearchJsonResponse(JSONObject response) {
        try {
            String message = response.getString("message");
            Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
            if (response.getString("status").equals("true")) {
                JSONObject jsonObject = response.getJSONObject("data");
                JSONArray gallery_imagesArray = jsonObject.getJSONArray("response");
                for (int i = 0; i < gallery_imagesArray.length(); i++) {
                    JSONObject productobject = gallery_imagesArray.getJSONObject(i);
                    HashMap<String, String> addList = new HashMap();
                }
            } else {
            }
        } catch (Exception e) {
            Log.v("error", String.valueOf(e));
        }
    }






    private void getHomeApi() {
        //  AppUtils.showRequestDialog(mActivity);
        AppTools.showGifDialog(mActivity, globalloader);
        String url = AppUrls.gethome;
        Log.v("faq_url", url);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseJsonResponse(response);
                    }

                    @Override
                    public void onError(ANError error) {
                        AppTools.hideGifDialog();
                        Log.v("data", error.getErrorBody());
                        Log.v("data", error.getErrorDetail());
                    }
                });
    }

    private void parseJsonResponse(JSONObject response) {
        try {
            AppTools.hideGifDialog();
            //  AppUtils.hideDialog();
            String message = response.getString("message");
            Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
            if (response.getString("status").equals("true")) {
                JSONObject jsonObject = response.getJSONObject("data");
                JSONObject logojsonObject = jsonObject.getJSONObject("logo");
                String logo = logojsonObject.getString("logo");
                Log.v("logo", logo);
                /*Glide.with(mActivity)
                        .load(logo)
                        .into(iv_logo);*/
                //.asGif()..placeholder(R.drawable.loading2) .crossFade()
                //  Picasso.get().load(logo).resize(815,315).into(iv_logo);
                // Picasso.get().load("http://7xhospitals.com/serviingo/public/home_images/c2VydmlpbmdvLWxvZ28tLmpwZw==210119072430.jpg").into(iv_logo);
                JSONArray gallery_imagesArray = jsonObject.getJSONArray("gallery_images");
                for (int i = 0; i < gallery_imagesArray.length(); i++) {
                    JSONObject productobject = gallery_imagesArray.getJSONObject(i);
                    HashMap<String, String> addList = new HashMap();
                }
                JSONArray slidersArray = jsonObject.getJSONArray("sliders");
                for (int i = 0; i < slidersArray.length(); i++) {
                    JSONObject productobject = slidersArray.getJSONObject(i);
                    HashMap<String, String> addList = new HashMap();
                    addList.put("id", productobject.getString("id"));
                    addList.put("slug", productobject.getString("slug"));
                    addList.put("link", productobject.getString("link"));
                    addList.put("status", productobject.getString("status"));
                    addList.put("created_at", productobject.getString("created_at"));
                    addList.put("updated_at", productobject.getString("updated_at"));
                    TopBannerList.add(addList);
                }
                ImageSliderPagerAdapter imageSliderPagerAdapter = new ImageSliderPagerAdapter(TopBannerList);
                viewPager.setAdapter(imageSliderPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {
                        count = position;
                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }

                });
                right_moveiv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (viewPager.getCurrentItem() < viewPager.getRight())
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1, true);
                    }
                });

                left_moveiv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (viewPager.getCurrentItem() > viewPager.getLeft())
                            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1, true);
                    }
                });


                JSONArray all_categoriesArray = jsonObject.getJSONArray("all_categories");

                for (int i = 0; i < all_categoriesArray.length(); i++) {
                    JSONObject productobject = all_categoriesArray.getJSONObject(i);
                    HashMap<String, String> addList = new HashMap();
                    addList.put("id", productobject.getString("id"));
                    addList.put("name", productobject.getString("name"));
                    addList.put("icon", productobject.getString("icon"));
                    addList.put("slug", productobject.getString("slug"));
                    addList.put("reviews_heading", productobject.getString("reviews_heading"));
                    addList.put("reviews_title", productobject.getString("reviews_title"));
                    addList.put("faq_title", productobject.getString("faq_title"));
                    addList.put("short_description", productobject.getString("short_description"));
                    addList.put("about_category", productobject.getString("about_category"));
                    addList.put("status", productobject.getString("status"));
                    addList.put("meta_title", productobject.getString("meta_title"));
                    addList.put("meta_keywords", productobject.getString("meta_keywords"));
                    addList.put("meta_description", productobject.getString("meta_description"));
                    addList.put("avg_rating", productobject.getString("avg_rating"));
                    addList.put("banner_title", productobject.getString("banner_title"));
                    addList.put("banner_description", productobject.getString("banner_description"));
                    addList.put("created_at", productobject.getString("created_at"));
                    addList.put("updated_at", productobject.getString("updated_at"));
                    Log.v("all_categories", addList + "");

                    Categorydata.add(addList);
                }
                box1_recyclerview.setHasFixedSize(true);
                CategoryAdapter categoryAdapter = new CategoryAdapter(Categorydata);
                box1_recyclerview.setAdapter(categoryAdapter);
                //Todo.. main section
                JSONObject main_sectionobj = jsonObject.getJSONObject("main_section");
                String main_sectionid = main_sectionobj.getString("id");
                String main_sectionslug = main_sectionobj.getString("slug");
                String main_sectionheading = main_sectionobj.getString("heading");
                String main_sectiontitle = main_sectionobj.getString("title");
                // tvreferdesc.setText(title);
                String main_sectiondescription = main_sectionobj.getString("description");
                String main_sectionimages = main_sectionobj.getString("images");
                String main_sectioncategories_ids = main_sectionobj.getString("categories_ids");
                String main_sectioncreated_at = main_sectionobj.getString("created_at");
                String main_sectionupdated_at = main_sectionobj.getString("updated_at");

                JSONObject one_sectionnobj = jsonObject.getJSONObject("one_section");
                String one_sectionid = one_sectionnobj.getString("id");
                String one_sectionslug = one_sectionnobj.getString("slug");
                String one_sectionheading = one_sectionnobj.getString("heading");
                String one_sectiontitle = one_sectionnobj.getString("title");
                // tvreferdesc.setText(title);
                String one_sectiondescription = one_sectionnobj.getString("description");
                String one_sectionimages = one_sectionnobj.getString("images");
                String one_sectioncategories_ids = one_sectionnobj.getString("categories_ids");
                String one_sectioncreated_at = one_sectionnobj.getString("created_at");
                String one_sectionupdated_at = one_sectionnobj.getString("updated_at");

                JSONObject two_sectionnobj = jsonObject.getJSONObject("two_section");
                String two_sectionid = two_sectionnobj.getString("id");
                String two_sectionslug = two_sectionnobj.getString("slug");
                String two_sectionheading = two_sectionnobj.getString("heading");
                String two_sectiontitle = two_sectionnobj.getString("title");
                // tvreferdesc.setText(title);
                String two_sectiondescription = two_sectionnobj.getString("description");
                String two_sectionimages = two_sectionnobj.getString("images");
                String two_sectioncategories_ids = two_sectionnobj.getString("categories_ids");
                String two_sectioncreated_at = two_sectionnobj.getString("created_at");
                String two_sectionupdated_at = two_sectionnobj.getString("updated_at");

                JSONObject three_sectionnobj = jsonObject.getJSONObject("three_section");
                String three_sectionid = three_sectionnobj.getString("id");
                String three_sectionslug = three_sectionnobj.getString("slug");
                String three_sectionheading = three_sectionnobj.getString("heading");
                String three_sectiontitle = three_sectionnobj.getString("title");
                // tvreferdesc.setText(title);
                String three_sectiondescription = three_sectionnobj.getString("description");
                String three_sectionimages = three_sectionnobj.getString("images");
                String three_sectioncategories_ids = three_sectionnobj.getString("categories_ids");
                String three_sectioncreated_at = three_sectionnobj.getString("created_at");
                String three_sectionupdated_at = three_sectionnobj.getString("updated_at");

                JSONObject four_sectionnobj = jsonObject.getJSONObject("four_section");
                String four_sectionid = four_sectionnobj.getString("id");
                String four_sectionslug = four_sectionnobj.getString("slug");
                String four_sectionheading = four_sectionnobj.getString("heading");
                String four_sectiontitle = four_sectionnobj.getString("title");
                // tvreferdesc.setText(title);
                String four_sectiondescription = four_sectionnobj.getString("description");
                String four_sectionimages = four_sectionnobj.getString("images");
                String four_sectioncategories_ids = four_sectionnobj.getString("categories_ids");
                String four_sectioncreated_at = four_sectionnobj.getString("created_at");
                String four_sectionupdated_at = four_sectionnobj.getString("updated_at");

                JSONObject refer_and_earnobj = jsonObject.getJSONObject("refer_and_earn");
                String refer_and_earnobjid = refer_and_earnobj.getString("id");
                String refer_and_earnobjslug = refer_and_earnobj.getString("slug");
                String heading = refer_and_earnobj.getString("heading");
                String title = refer_and_earnobj.getString("title");
                Log.v("titleCancellation", title);
                tvreferdesc.setText(title);

                String description = refer_and_earnobj.getString("description");
                String images = refer_and_earnobj.getString("images");
                Log.v("image", images);
                //  Picasso.get().load("http://7xhospitals.com/serviingo/public/home_images/Y21WbVpYSXRhV052Ymk1d2JtYz0yMDEyMDMwNzM3MDQucG5n210112064425.png").into(referImageview);
                String categories_ids = refer_and_earnobj.getString("categories_ids");
                String refer_and_earnobjcreated_at = refer_and_earnobj.getString("created_at");
                String refer_and_earnobjupdated_at = refer_and_earnobj.getString("updated_at");


                JSONObject facebook_linkobj = jsonObject.getJSONObject("about_content");
                String facebook_linkid = facebook_linkobj.getString("id");
                String about_contentimage = facebook_linkobj.getString("image");
                String about_contentheading = facebook_linkobj.getString("heading");
                //AppTools.setImgPicasso(AppUrls.BASEAboutimagepath + about_contentimage,mActivity,iv_Imageview);
                Picasso.get().load(AppUrls.BASEAboutimagepath + about_contentimage).into(iv_Imageview);
                aboutHeading.setText(about_contentheading);
                String facebook_linkkshort_description = facebook_linkobj.getString("short_description");
                short_descriptionn.setText(facebook_linkkshort_description);
                String about_linkdescription = facebook_linkobj.getString("description");
                Log.v("aboutDesc", about_linkdescription);
                aboutDesc.setText(Html.fromHtml(about_linkdescription));
                String facebook_linksection1_heading = facebook_linkobj.getString("section1_heading");

                String facebook_linksection1_description = facebook_linkobj.getString("section1_description");
                String facebook_linksection2_description = facebook_linkobj.getString("section2_description");
                String facebook_linksection3_image = facebook_linkobj.getString("section3_image");
                String facebook_linksection3_heading = facebook_linkobj.getString("section3_heading");
                String facebook_linksection3_description = facebook_linkobj.getString("section3_description");
                String about_linkservice_provide = facebook_linkobj.getString("service_provide");
                //50+ Services provide
//                tvserviceprovider.setText(about_linkservice_provide + "+ Services providers");
                tvserviceprovider.setText(about_linkservice_provide + " +");
                String facebook_linkcreated_at = facebook_linkobj.getString("created_at");

                //LatestServices
                JSONArray latest_servicesArray = jsonObject.getJSONArray("latest_services");
                for (int i = 0; i < latest_servicesArray.length(); i++) {
                    JSONObject productobject = latest_servicesArray.getJSONObject(i);
                    HashMap<String, String> addList = new HashMap();
                    addList.put("id", productobject.getString("id"));
                    addList.put("category_id", productobject.getString("category_id"));
                    addList.put("sub_category_id", productobject.getString("sub_category_id"));
                    addList.put("sub_sub_category_id", productobject.getString("sub_sub_category_id"));
                    addList.put("name", productobject.getString("name"));
                    addList.put("amount", productobject.getString("amount"));
                    addList.put("discount", productobject.getString("discount"));
                    addList.put("after_discount", productobject.getString("after_discount"));
                    addList.put("status", productobject.getString("status"));
                    addList.put("icon", productobject.getString("icon"));
                    addList.put("detail_image", productobject.getString("detail_image"));
                    addList.put("description", productobject.getString("description"));
                    addList.put("detail_video", productobject.getString("detail_video"));
                    addList.put("created_at", productobject.getString("created_at"));
                    addList.put("updated_at", productobject.getString("updated_at"));
                    HorrizontalCategorydata.add(addList);
                }


                JSONArray recent_categoriesArray = jsonObject.getJSONArray("recent_categories");
                for (int i = 0; i < recent_categoriesArray.length(); i++) {
                    JSONObject productobject = recent_categoriesArray.getJSONObject(i);
                    HashMap<String, String> addList = new HashMap();
                    addList.put("id", productobject.getString("id"));
                    addList.put("name", productobject.getString("name"));
                    addList.put("icon", productobject.getString("icon"));
                    addList.put("slug", productobject.getString("slug"));
                    addList.put("reviews_heading", productobject.getString("reviews_heading"));
                    addList.put("reviews_title", productobject.getString("reviews_title"));
                    addList.put("faq_title", productobject.getString("faq_title"));

                    addList.put("short_description", productobject.getString("short_description"));
                    addList.put("about_category", productobject.getString("about_category"));
                    addList.put("status", productobject.getString("status"));
                    addList.put("meta_title", productobject.getString("meta_title"));

                    addList.put("meta_keywords", productobject.getString("meta_keywords"));
                    addList.put("meta_description", productobject.getString("meta_description"));
                    addList.put("avg_rating", productobject.getString("avg_rating"));
                    addList.put("banner_title", productobject.getString("banner_title"));
                    addList.put("banner_description", productobject.getString("banner_description"));
                    addList.put("created_at", productobject.getString("created_at"));
                    addList.put("updated_at", productobject.getString("updated_at"));
                    recent_data.add(addList);
                }


                JSONArray array = jsonObject.getJSONArray("middle_sliders");
                Log.v("MiddleSliders", array.toString());
                Log.v("MiddleSliders", array.get(0).toString());
                for (int i = 0; i < array.length(); i++) {
                    JSONObject productobject = array.getJSONObject(i);
                    HashMap<String, String> addList = new HashMap();
                    addList.put("id", productobject.getString("id"));

                    addList.put("slug", productobject.getString("slug"));
                    addList.put("link", productobject.getString("link"));
                    addList.put("status", productobject.getString("status"));
                    addList.put("created_at", productobject.getString("created_at"));
                    addList.put("updated_at", productobject.getString("updated_at"));
                    middleSliderList.add(addList);
                }
                ImageSliderPagerAdapter imageSliderPagerAdapter2 = new ImageSliderPagerAdapter(middleSliderList);
                mViewPager.setAdapter(imageSliderPagerAdapter2);
                mtabLayout.setupWithViewPager(mViewPager);

                mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                        count = position;

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

                JSONObject facebook_linkkobj = jsonObject.getJSONObject("facebook_link");
                String facebook_linkkid = facebook_linkkobj.getString("id");
                String facebook_linkslug = facebook_linkkobj.getString("slug");
                facebook_linklink = facebook_linkkobj.getString("link");
                String facebook_linkkcreated_at = facebook_linkkobj.getString("created_at");
                String facebook_linkupdated_at = facebook_linkkobj.getString("updated_at");

                JSONObject twitter_linkobj = jsonObject.getJSONObject("twitter_link");
                String twitter_linkid = twitter_linkobj.getString("id");
                String twitter_linkslug = twitter_linkobj.getString("slug");
                String twitter_linklink = twitter_linkobj.getString("link");
                String twitter_linkcreated_at = twitter_linkobj.getString("created_at");
                String twitter_linkupdated_at = twitter_linkobj.getString("updated_at");

                JSONObject insta_linkobj = jsonObject.getJSONObject("insta_link");
                String insta_linkid = insta_linkobj.getString("id");
                String insta_linkslug = insta_linkobj.getString("slug");
                insta_linklink = insta_linkobj.getString("link");
                String insta_linkcreated_at = insta_linkobj.getString("created_at");
                String insta_linkupdated_at = insta_linkobj.getString("updated_at");

                JSONObject youtube_linkobj = jsonObject.getJSONObject("youtube_link");
                String youtube_linkid = youtube_linkobj.getString("id");
                String youtube_linkslug = youtube_linkobj.getString("slug");
                youtube_linklink = youtube_linkobj.getString("link");
                String youtube_linkcreated_at = youtube_linkobj.getString("created_at");
                String youtube_linkupdated_at = youtube_linkobj.getString("updated_at");

                JSONObject linkedin_linkobj = jsonObject.getJSONObject("linkedin_link");
                String linkedin_linkid = linkedin_linkobj.getString("id");
                String linkedin_linkslug = linkedin_linkobj.getString("slug");
                linkedin_linklink = linkedin_linkobj.getString("link");
                String linkedin_linkcreated_at = linkedin_linkobj.getString("created_at");
                String linkedin_linkupdated_at = linkedin_linkobj.getString("updated_at");

                JSONObject pinterest_linkobj = jsonObject.getJSONObject("pinterest_link");
                String pinterest_linkid = pinterest_linkobj.getString("id");
                String pinterest_linkslug = pinterest_linkobj.getString("slug");
                pinterest_linklink = pinterest_linkobj.getString("link");
                String pinterest_linkcreated_at = pinterest_linkobj.getString("created_at");
                String pinterest_linkupdated_at = pinterest_linkobj.getString("updated_at");

                HorizontalCategoryAdapter horizontalCategoryAdapter = new HorizontalCategoryAdapter(HorrizontalCategorydata);
                recyclerView.setAdapter(horizontalCategoryAdapter);
                GridCategoryAdapter gridCategoryAdapter = new GridCategoryAdapter(recent_data);
                rcv.setAdapter(gridCategoryAdapter);


                JSONArray data_array = jsonObject.getJSONArray("area_pincodes_cities");


                for (int i = 0; i < data_array.length(); i++) {


                    JSONObject block_object = data_array.getJSONObject(i);


                    HashMap<String, String> map = new HashMap<String, String>();

                    map.put("id", block_object.get("id").toString());
                    map.put("state_id", block_object.get("state_id").toString());
                    map.put("city_id", block_object.get("city_id").toString());
                    map.put("created_at", block_object.get("created_at").toString());
                    map.put("updated_at", block_object.get("updated_at").toString());

                    JSONObject block_newObj = block_object.getJSONObject("get_state_name");
                    String data1 = block_newObj.getString("id");
                    Log.v("BlockNewobjId", data1);
                    String country_id = block_newObj.getString("country_id");
                    String name = block_newObj.getString("name");
                    String created_at = block_newObj.getString("created_at");
                    String updated_at = block_newObj.getString("updated_at");
                    JSONObject get_city_nameObj = block_object.getJSONObject("get_city_name");
                    String getdata1 = get_city_nameObj.getString("id");
                    Log.v("BlockNewobjId", data1);
                    String getstate_id = get_city_nameObj.getString("state_id");
                    String getname = get_city_nameObj.getString("name");
                    String getcreated_at = get_city_nameObj.getString("created_at");
                    String getupdated_at = get_city_nameObj.getString("updated_at");

                    state.add(map);

                    Statelist.add(state.get(i).get("name"));
                    StatelistID.add(state.get(i).get("id"));
                }

            } else {

            }
        } catch (Exception e) {
            Log.v("error", String.valueOf(e));
        }
    }

    @Override
    public void onBackPressed() {
        onBackPress(mActivity);
    }

    void updateStatus() {
        if (count >= TopBannerList.size()) {

            count = 0;
        }

        viewPager.setCurrentItem(count);
        count++;
        Log.v("nos", String.valueOf(count));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.checkout:
                startActivity(new Intent(MainActivity.this, CheckOut.class));
                break;
            case R.id.terms:
                startActivity(new Intent(MainActivity.this, TermsandCondition.class));
                break;
            case R.id.cart:
                startActivity(new Intent(MainActivity.this, Addtocart.class));
                break;
            case R.id.Success:
                startActivity(new Intent(MainActivity.this, Success.class));
                break;
            case R.id.Profile:
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                break;
            case R.id.wllet:
                startActivity(new Intent(MainActivity.this, MyWalletActivity.class));
                break;
            case R.id.ratingreviews:
                startActivity(new Intent(MainActivity.this, ReviewRatingActivity.class));
                break;
            case R.id.ratereviewsmodel:
                startActivity(new Intent(MainActivity.this, TestingAgreeCulturalUi.class));
                break;
            case R.id.myAddressBook:
                startActivity(new Intent(MainActivity.this, Addressbook.class));
                break;
            case R.id.addaddress:
                startActivity(new Intent(MainActivity.this, AddressActivity.class));
                break;
            case R.id.chat:
                startActivity(new Intent(MainActivity.this, ChatActivity.class));
                break;
            case R.id.addonService:
                startActivity(new Intent(MainActivity.this, Addones.class));
                break;
            case R.id.financialcategory:
                startActivity(new Intent(MainActivity.this, FinancialCategory.class));
                break;
            case R.id.financialservice:
                startActivity(new Intent(MainActivity.this, FinancialService.class));
                break;
            case R.id.financialserviceDetail:
                startActivity(new Intent(MainActivity.this, FinancialServiceDetail.class));
                break;
            case R.id.vendorregister:
                startActivity(new Intent(MainActivity.this, VenderReg.class));
                break;
            case R.id.pp:
                startActivity(new Intent(MainActivity.this, Privacypolicy.class));
                break;
            case R.id.faq:
                startActivity(new Intent(MainActivity.this, FAQ.class));
                break;
            case R.id.cancellationPolicy:
                startActivity(new Intent(MainActivity.this, cancellationPolicy.class));
                break;




           /* case R.id.ivPinterest:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(pinterest_linklink));
                startActivity(intent);
                break;
            case R.id.ivYoutube:
                Intent intent2 = new Intent();
                intent2.setAction(Intent.ACTION_VIEW);
                intent2.addCategory(Intent.CATEGORY_BROWSABLE);
                intent2.setData(Uri.parse(youtube_linklink));
                startActivity(intent2);
                break;
            case R.id.ivinsta:
                Intent intent3 = new Intent();
                intent3.setAction(Intent.ACTION_VIEW);
                intent3.addCategory(Intent.CATEGORY_BROWSABLE);
                intent3.setData(Uri.parse(insta_linklink));
                startActivity(intent3);
                break;
            case R.id.ivtwitter:
                Intent intent4 = new Intent();
                intent4.setAction(Intent.ACTION_VIEW);
                intent4.addCategory(Intent.CATEGORY_BROWSABLE);
                intent4.setData(Uri.parse(linkedin_linklink));
                startActivity(intent4);
                break;
            case R.id.ivfacebook:
                Intent intent5 = new Intent();
                intent5.setAction(Intent.ACTION_VIEW);
                intent5.addCategory(Intent.CATEGORY_BROWSABLE);
                intent5.setData(Uri.parse(pinterest_linklink));
                startActivity(intent5);
                break;*/
            case R.id.iv_menu:
                if (pref.get(AppSettings.loginCheck).equals("true")) {
                    if (scrollView.getVisibility() == View.VISIBLE) {
                        AppUtils.collapse(scrollView);
                        break;
                    }

                    AppUtils.expand(scrollView);
                    rr_home.setVisibility(View.VISIBLE);
                    rr_category.setVisibility(View.VISIBLE);
                    rr_recharge.setVisibility(View.VISIBLE);
                    rrInsurance.setVisibility(View.VISIBLE);
                    rr_About.setVisibility(View.GONE);
                    rr_Contact.setVisibility(View.GONE);
                    rr_register.setVisibility(View.GONE);
                    rrsignin.setVisibility(View.GONE);
                } else {
                    if (scrollView.getVisibility() == View.VISIBLE) {
                        AppUtils.collapse(scrollView);
                        break;
                    }

                    AppUtils.expand(scrollView);
                    rr_home.setVisibility(View.GONE);
                    rr_category.setVisibility(View.GONE);
                    rr_recharge.setVisibility(View.GONE);
                    rrInsurance.setVisibility(View.GONE);
                    rr_About.setVisibility(View.VISIBLE);
                    rr_Contact.setVisibility(View.VISIBLE);
                    rr_register.setVisibility(View.VISIBLE);
                    rrsignin.setVisibility(View.VISIBLE);
                }
               /* if (AppSettings.getString(AppSettings.id).isEmpty()) {
                    if (scrollView.getVisibility() == View.VISIBLE) {
                        AppUtils.collapse(scrollView);
                        break;
                    }

                    AppUtils.expand(scrollView);
                } else {
                    if (scrollView.getVisibility() == View.VISIBLE) {
                        AppUtils.collapse(scrollView);
                        break;
                    }

                    AppUtils.expand(scrollView);
                }*/

                break;
            case R.id.rlDashBoard:
//samepage
                break;
            case R.id.rr_home:
//My Booking
                break;
            case R.id.rr_category:
//My Profile
                startActivity(new Intent(MainActivity.this, ProfileActivity.class));

                break;
            case R.id.rr_recharge:
//My Wallet

                startActivity(new Intent(MainActivity.this, MyWalletActivity.class));

                break;
            case R.id.rrInsurance:

                doLogoutPresenter.DoLogout(this);
                AppTools.showGifDialog(mActivity, globalloader);

                break;

            case R.id.rr_About:

                startActivity(new Intent(MainActivity.this, ReviewRatingActivity.class));
//                startActivity(new Intent(MainActivity.this, subCategoryActivities.class));
//                startActivity(new Intent(MainActivity.this, SubsubCategory.class));

                break;
            case R.id.rr_Contact:
                startActivity(new Intent(MainActivity.this, ContactUsActivity.class));
                break;
            case R.id.rr_register:
//                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
                startActivity(new Intent(MainActivity.this, VenderReg.class));
                break;
            case R.id.rrsignin:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;

        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View view = getCurrentFocus();
        if (view != null && (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) && view instanceof EditText && !view.getClass().getName().startsWith("android.webkit.")) {
            int[] scrcoords = new int[2];
            view.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + view.getLeft() - scrcoords[0];
            float y = ev.getRawY() + view.getTop() - scrcoords[1];
            if (x < view.getLeft() || x > view.getRight() || y < view.getTop() || y > view.getBottom())
                ((InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow((this.getWindow().getDecorView().getApplicationWindowToken()), 0);
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (!TextUtils.isEmpty(s) && s.length() >= 3) {
            if (SimpleHTTPConnection.isNetworkAvailable(mActivity)) {
               /* offset="0";
                productList.clear();
                sortsList.clear();*/
                PostSearchApi();
            } else {
                AppUtils.showErrorMessage(tvreferdesc, "Please check your Internet Connection.", mActivity);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onDoLogoutError(String message) {
        AppTools.hideGifDialog();
        Sneaker.with(this)
                .setTitle(message)
                .setMessage("")
                .sneakError();

    }

    @Override
    public void onDoLogoutSuccess(ResponseBody response, String message) {
        Toast.makeText(this, "User Logout Successfully.", Toast.LENGTH_SHORT).show();

        SharedPrefManager.getInstans(getApplicationContext()).clear();
        AppSettings.putString(AppSettings.mobile, "");
        AppSettings.putString(AppSettings.loginCheck, "");

        //userprofile data
        AppSettings.putString(AppSettings.id, "");
        AppSettings.putString(AppSettings.name, "");
        AppSettings.putString(AppSettings.email, "");
        AppSettings.putString(AppSettings.gender, "");
        AppSettings.putString(AppSettings.mobile, "");
        AppSettings.putString(AppSettings.wallet, "");
        AppSettings.putString(AppSettings.dob, "");
        AppSettings.putString(AppSettings.user_pic, "");
        AppSettings.putString(AppSettings.referralId, "");
        AppSettings.putString(AppSettings.profilePic, "");

        AppSettings.putString(AppSettings.googleName, "");
        AppSettings.putString(AppSettings.googleEmail, "");
        AppSettings.putString(AppSettings.googledob, "");
        AppSettings.putString(AppSettings.googleId, "");

        AppSettings.putString(AppSettings.fbname, "");
        AppSettings.putString(AppSettings.fbId, "");
        pref.set(AppSettings.loginCheck,"false");
        pref.commit();
        AppTools.hideGifDialog();

        startActivity(new Intent(this, LoginActivity.class));


    /*    if (response.getString("status").equals("true")) {
            SharedPrefManager.getInstans(getApplicationContext()).clear();
            AppSettings.putString(AppSettings.mobile, "");
            AppSettings.putString(AppSettings.loginCheck, "");

            //userprofile data
            AppSettings.putString(AppSettings.id, "");
            AppSettings.putString(AppSettings.name, "");
            AppSettings.putString(AppSettings.email, "");
            AppSettings.putString(AppSettings.gender, "");
            AppSettings.putString(AppSettings.mobile, "");
            AppSettings.putString(AppSettings.wallet, "");
            AppSettings.putString(AppSettings.dob, "");
            AppSettings.putString(AppSettings.user_pic, "");
            AppSettings.putString(AppSettings.referralId, "");
            AppSettings.putString(AppSettings.profilePic, "");

            AppSettings.putString(AppSettings.googleName, "");
            AppSettings.putString(AppSettings.googleEmail, "");
            AppSettings.putString(AppSettings.googledob, "");
            AppSettings.putString(AppSettings.googleId, "");

            AppSettings.putString(AppSettings.fbname, "");
            AppSettings.putString(AppSettings.fbId, "");
            startActivity(new Intent(this, LoginActivity.class));
        } else {
        }*/
    }

    @Override
    public void onDoLogoutFailure(Throwable t) {
        AppTools.hideGifDialog();
       Sneaker.with(this)
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .sneakError();



    }

    private class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.WalletHolder> {
        ArrayList<HashMap<String, String>> data = new ArrayList();

        public CategoryAdapter(ArrayList<HashMap<String, String>> programlist) {
            this.data = programlist;
        }

        @Override
        public CategoryAdapter.WalletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.grid_layout, parent, false);
            return new CategoryAdapter.WalletHolder(view);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public void onBindViewHolder(final CategoryAdapter.WalletHolder holder, final int position) {
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(MainActivity.this, SubcategoryActivity.class);
                    i.putExtra("id", data.get(position).get("id"));
                    startActivity(i);
                }
            });
          /*  if (((String) ((HashMap) this.data.get(position)).get("icon")).equalsIgnoreCase("")) {
                Picasso.get().load((int) R.mipmap.logo).resize(815,315).into(holder.iv_Imageview);
              //  progressBar.setVisibility(View.VISIBLE);
            } else {*/
            Picasso.get().load(AppUrls.BASECategoryimagepath + data.get(position).get("icon")).into(holder.iv_Imageview);
            holder.CateName.setText(data.get(position).get("name"));

            /* }*/

        }

        public class WalletHolder extends RecyclerView.ViewHolder {
            TextView CateName;
            CardView cardView;
            ImageView iv_Imageview;

            public WalletHolder(View itemView) {
                super(itemView);
                // tvCategory = itemView.findViewById(R.id.tv_Category);
                cardView = itemView.findViewById(R.id.cv_View);
                iv_Imageview = itemView.findViewById(R.id.iv_Imageview);
                CateName = itemView.findViewById(R.id.CateName);
                // newregistrationi = itemView.findViewById(R.id.newregistrationi);
            }
        }
    }

    private class HorizontalCategoryAdapter extends RecyclerView.Adapter<HorizontalCategoryAdapter.WalletHolder> {
        ArrayList<HashMap<String, String>> data = new ArrayList();

        public HorizontalCategoryAdapter(ArrayList<HashMap<String, String>> programlist) {
            this.data = programlist;
        }

        @Override
        public HorizontalCategoryAdapter.WalletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.horizontal_category_ui, parent, false);
            return new HorizontalCategoryAdapter.WalletHolder(view);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public void onBindViewHolder
                (final HorizontalCategoryAdapter.WalletHolder holder, final int position) {
           /* holder.cv_View.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this,newActivity.class));
                }
            });*/

            Picasso.get().load(AppUrls.BASEServicesimagepath + data.get(position).get("icon")).into(holder.iv_Imageview);

            holder.tvCategoriesNamee.setText(data.get(position).get("name"));

        }

        public class WalletHolder extends RecyclerView.ViewHolder {
            TextView tvCategoriesNamee;
            ImageView iv_Imageview;
            CardView cv_View;

            public WalletHolder(View itemView) {
                super(itemView);
                iv_Imageview = itemView.findViewById(R.id.iv_Imageview);
                cv_View = itemView.findViewById(R.id.cv_View);
                tvCategoriesNamee = itemView.findViewById(R.id.tvCategoriesNamee);
            }
        }
    }

    private class GridCategoryAdapter extends RecyclerView.Adapter<GridCategoryAdapter.WalletHolder> {
        ArrayList<HashMap<String, String>> data = new ArrayList();

        public GridCategoryAdapter(ArrayList<HashMap<String, String>> programlist) {
            this.data = programlist;
        }

        @Override
        public GridCategoryAdapter.WalletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.recent_grid, parent, false);
            return new GridCategoryAdapter.WalletHolder(view);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public void onBindViewHolder(final GridCategoryAdapter.WalletHolder holder, final int position) {
            Picasso.get().load(AppUrls.BASECategoryimagepath + data.get(position).get("icon")).into(holder.iv_Imageview);
            holder.CateName.setText(data.get(position).get("name"));
        }


        public class WalletHolder extends RecyclerView.ViewHolder {
            TextView CateName;
            ImageView iv_Imageview;
            CardView cv_View;

            public WalletHolder(View itemView) {
                super(itemView);

                iv_Imageview = itemView.findViewById(R.id.iv_Imageview);
                CateName = itemView.findViewById(R.id.CateName);
                cv_View = itemView.findViewById(R.id.cv_View);
            }
        }
    }

    public class ImageSliderPagerAdapter extends PagerAdapter {

        LayoutInflater inflater;
        ArrayList<HashMap<String, String>> imageList;

        public ImageSliderPagerAdapter(ArrayList<HashMap<String, String>> imageList) {
            try {

                this.imageList = imageList;
                this.inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        public int getCount() {
            Log.v("siz", String.valueOf(imageList.size()));
            return imageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View itemView = inflater.inflate(R.layout.adapter, container, false);

            Log.v("MyImage", "url123" + imageList);

            ImageView iv = itemView.findViewById(R.id.iv);
            VideoView videoview = itemView.findViewById(R.id.videoview);
            // Picasso.get().load(imageList.get(position).get("link")).into(iv);

          /*  Glide.with(mActivity)
                    .load(imageList.get(position).get("link"))
                    .into(iv);*/
          /*  Uri uri = Uri.parse(imageList.get(position).get("link"));
            videoview.setVideoURI(uri);
            videoview.start();*/

           /* String uriPath = "android.resource://"+getPackageName()+"/"+R.raw.aha_hands_only_cpr_english;
            Uri uri = Uri.parse(uriPath);
            videoview.setVideoURI(uri);*/
            //  imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            // if (imageList.get(position).getImage().length() > 0){
            Log.v("MyImage", "url" + imageList.get(position));
            //setImgPicasso(imageList.get(position).get("image"), mActivity, imageView);

/*
            Glide.with(context).load(imageList.get(position).getImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
*/

            //}

            container.addView(itemView);
            return itemView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    public class ViewPagerAdapterMiddleUpper extends PagerAdapter {

        private Context context;
        private LayoutInflater layoutInflater;
        private Integer[] images = {R.drawable.pagerimage, R.drawable.flag};

        public ViewPagerAdapterMiddleUpper(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.item_pagermiddle_upper, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imagemiddleUpper);
            imageView.setImageResource(images[position]);


            ViewPager vp = (ViewPager) container;
            vp.addView(view, 0);
            return view;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            ViewPager vp = (ViewPager) container;
            View view = (View) object;
            vp.removeView(view);

        }
    }

    public class ViewPagerAdapter extends PagerAdapter {

        private Context context;
        private LayoutInflater layoutInflater;
        private Integer[] images = {R.drawable.usr, R.drawable.flag};

        public ViewPagerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.itemviewpagerservice, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageViewusr);
            imageView.setImageResource(images[position]);


            ViewPager vp = (ViewPager) container;
            vp.addView(view, 0);
            return view;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            ViewPager vp = (ViewPager) container;
            View view = (View) object;
            vp.removeView(view);

        }
    }

    public class ViewPagerAdapterSpolight extends PagerAdapter {

        private Context context;
        private LayoutInflater layoutInflater;
        private Integer[] images = {R.drawable.iv_restaurents, R.drawable.flag};

        public ViewPagerAdapterSpolight(Context context) {
            this.context = context;
        }

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.item_pagerspotlight, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageViewspot);
            imageView.setImageResource(images[position]);


            ViewPager vp = (ViewPager) container;
            vp.addView(view, 0);
            return view;

        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            ViewPager vp = (ViewPager) container;
            View view = (View) object;
            vp.removeView(view);

        }
    }
}