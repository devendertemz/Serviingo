package com.serviingo.serviingo.Activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.rjesture.startupkit.ApiStrings;
import com.rjesture.startupkit.AppTools;
import com.serviingo.serviingo.Common.Preferences;
import com.serviingo.serviingo.Database.AppSettings;
import com.serviingo.serviingo.R;
import com.serviingo.serviingo.utils.AppUrls;
import com.serviingo.serviingo.utils.AppUtils;
import com.serviingo.serviingo.view.BaseActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static com.rjesture.startupkit.ApiStrings.apiErrorException;
import static com.rjesture.startupkit.ApiStrings.defResponse;
import static com.rjesture.startupkit.AppTools.hideGifDialog;
import static com.rjesture.startupkit.AppTools.setLog;
import static com.rjesture.startupkit.AppTools.showToast;
import static com.serviingo.serviingo.R.drawable.globalloader;

public class Lowcontact extends BaseActivity implements View.OnClickListener {
    public static ArrayList<HashMap<String, String>> packgeCategoryArrayList = new ArrayList();
    public static ArrayList<HashMap<String, String>> packageArray = new ArrayList();
    public static ScrollView scrollView;
    String selectedId = "";
    RelativeLayout rlDashBoard, rr_home, rr_category, rr_recharge, rrInsurance,
            rr_About, rr_Contact, rr_register, rrsignin;
    ImageView ivBack, ivNav;
    TextView tvHeading;
    Preferences preferences;
    RecyclerView horizontalrecy, verticalrecy;
    PackagesAdapter pacakage;
    LinearLayoutManager linearLayoutManager, llManager;
    Intent i = getIntent();
    ArrayList<HashMap<String, String>> recent_data = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lowcontact);
        setui();
    }

    private void setui() {
        preferences = new Preferences(mActivity);
        verticalrecy = findViewById(R.id.verticalrecy);
        linearLayoutManager = (new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        verticalrecy.setLayoutManager(linearLayoutManager);
        verticalrecy.setHasFixedSize(true);

        horizontalrecy = findViewById(R.id.horizontalrecy);
        llManager = (new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        horizontalrecy.setLayoutManager(llManager);
        horizontalrecy.setHasFixedSize(true);





       /* tvPrice = findViewById(R.id.tvPrice);
        tvCutPrice = findViewById(R.id.tvCutPrice);
        tvMainPrice = findViewById(R.id.tvMainPrice);
        tvgreen = findViewById(R.id.tvgreen);
        text = findViewById(R.id.text);*/
        ivNav = findViewById(R.id.ivNav);
        ivBack = findViewById(R.id.ivBack);
        tvHeading = findViewById(R.id.tvHeading);
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
        setOnClickListener();
        //getTQ();
       getSubCategoryDetailsApi();
    }
   /* private void addKeyValue(String val, String id) {
        HashMap<String, String> addList = new HashMap();
        addList.put("id", id);
        addList.put("val", val);
        recent_data.add(addList);
    }*/

    private void getTQ() {
        AppTools.showGifDialog(mActivity, globalloader);
        //String url = AppUrls.getMakeYourOwnPackage + preferences.get(AppSettings.subcatId);
        String url = AppUrls.getMakeYourOwnPackage +" preferences.get(AppSettings.subcatId)";
        setLog("getMakeYourOwnPackage", url);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseMakeYourJsonResponse(response);
                    }

                    @Override
                    public void onError(ANError error) {
                        setLog("data", error.getErrorBody());
                        setLog("data", error.getErrorDetail());
                    }
                });
    }

    private void parseMakeYourJsonResponse(JSONObject response) {
        AppTools.hideGifDialog();
        try {
            String message = response.getString("message");
            Toast.makeText(this, "" + message, Toast.LENGTH_SHORT).show();
            if (response.getString("status").equals("true")) {
                JSONObject jsonObject = response.getJSONObject("data");
                JSONObject services = jsonObject.getJSONObject("services");
                String id = services.getString("id");
                String category_id = services.getString("category_id");
                String sub_category_id = services.getString("sub_category_id");
                String sub_sub_category_id = services.getString("sub_sub_category_id");
                String name = services.getString("name");
                //tvwallet.setText(name);
                String amount = services.getString("amount");
                //tvCutPrice.setText(amount);
                //  tvCutPrice.setPaintFlags(tvCutPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                String discount = services.getString("discount");
                //  tvMainPrice.setText(discount);
                String after_discount = services.getString("after_discount");
               /* if(after_discount!=null){
                    tvPrice.setText(amount);
                }else{
                    tvPrice.setText(after_discount);
                }*/
                String status = services.getString("status");
                String icon = services.getString("icon");
//                 Picasso.get().load(AppUrls.BASEServicesimagepath + icon).into(imageView);
                //  AppTools.setImgPicasso(AppUrls.BASEServicesimagepath + icon,mActivity,imageView);

                String detail_image = services.getString("detail_image");
                String description = services.getString("description");
                String detail_video = services.getString("detail_video");
                String created_at = services.getString("created_at");
                String updated_at = services.getString("updated_at");
                String deleted_at = services.getString("deleted_at");

            } else {
            }
        } catch (Exception e) {
            setLog("error", String.valueOf(e));
        }
    }

    private void getSubCategoryDetailsApi() {
        AppTools.showGifDialog(mActivity, globalloader);
        ApiStrings.setApiString("getSubCategoryDetails");
      //  String url = AppUrls.getSubCategoryDetails + preferences.get(AppSettings.subcatId);
        String url = AppUrls.getSubCategoryDetails +"30";
        setLog(ApiStrings.apiUrl, url);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parsegetSubCategoryDetailsdApi(response);
                    }
                    @Override
                    public void onError(ANError error) {

                        AppUtils.hideDialog();
                        // handle error
                        if (error.getErrorCode() != 0) {
                            showToast(mActivity, defResponse);
                            setLog(ApiStrings.apiErrorCode, "onError errorCode : " + error.getErrorCode());
                            setLog(ApiStrings.apiErrorBody, "onError errorBody : " + error.getErrorBody());
                            setLog(ApiStrings.apiErrorDetail, "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            showToast(mActivity, defResponse);
                            setLog(apiErrorException, error.getMessage());

                        }
                    }
                });
    }

    private void parsegetSubCategoryDetailsdApi(JSONObject response) {
        hideGifDialog();
        Log.d(ApiStrings.apiResponse, response.toString());
        try {

            if (response.getString("status").equals("true")) {
                String message = response.getString("message");
                Log.v("resp", response.toString());
                packgeCategoryArrayList.clear();
                packageArray.clear();

                if (response.getString("status").equals("true")) {
                    JSONObject jsonObject = response.getJSONObject("data");
                    JSONArray package_categories = jsonObject.getJSONArray("package_categories");
                    for (int i = 0; i < package_categories.length(); i++) {
                        JSONObject productobject = package_categories.getJSONObject(i);
                        HashMap<String, String> addList = new HashMap();
                        if (i == 0) {
                            selectedId = productobject.getString("id");
                         //   selectedId="13";
                        }
                        addList.put("id", productobject.getString("id"));
                        addList.put("subcat_id", productobject.getString("subcat_id"));
                        addList.put("subsub_category_id", productobject.getString("subsub_category_id"));
                        addList.put("name", productobject.getString("name"));
                        packgeCategoryArrayList.add(addList);
                    }
                    if (package_categories.length() == 0) {// this
                        HashMap<String, String> addList = new HashMap();
                        selectedId = "0";
                        addList.put("id", "0");
                        addList.put("subcat_id", "0");
                        addList.put("subsub_category_id", "0");
                        addList.put("name", "Make your Package");
                        packgeCategoryArrayList.add(addList);


                        HashMap<String, String> addLists = new HashMap();
                        addLists.put("id", "0");
                        addLists.put("slug", "0");
                        addLists.put("category_id", "0");
                        addLists.put("sub_category_id", "0");
                        addLists.put("sub_sub_category_id", "0");
                        addLists.put("package_category_id", "0");
                        addLists.put("name", "0");
                        addLists.put("video","0");
                        addLists.put("service_id", "0");
                        addLists.put("amount", "0");
                        addLists.put("discount", "0");
                        addLists.put("after_discount", "0");
                        addLists.put("about_package", "0");
                        addLists.put("status", "0");
                        addLists.put("active", "0");
                        addLists.put("active_till","0");
                        addLists.put("deal_slug","0");
                        addLists.put("vendor_commission", "0");
                        addLists.put("timer", "0");
                        addLists.put("created_at", "0");
                        addLists.put("updated_at", "0");
                        packageArray.add(addLists);


                    }// this
                    else {// this
                    JSONArray packages = jsonObject.getJSONArray("packages");
                    for (int i = 0; i < packages.length(); i++) {
                        JSONObject productobject = packages.getJSONObject(i);
                        HashMap<String, String> addList = new HashMap();
                        addList.put("id", productobject.getString("id"));
                        addList.put("slug", productobject.getString("slug"));
                        addList.put("category_id", productobject.getString("category_id"));
                        addList.put("sub_category_id", productobject.getString("sub_category_id"));
                        addList.put("sub_sub_category_id", productobject.getString("sub_sub_category_id"));
                        addList.put("package_category_id", productobject.getString("package_category_id"));
                        addList.put("name", productobject.getString("name"));
                        addList.put("video", productobject.getString("video"));
                        addList.put("service_id", productobject.getString("service_id"));
                        addList.put("amount", productobject.getString("amount"));
                        addList.put("discount", productobject.getString("discount"));
                        addList.put("after_discount", productobject.getString("after_discount"));
                        addList.put("about_package", productobject.getString("about_package"));
                        addList.put("status", productobject.getString("status"));
                        addList.put("active", productobject.getString("active"));
                        addList.put("active_till", productobject.getString("active_till"));
                        addList.put("deal_slug", productobject.getString("deal_slug"));
                        addList.put("vendor_commission", productobject.getString("vendor_commission"));
                        addList.put("timer", productobject.getString("timer"));
                        addList.put("created_at", productobject.getString("created_at"));
                        addList.put("updated_at", productobject.getString("updated_at"));
                        packageArray.add(addList);
                    }


                    JSONArray deals = jsonObject.getJSONArray("deals");
                    for (int i = 0; i < deals.length(); i++) {
                        JSONObject productobject = deals.getJSONObject(i);
                        HashMap<String, String> addList = new HashMap();
                        addList.put("id", productobject.getString("id"));
                        addList.put("slug", productobject.getString("slug"));
                        addList.put("category_id", productobject.getString("category_id"));
                        addList.put("sub_category_id", productobject.getString("sub_category_id"));
                        addList.put("sub_sub_category_id", productobject.getString("sub_sub_category_id"));
                        addList.put("package_category_id", productobject.getString("package_category_id"));
                        addList.put("name", productobject.getString("name"));
                        addList.put("video", productobject.getString("video"));
                        addList.put("service_id", productobject.getString("service_id"));
                        addList.put("amount", productobject.getString("amount"));
                        addList.put("discount", productobject.getString("discount"));
                        addList.put("after_discount", productobject.getString("after_discount"));
                        addList.put("about_package", productobject.getString("about_package"));
                        addList.put("status", productobject.getString("status"));
                        addList.put("active", productobject.getString("active"));
                        addList.put("active_till", productobject.getString("active_till"));
                        addList.put("deal_slug", productobject.getString("deal_slug"));
                        addList.put("vendor_commission", productobject.getString("vendor_commission"));
                        addList.put("timer", productobject.getString("timer"));
                        addList.put("created_at", productobject.getString("created_at"));
                        addList.put("updated_at", productobject.getString("updated_at"));

                        packageArray.add(addList);
                    }
                }// this
                    pacakage = new PackagesAdapter(packgeCategoryArrayList);
                    horizontalrecy.setAdapter(pacakage);
                    makeSubPackages(selectedId);

                } else {

                }
            }
        } catch (Exception e) {
            setLog(apiErrorException, e.getMessage());
            showToast(mActivity, defResponse);

        }
    }


    private void setOnClickListener() {
        rlDashBoard.setOnClickListener(this);
        rr_home.setOnClickListener(this);
        //  tvgreen.setOnClickListener(this);
        rr_category.setOnClickListener(this);
        rr_recharge.setOnClickListener(this);
        rrInsurance.setOnClickListener(this);
        rr_About.setOnClickListener(this);
        rr_Contact.setOnClickListener(this);
        rr_register.setOnClickListener(this);
        rrsignin.setOnClickListener(this);
        ivBack.setOnClickListener(this);
        ivNav.setOnClickListener(this);
        String icon = getIntent().getStringExtra("icon");
        String name = getIntent().getStringExtra("name");

        /* Picasso.get().load(AppUrls.BASESUBSubCategoryimagepath + icon).into(imageView);*/
        //tvwallet.setText(name);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
   /* private void getgetMakeOwnPackageSubSubCategory() {

        String url = AppUrls.getMakeOwnPackageSubSubCategory;
        setLog("faq_url", url);
        AndroidNetworking.get(url)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseMakeOwnPackageSubSubCategoryResponse(response);
                    }
                    @Override
                    public void onError(ANError error) {
                        setLog("data", error.getErrorBody());
                        setLog("data", error.getErrorDetail());
                    }
                });
    }

    private void parseMakeOwnPackageSubSubCategoryResponse(JSONObject response) {
        try {
            String message=response.getString("message");
            Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
            if (response.getString("status").equals("true")) {
                JSONObject jsonObject = response.getJSONObject("data");
                JSONObject services=jsonObject.getJSONObject("services");

            } else {
            }
        } catch (Exception e) {
            setLog("error", String.valueOf(e));
        }

    }
*/

    private void makeSubPackages(String id) {
        ArrayList<HashMap<String, String>> sub_Package = new ArrayList<>();
        for (int i = 0; i < packageArray.size(); i++) {
            if (packageArray.get(i).get("package_category_id").equalsIgnoreCase(id)) {
                sub_Package.add(packageArray.get(i));
            }
        }
        setLog("MyCatData", "Check" + sub_Package.toString());
        DataAdapter dataAdapter = new DataAdapter(sub_Package);
        verticalrecy.setAdapter(dataAdapter);
        pacakage.notifyDataSetChanged();
        dataAdapter.notifyDataSetChanged();

    }

    //PackagesAdapter
    private class PackagesAdapter extends RecyclerView.Adapter<PackagesAdapter.WalletHolder> {
        ArrayList<HashMap<String, String>> data = new ArrayList();

        public PackagesAdapter(ArrayList<HashMap<String, String>> programlist) {
            this.data = programlist;
        }

        @Override
        public PackagesAdapter.WalletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.horizontal_packages, parent, false);
            return new PackagesAdapter.WalletHolder(view);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public void onBindViewHolder(final PackagesAdapter.WalletHolder holder, final int position) {
            if (data.get(position).get("id").equalsIgnoreCase(selectedId)) {
                holder.tvdata.setTextColor(getResources().getColor(R.color.white));
                holder.tvdata.setBackground(getResources().getDrawable(R.drawable.blue_ui));
                tvHeading.setText(data.get(position).get("name"));

            } else {
                holder.tvdata.setTextColor(getResources().getColor(R.color.black));
                holder.tvdata.setBackground(getResources().getDrawable(R.drawable.allsideborder));
            }
            holder.tvdata.setText(data.get(position).get("name"));
            holder.tvdata.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedId = data.get(position).get("id");
                    makeSubPackages(data.get(position).get("id"));

                }
            });
        }

        public class WalletHolder extends RecyclerView.ViewHolder {
            TextView tvdata;
            public WalletHolder(View itemView) {
                super(itemView);
                tvdata = itemView.findViewById(R.id.data);
            }
        }
    }

   /* private void PostPackageApi() {
        String url = AppUrls.PostPackageIn;
        AndroidNetworking.post(url)
                .addBodyParameter("package_id","1")
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        parseJsonResponse(response);
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        if (error.getErrorCode() != 0) {
                            Log.d("onError errorCode ", "onError errorCode : " + error.getErrorCode());
                            Log.d("onError errorBody", "onError errorBody : " + error.getErrorBody());
                            Log.d("onError errorDetail", "onError errorDetail : " + error.getErrorDetail());
                        } else {
                            Toast.makeText(mActivity,String.valueOf(error.getErrorDetail()), Toast.LENGTH_SHORT).show();
                            //  AppUtils.showErrorMessage(etNewpassword, String.valueOf(error.getErrorDetail()), mActivity);
                        }
                    }
                });
    }

    private void parseJsonResponse(JSONObject response) {
        try {
            String message=response.getString("message");
            Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
            if (response.getString("status").equals("true")) {
                JSONObject jsonObject = response.getJSONObject("data");
                String title=jsonObject.getString("title");
                String description=jsonObject.getString("description");
                String keyword=jsonObject.getString("keyword");
                JSONObject contact_infoobj=jsonObject.getJSONObject("package");
                String id         =contact_infoobj.getString("id");
                String slug        =contact_infoobj.getString("slug");
                String category_id        =contact_infoobj.getString("category_id");
                String sub_category_id        =contact_infoobj.getString("sub_category_id");
                String sub_sub_category_id        =contact_infoobj.getString("sub_sub_category_id");
                String name        =contact_infoobj.getString("name");
                String video        =contact_infoobj.getString("video");
                String service_id        =contact_infoobj.getString("service_id");
                String amount        =contact_infoobj.getString("amount");
                String discount        =contact_infoobj.getString("discount");
                String after_discount        =contact_infoobj.getString("after_discount");
                String about_package        =contact_infoobj.getString("about_package");
                String status        =contact_infoobj.getString("status");
                String active        =contact_infoobj.getString("active");
                String active_till        =contact_infoobj.getString("active_till");
                String deal_slug        =contact_infoobj.getString("deal_slug");
                String vendor_commission        =contact_infoobj.getString("vendor_commission");
                String timer        =contact_infoobj.getString("timer");
                String created_at        =contact_infoobj.getString("created_at");
                String updated_at        =contact_infoobj.getString("updated_at");

                JSONObject sub_categoryobj=jsonObject.getJSONObject("sub_category");
                String idr         =sub_categoryobj.getString("id");
                String categoryr_id         =sub_categoryobj.getString("category_id");
                String sub_category_icon         =sub_categoryobj.getString("sub_category_icon");
                String sub_category_name         =sub_categoryobj.getString("sub_category_name");
                String sub_category_slug         =sub_categoryobj.getString("sub_category_slug");
                String short_description         =sub_categoryobj.getString("short_description");
                String features         =sub_categoryobj.getString("features");
                String meta_title         =sub_categoryobj.getString("meta_title");
                String meta_description         =sub_categoryobj.getString("meta_description");
                String meta_keyword         =sub_categoryobj.getString("meta_keyword");
                String staturs         =sub_categoryobj.getString("status");
                String eligiable_for_all         =sub_categoryobj.getString("eligiable_for_all");
                String createed_at         =sub_categoryobj.getString("created_at");
                String updateed_at         =sub_categoryobj.getString("updated_at");
                String servicable_pincode         =sub_categoryobj.getString("servicable_pincode");
                String deleted_at         =sub_categoryobj.getString("deleted_at");

                JSONObject servicesobj=jsonObject.getJSONObject("services");
                String iddr         =servicesobj.getString("id");
                String icategory_id         =servicesobj.getString("category_id");
                String isub_category_id         =servicesobj.getString("sub_category_id");
                String isub_sub_category_id         =servicesobj.getString("sub_sub_category_id");
                String iname         =servicesobj.getString("name");
                String iamount         =servicesobj.getString("amount");
                String idiscount         =servicesobj.getString("discount");
                String iafter_discount         =servicesobj.getString("after_discount");
                String istatus         =servicesobj.getString("status");
                String icon         =servicesobj.getString("icon");
                String detail_image         =servicesobj.getString("detail_image");
                String desscription         =servicesobj.getString("description");
                String detail_video         =servicesobj.getString("detail_video");
                String icreated_at         =servicesobj.getString("created_at");
                String iupdated_at        =servicesobj.getString("updated_at");
                String ideleted_at         =servicesobj.getString("deleted_at");

            } else {
            }
        } catch (Exception e) {
            setLog("error", String.valueOf(e));
        }
    }*/

    private class DataAdapter extends RecyclerView.Adapter<DataAdapter.WalletHolder> {
        ArrayList<HashMap<String, String>> data = new ArrayList();

        public DataAdapter(ArrayList<HashMap<String, String>> programlist) {
            this.data = programlist;
        }

        @Override
        public DataAdapter.WalletHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.data_view, parent, false);
            return new DataAdapter.WalletHolder(view);
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        @Override
        public void onBindViewHolder(final DataAdapter.WalletHolder holder, final int position) {
            holder.tvwallet.setText(data.get(position).get("name"));
holder.tvgreen.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(mActivity,Addtocart.class));
    }
});


           holder.tvCutPrice.setText( " \u20B9 " + data.get(position).get("amount"));
            holder.tvCutPrice.setPaintFlags(holder.tvCutPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.tvMainPrice.setText(" \u20B9 " + data.get(position).get("discount"));
                if(data.get(position).get("after_discount")!=null){
                    holder.tvPrice.setText(" \u20B9 " + data.get(position).get("amount"));
                }else{
                    holder.tvPrice.setText(" \u20B9 " + data.get(position).get("after_discount"));
                }
              Picasso.get().load(AppUrls.BASEServicesimagepath + data.get(position).get("icon")).into(holder.imageView);
//   AppTools.setImgPicasso(AppUrls.BASEServicesimagepath + data.get(position).get("icon"),mActivity,holder.imageView);



        }

        public class WalletHolder extends RecyclerView.ViewHolder {
            TextView tvwallet,tvdate,tvPrice,tvCutPrice,tvMainPrice,tvgreen,text;
ImageView imageView;
            public WalletHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imageView);
                tvwallet = itemView.findViewById(R.id.tvwallet);
                tvdate = itemView.findViewById(R.id.tvdate);
                tvPrice = itemView.findViewById(R.id.tvPrice);
                tvCutPrice = itemView.findViewById(R.id.tvCutPrice);
                tvMainPrice = itemView.findViewById(R.id.tvMainPrice);
                tvgreen = itemView.findViewById(R.id.tvgreen);
                text = itemView.findViewById(R.id.text);
            }
        }
    }

}
