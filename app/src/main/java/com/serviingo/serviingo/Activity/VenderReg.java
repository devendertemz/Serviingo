package com.serviingo.serviingo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.irozon.sneaker.Sneaker;
import com.rjesture.startupkit.AppTools;
import com.serviingo.serviingo.Adapterr.CategorySpinnerAdapter;
import com.serviingo.serviingo.Adapterr.CitySpinnerAdapter;
import com.serviingo.serviingo.Adapterr.StateSpinnerAdapter;
import com.serviingo.serviingo.CommonControlerApi.CategoriesPresenter;
import com.serviingo.serviingo.CommonControlerApi.VandorRegPresenter;
import com.serviingo.serviingo.Database.AppSettings;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.Rtrofit.ApiClientt;
import com.serviingo.serviingo.model.CategoryModel;
import com.serviingo.serviingo.model.CityModel;
import com.serviingo.serviingo.model.StateModel;
import com.serviingo.serviingo.modelrepo.Responsee.VandorRepo;
import com.serviingo.serviingo.modelrepo.request.VandorRegs;
import com.serviingo.serviingo.utils.AppUtils;
import com.serviingo.serviingo.view.BaseActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.serviingo.serviingo.R.drawable.globalloader;

public class VenderReg extends BaseActivity implements View.OnClickListener, CategoriesPresenter.CategoriesView, VandorRegPresenter.VandorRegView {

    ImageView ivback, ivNav, referImageview;
    String pinterest_linklink, facebook_linklink, linkedin_linklink, youtube_linklink, insta_linklink;
    public static ScrollView scrollView;
    RelativeLayout rlDashBoard, rr_home, rr_category, rr_recharge, rrInsurance,
            rr_About, rr_Contact, rr_register, rrsignin;
    StateModel stateModel;
    ArrayList<StateModel> stateModelList;
    ArrayList<CityModel> cityModelList;
    ArrayList<CategoryModel> categoryModelArrayList;

    Spinner spinner_state, spinner_city, SpinnerCategory;
    private CategoriesPresenter presenter;
    EditText ed_fistname, ed_lastname, ed_email, ed_mobile, ed_password, ed_cpassword;

