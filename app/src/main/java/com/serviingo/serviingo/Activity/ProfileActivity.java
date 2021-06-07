package com.serviingo.serviingo.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.irozon.sneaker.Sneaker;
import com.rjesture.startupkit.AppTools;
import com.serviingo.serviingo.Adapterr.CitySpinnerAdapter;
import com.serviingo.serviingo.Adapterr.StateSpinnerUpdateAdapter;
import com.serviingo.serviingo.CommonControlerApi.DoMyprofilePresenter;
import com.serviingo.serviingo.CommonControlerApi.DoMyprofileUpdatePresenter;
import com.serviingo.serviingo.Database.AppSettings;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.Rtrofit.ApiClientt;
import com.serviingo.serviingo.model.CategoryModel;
import com.serviingo.serviingo.model.CityModel;
import com.serviingo.serviingo.model.StateModel;
import com.serviingo.serviingo.modelrepo.Responsee.Profile_Repo;
import com.serviingo.serviingo.modelrepo.request.UpdateProfile_request;
import com.serviingo.serviingo.storage.SharedPrefManager;
import com.serviingo.serviingo.utils.AppUrls;
import com.serviingo.serviingo.utils.AppUtils;
import com.serviingo.serviingo.view.BaseActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.serviingo.serviingo.R.drawable.globalloader;

public class ProfileActivity extends BaseActivity implements View.OnClickListener, DoMyprofilePresenter.DoMyprofileView, DoMyprofileUpdatePresenter.DoMyprofileUpdateView {

    TextView btn_addresssDetails;
    ImageView imageView, ivBack, ivNav;
    RelativeLayout rlDashBoard, rr_home, rr_category, rr_recharge, rrInsurance,
            rr_About, rr_Contact, rr_register, rrsignin;
    public static ScrollView scrollView;
    DoMyprofilePresenter presenter;
    CircleImageView circleImageView;

    EditText etprofile_name, etprofileEmail, etprofilePhone, ed_houseno, ed_nearby, ed_pincode;


    StateModel stateModel;
    ArrayList<StateModel> stateModelList;
    ArrayList<CityModel> cityModelList;

    Spinner spinner_state, spinner_city, genderSpinner;
    String state_id, city_id, genderr;
    String[] gender = {"Select Gender", "Male", "Female", "Transgender "};//array of strings used to populate the spinner
    DoMyprofileUpdatePresenter doMyprofileUpdatePresenter;


    String genderid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profileactivity);
        AppTools.showGifDialog(mActivity, globalloader);

        setui();
        DoMyprofile();
        getState();

    }

    private void DoMyprofile() {
        presenter = new DoMyprofilePresenter(this);
        presenter.DoMyprofil(this);

    }

    private void setui() {

        btn_addresssDetails = findViewById(R.id.btn_addresssDetails);
        doMyprofileUpdatePresenter = new DoMyprofileUpdatePresenter(this);


        ivNav = findViewById(R.id.ivNav);
        ivBack = findViewById(R.id.ivBack);
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


        etprofile_name = findViewById(R.id.etprofile_name);
        etprofileEmail = findViewById(R.id.etprofileEmail);
        etprofilePhone = findViewById(R.id.etprofilePhone);
        ed_houseno = findViewById(R.id.ed_houseno);
        ed_nearby = findViewById(R.id.ed_nearby);
        ed_pincode = findViewById(R.id.ed_pincode);

        spinner_state = findViewById(R.id.spinner_state);
        spinner_city = findViewById(R.id.spinner_city);
        genderSpinner = findViewById(R.id.genderSpinner);


        circleImageView = findViewById(R.id.ivProfilePic);
        setOnClickListener();

        ArrayAdapter<String> aa = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gender);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//Setting the ArrayAdapter data on the Spinner
        genderSpinner.setAdapter(aa);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                genderr = parent.getItemAtPosition(position).toString().trim();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void setOnClickListener() {

        btn_addresssDetails.setOnClickListener(this);


        rlDashBoard.setOnClickListener(this);
        rr_home.setOnClickListener(this);
        rr_category.setOnClickListener(this);
        rr_recharge.setOnClickListener(this);
        rrInsurance.setOnClickListener(this);
        rr_About.setOnClickListener(this);
        rr_Contact.setOnClickListener(this);
        rr_register.setOnClickListener(this);
        rrsignin.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivNav.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_addresssDetails:
                updateProfile();

                break;

            case R.id.ivNav:
                if (AppSettings.getString(AppSettings.id).isEmpty()) {
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
    public void onDoMyprofileError(String message) {
        AppTools.hideGifDialog();

        Log.e("onDoMyprofileError", message);

    }

    @Override
    public void onDoMyprofileSuccess(Profile_Repo response, String message) {
        AppTools.hideGifDialog();

        if (message.equalsIgnoreCase("ok")) {
            Picasso.get().load(response.getData().getProfile().getProfilePhotoUrl()).into(circleImageView);
            etprofile_name.setText(response.getData().getProfile().getName());

            etprofileEmail.setText(response.getData().getProfile().getEmail());
            etprofilePhone.setText(response.getData().getProfile().getMobileNumber());
            genderid = response.getData().getProfile().getGender().trim();


            if (genderid.equalsIgnoreCase("Male")) {
                genderSpinner.setSelection(1);

            } else if (genderid.equalsIgnoreCase("Female")) {

                genderSpinner.setSelection(2);

            } else if (genderid.equalsIgnoreCase("Transgender")) {

                genderSpinner.setSelection(3);
            } else {

                genderSpinner.setSelection(0);
            }
            state_id=response.getData().getProfile().getStateId().trim();

            city_id=response.getData().getProfile().getCityId().trim();
            ed_houseno.setText(response.getData().getProfile().getAddress().trim());
            ed_nearby.setText(response.getData().getProfile().getLandmark().trim());
            ed_pincode.setText(response.getData().getProfile().getPincode().trim());


        }


    }

    @Override
    public void onDoMyprofileFailure(Throwable t) {
        AppTools.hideGifDialog();

        Log.e("onDoMyprofileFailure", t.getLocalizedMessage());

    }


    private void getState() {

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
                        AppTools.hideGifDialog();

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


                            //   Toast.makeText(VenderReg.this, jsonObject2.getString("id") + "", Toast.LENGTH_SHORT).show();

                        }
                        /*
                         */
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                    StateSpinnerUpdateAdapter coinSpinnerAdapter = new StateSpinnerUpdateAdapter(getApplicationContext(), stateModelList,state_id);

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
                AppTools.hideGifDialog();

                Toast.makeText(ProfileActivity.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();

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
                    AppTools.hideGifDialog();

                    try {
                        s = response.body().string();
                        Log.e("response", s);
                        stateModel.setName("Select City");
                        stateModel.setId("00");

                        cityModelList.add(0, stateModel);

                        Toast.makeText(ProfileActivity.this, s + "", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ProfileActivity.this, t.getLocalizedMessage() + "", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onDoMyprofileUpdateError(String message) {
        Sneaker.with(this)
                .setTitle(message)
                .setMessage("")
                .sneakError();
        AppTools.hideGifDialog();


    }

    @Override
    public void onDoMyprofileUpdateSuccess(Profile_Repo response, String message) {
     /*   Sneaker.with(this)
                .setTitle(message)
                .setMessage("")
                .sneakError();*/
        AppTools.hideGifDialog();

        if (message.equalsIgnoreCase("ok")) {

            if (message.equalsIgnoreCase("ok")) {

                Toast.makeText(this, response.getMessage() + "", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, MainActivity.class));
            }


        }

    }

    @Override
    public void onDoMyprofileUpdateFailure(Throwable t) {
        Sneaker.with(this)
                .setTitle(t.getLocalizedMessage())
                .setMessage("")
                .sneakError();
        AppTools.hideGifDialog();

    }


    private void updateProfile() {
        //EditText etprofile_name,etprofileEmail,etprofilePhone,ed_houseno,ed_nearby,ed_pincode;

        String name = etprofile_name.getText().toString().trim();
        String email = etprofileEmail.getText().toString().trim();
        String phone = etprofilePhone.getText().toString().trim();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Sneaker.with(this)
                    .setTitle("Enter your Valid email!")
                    .setMessage("")
                    .sneakError();
        } else if (genderr.equalsIgnoreCase("Select Gender")) {
            Sneaker.with(this)
                    .setTitle("Select Gender!")
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
        } else {
            AppTools.showGifDialog(mActivity, globalloader);

            UpdateProfile_request updateProfile_request = new UpdateProfile_request(name, genderr, email, phone, state_id, city_id,
                    ed_houseno.getText().toString().trim(), ed_nearby.getText().toString().trim(), ed_pincode.getText().toString().trim());
            doMyprofileUpdatePresenter.DoMyprofil(ProfileActivity.this, updateProfile_request);
        }

    }
}