    String state_id, city_id, category_id;
    VandorRegPresenter vandorRegPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vender_register);

        findViewById();
    }

    private void findViewById() {


        ivback = findViewById(R.id.ivBack);
        ivNav = findViewById(R.id.ivNav);
        scrollView = findViewById(R.id.scroll_side_menu);
        rlDashBoard = findViewById(R.id.rlDashBoard);
        rr_home = findViewById(R.id.rr_home);
        rr_category = findViewById(R.id.rr_category);
        rr_recharge = findViewById(R.id.rr_recharge);
        rrInsurance = findViewById(R.id.rrInsurance);
        rr_About = findViewById(R.id.rr_About);
        rr_Contact = findViewById(R.id.rr_Contact);
        rr_register = findViewById(R.id.rr_register);
        rrsignin = findViewById(R.id.rrsignin);

        spinner_state = findViewById(R.id.spinner_state);
        spinner_city = findViewById(R.id.spinner_city);
        SpinnerCategory = findViewById(R.id.SpinnerCategory);

        ed_fistname = findViewById(R.id.ed_fistname);
        ed_lastname = findViewById(R.id.ed_lastname);
        ed_email = findViewById(R.id.ed_email);
        ed_mobile = findViewById(R.id.ed_mobile);
        ed_password = findViewById(R.id.ed_password);
        ed_cpassword = findViewById(R.id.ed_cpassword);


        setOnclickistener();
        presenter = new CategoriesPresenter(this);

        presenter.CategorieUser();
        vandorRegPresenter = new VandorRegPresenter(this);
        getState();
    }

    private void setOnclickistener() {

        ivNav.setOnClickListener(this);
        ivback.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.ivPinterest:
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

            case R.id.ivNav:
                if (AppSettings.getString(AppSettings.id).isEmpty()) {
                    if (scrollView.getVisibility() == View.VISIBLE) {
                        AppUtils.collapse(scrollView);
                        break;
                    }
                    AppUtils.expand(scrollView);
                } else {
                    if (scrollView.getVisibility() == View.VISIBLE) {
        /* ivDrop.setVisibility( View.GONE );//scrollView.setVisibility(View.GONE);
         view.setVisibility( View.GONE );*/
                        AppUtils.collapse(scrollView);
                        break;
                    }
     /*ivDrop.setVisibility( View.VISIBLE );
     view.setVisibility( View.VISIBLE );*/
                    AppUtils.expand(scrollView);
                }

                break;
            case R.id.ivBack:
                onBackPressed();
                break;
            case R.id.rlDashBoard:
//samepage
                break;
            case R.id.rr_home:
//My Booking
                break;
            case R.id.rr_category:
//My Profile
                break;
            case R.id.rr_recharge:
//My Wallet
                break;
            case R.id.rrInsurance:
//Logout
                break;
            case R.id.rr_About:
                startActivity(new Intent(this, AboutUs.class));
                break;
            case R.id.rr_Contact:
                startActivity(new Intent(this, ContactUsActivity.class));
                break;
            case R.id.rr_register:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
            case R.id.rrsignin:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void getState() {
        AppTools.showGifDialog(mActivity, globalloader);

        stateModelList = new ArrayList<StateModel>();
        stateModel = new StateModel();


        Call<ResponseBody> bodyCall = ApiClientt.getUserService().GetState();

        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                // Log.e("response", String.valueOf(response.body().toString()));

                //Toast.makeText(VenderReg.this, response.code(), Toast.LENGTH_SHORT).show();
                // Toast.makeText(VenderReg.this, response.body().getLocale()+"", Toast.LENGTH_SHORT).show();

                String s = null;


                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {
                        s = response.body().string();
                        Log.e("response", s);
                        stateModel.setId("00");
                        stateModel.setName("Select State");

                        stateModelList.add(0, stateModel);

                        JSONObject jsonObject = new JSONObject(s);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        JSONArray jsonArray = jsonObject1.getJSONArray("states");
                        for (int i = 0; i <= jsonArray.length(); i++) {

                            JSONObject jsonObject2 = jsonArray.getJSONObject(i);

                            stateModel = new StateModel();
                            stateModel.setId(jsonObject2.getString("id"));
                            stateModel.setName(jsonObject2.getString("name"));
                            stateModel.setCountry_id(jsonObject2.getString("country_id"));

                            stateModelList.add(stateModel);
                            AppTools.hideGifDialog();

                            //   Toast.makeText(VenderReg.this, jsonObject2.getString("id") + "", Toast.LENGTH_SHORT).show();

                        }
                        /*
                         */
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                    StateSpinnerAdapter coinSpinnerAdapter = new StateSpinnerAdapter(getApplicationContext(), stateModelList);
                    spinner_state.setAdapter(coinSpinnerAdapter);
                    spinner_state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                            //  spinnertextview.setTextColor(Color.WHITE);
                            state_id = stateModelList.get(position).getId();


                            GetCity(state_id);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //  loadingDialogs.dismissDialog();
                Toast.makeText(VenderReg.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void GetCity(String id) {
        cityModelList = new ArrayList<>();
        CityModel stateModel = new CityModel();
        ;
        Call<ResponseBody> bodyCall = ApiClientt.getUserService().GetCity(id);

        bodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.e("responseee", String.valueOf(response.code()));
//       Toast.makeText(VenderReg.this, response.code(), Toast.LENGTH_SHORT).show();
                // Toast.makeText(VenderReg.this, response.body().getLocale()+"", Toast.LENGTH_SHORT).show();

                String s = null;
                cityModelList.clear();

                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    try {
                        s = response.body().string();
                        Log.e("response", s);
                        stateModel.setName("Select City");
                        stateModel.setId("00");

                        cityModelList.add(0, stateModel);

                        Toast.makeText(VenderReg.this, s + "", Toast.LENGTH_SHORT).show();
                        JSONObject jsonObject = new JSONObject(s);
                        JSONObject jsonObject1 = jsonObject.getJSONObject("data");
                        JSONArray jsonArray = jsonObject1.getJSONArray("cities");
                        for (int i = 0; i <= jsonArray.length(); i++) {
                            JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                            CityModel stateModel = new CityModel();
                            stateModel.setId(jsonObject2.getString("id"));
                            stateModel.setName(jsonObject2.getString("name"));
                            cityModelList.add(stateModel);
                        }
                        /*
                         */
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                    AppTools.hideGifDialog();

                    CitySpinnerAdapter coinSpinnerAdapter = new CitySpinnerAdapter(getApplicationContext(), cityModelList);
                    spinner_city.setAdapter(coinSpinnerAdapter);
                    spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


                            city_id = cityModelList.get(position).getId();


                            // GetCity(state_id);

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //  loadingDialogs.dismissDialog();
                Toast.makeText(VenderReg.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();

            }
        });


    }


    @Override
    public void onLoginError(String message) {
        Log.e("error", message);
        Toast.makeText(this, message.toString() + "", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoginSuccess(ResponseBody response, String message) {
        categoryModelArrayList = new ArrayList<>();

        try {
            CategoryModel categoryModel1 = new CategoryModel("00","Select Category");

            categoryModelArrayList.add(0,categoryModel1);

            JSONObject jsonObject = new JSONObject(response.string());

            JSONObject jsonObject1 = jsonObject.getJSONObject("data");


            JSONArray jsonArray = jsonObject1.getJSONArray("categories");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                CategoryModel categoryModel = new CategoryModel(jsonObject2.getString("id"), jsonObject2.getString("name"),
                        jsonObject2.getString("icon"), jsonObject2.getString("slug"), jsonObject2.getString("reviews_heading"), jsonObject2.getString("reviews_title"), jsonObject2.getString("faq_title"),
                        jsonObject2.getString("short_description"), jsonObject2.getString("about_category"), jsonObject2.getInt("status"), jsonObject2.getString("meta_title")
                        , jsonObject2.getString("meta_keywords"), jsonObject2.getString("meta_description")
                        , jsonObject2.getInt("avg_rating"), jsonObject2.getString("banner_title")
                        , jsonObject2.getString("banner_description"));

                categoryModelArrayList.add(categoryModel);
            }


        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        Log.e("onLoginSuccess", String.valueOf(response));
        CategorySpinnerAdapter categorySpinnerAdapter = new CategorySpinnerAdapter(getApplication(), categoryModelArrayList);
        SpinnerCategory.setAdapter(categorySpinnerAdapter);
        SpinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category_id = categoryModelArrayList.get(position).id;


                // GetCity(state_id);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Toast.makeText(this, message + "", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLoginFailure(Throwable t) {
        Toast.makeText(this, t.getMessage() + "", Toast.LENGTH_SHORT).show();
        Log.e("onLoginFailure", t.getMessage());


    }

    public void onClick_vanderReg(View view) {
        vandroreg();
    }

    private void vandroreg() {

        String fname = ed_fistname.getText().toString().trim();
        String lname = ed_lastname.getText().toString().trim();
        String email = ed_email.getText().toString().trim();
        String mobile = ed_mobile.getText().toString().trim();
        String password = ed_password.getText().toString().trim();
        String cpassword = ed_cpassword.getText().toString().trim();
        if (fname.isEmpty()) {
            Sneaker.with(this)
                    .setTitle("Enter your First name!")
                    .setMessage("")
                    .sneakError();

        } else if (lname.isEmpty()) {
            Sneaker.with(this)
                    .setTitle("Enter your last name!")
                    .setMessage("")
                    .sneakError();
        } else if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Sneaker.with(this)
                    .setTitle("Enter your Valid email!")
                    .setMessage("")
                    .sneakError();
        } else if (mobile.length() != 10) {
            Sneaker.with(this)
                    .setTitle("Please enter your 10 digit of mobile!")
                    .setMessage("")
                    .sneakError();
        } else if (state_id.equalsIgnoreCase("00")) {
            Sneaker.with(this)
                    .setTitle("Select  State!")
                    .setMessage("")
                    .sneakError();
        } else if (city_id.equalsIgnoreCase("00")) {
            Sneaker.with(this)
                    .setTitle("Select  City!")
                    .setMessage("")
                    .sneakError();
        } else if (password.isEmpty()) {
            Sneaker.with(this)
                    .setTitle("Enter password !")
                    .setMessage("")
                    .sneakError();
        } else if (!cpassword.equals(password)) {
            Sneaker.with(this)
                    .setTitle("confirm password not match!")
                    .setMessage("")
                    .sneakError();
        } else if (category_id.equalsIgnoreCase("00")) {
            Sneaker.with(this)
                    .setTitle("Select Category !")
                    .setMessage("")
                    .sneakError();
        } else {
            Log.d("checkkkkkk", city_id + "\n" + state_id + "\n" + category_id);

            AppTools.showGifDialog(mActivity, globalloader);

            VandorRegs vandorRegs = new VandorRegs(fname, lname, state_id, city_id, category_id, email, mobile, password, cpassword);

            vandorRegPresenter.VandorReg(vandorRegs);

        }


    }

    @Override
    public void onVandorRegError(String message) {
        AppTools.hideGifDialog();

        Sneaker.with(this)
                .setTitle(message)
                .setMessage("")
                .sneakError();

    }

    @Override
    public void onVandorRegSuccess(VandorRepo response, String message) {
        //    Toast.makeText(this, message+"", Toast.LENGTH_SHORT).show();
        Sneaker.with(this)
                .setTitle(response.getMessage())
                .setMessage("")
                .sneakError();
        AppTools.hideGifDialog();

        Intent intent = new Intent(VenderReg.this, MainActivity.class);
        Toast.makeText(this, response.getMessage() + "", Toast.LENGTH_LONG).show();
        startActivity(intent);


    }

    @Override
    public void onVandorRegFailure(Throwable t) {
        AppTools.hideGifDialog();

        Toast.makeText(this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();
        Log.e("onVandorRegFailure", t.getLocalizedMessage());
        Sneaker.with(this)
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .sneakError();
    }
}