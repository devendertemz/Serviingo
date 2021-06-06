package com.serviingo.serviingo.modelrepo.Responsee;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile_Repo {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
    public class Data {

        @SerializedName("profile")
        @Expose
        private Profile profile;

        public Profile getProfile() {
            return profile;
        }

        public void setProfile(Profile profile) {
            this.profile = profile;
        }
        public class Profile {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("referral_user_id")
            @Expose
            private String referralUserId;
            @SerializedName("referral_master_id")
            @Expose
            private String referralMasterId;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("mobile_number")
            @Expose
            private String mobileNumber;
            @SerializedName("state_id")
            @Expose
            private String stateId;
            @SerializedName("city_id")
            @Expose
            private String cityId;
            @SerializedName("category_id")
            @Expose
            private String categoryId;
            @SerializedName("sub_category_id")
            @Expose
            private String subCategoryId;
            @SerializedName("service_id")
            @Expose
            private String serviceId;
            @SerializedName("profile_pic")
            @Expose
            private String profilePic;
            @SerializedName("role")
            @Expose
            private String role;
            @SerializedName("gender")
            @Expose
            private String gender;
            @SerializedName("pincode")
            @Expose
            private String pincode;
            @SerializedName("address")
            @Expose
            private String address;
            @SerializedName("first_name")
            @Expose
            private String firstName;
            @SerializedName("last_name")
            @Expose
            private String lastName;
            @SerializedName("business_type")
            @Expose
            private String businessType;
            @SerializedName("website")
            @Expose
            private String website;
            @SerializedName("referral_id")
            @Expose
            private String referralId;
            @SerializedName("full_address")
            @Expose
            private String fullAddress;
            @SerializedName("pan_card_number")
            @Expose
            private String panCardNumber;
            @SerializedName("aadhaar_card_number")
            @Expose
            private String aadhaarCardNumber;
            @SerializedName("business_proof_number")
            @Expose
            private String businessProofNumber;
            @SerializedName("business_address")
            @Expose
            private String businessAddress;
            @SerializedName("account_type")
            @Expose
            private String accountType;
            @SerializedName("account_name")
            @Expose
            private String accountName;
            @SerializedName("account_number")
            @Expose
            private String accountNumber;
            @SerializedName("ifsc_code")
            @Expose
            private String ifscCode;
            @SerializedName("bank_name")
            @Expose
            private String bankName;
            @SerializedName("bank_branch")
            @Expose
            private String bankBranch;
            @SerializedName("pan_card_document")
            @Expose
            private String panCardDocument;
            @SerializedName("aadhaar_card_front")
            @Expose
            private String aadhaarCardFront;
            @SerializedName("aadhaar_card_back")
            @Expose
            private String aadhaarCardBack;
            @SerializedName("business_proof_document")
            @Expose
            private String businessProofDocument;
            @SerializedName("cancelled_cheque_img")
            @Expose
            private String cancelledChequeImg;
            @SerializedName("photographs")
            @Expose
            private String photographs;
            @SerializedName("other_documents")
            @Expose
            private String otherDocuments;
            @SerializedName("country_id")
            @Expose
            private String countryId;
            @SerializedName("landmark")
            @Expose
            private String landmark;
            @SerializedName("status")
            @Expose
            private Integer status;
            @SerializedName("email_verified_at")
            @Expose
            private String emailVerifiedAt;
            @SerializedName("current_team_id")
            @Expose
            private String currentTeamId;
            @SerializedName("profile_photo_path")
            @Expose
            private String profilePhotoPath;
            @SerializedName("wallet_amount")
            @Expose
            private String walletAmount;
            @SerializedName("unique_id")
            @Expose
            private String uniqueId;
            @SerializedName("total_loan")
            @Expose
            private Integer totalLoan;
            @SerializedName("remaining_loan")
            @Expose
            private Integer remainingLoan;
            @SerializedName("dob")
            @Expose
            private String dob;
            @SerializedName("father_name")
            @Expose
            private String fatherName;
            @SerializedName("sender_amount")
            @Expose
            private Integer senderAmount;
            @SerializedName("receiver_amount")
            @Expose
            private Integer receiverAmount;
            @SerializedName("sender_referral_status")
            @Expose
            private String senderReferralStatus;
            @SerializedName("receiver_referral_status")
            @Expose
            private String receiverReferralStatus;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("deleted_at")
            @Expose
            private String deletedAt;
            @SerializedName("profile_photo_url")
            @Expose
            private String profilePhotoUrl;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getReferralUserId() {
                return referralUserId;
            }

            public void setReferralUserId(String referralUserId) {
                this.referralUserId = referralUserId;
            }

            public String getReferralMasterId() {
                return referralMasterId;
            }

            public void setReferralMasterId(String referralMasterId) {
                this.referralMasterId = referralMasterId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getMobileNumber() {
                return mobileNumber;
            }

            public void setMobileNumber(String mobileNumber) {
                this.mobileNumber = mobileNumber;
            }

            public String getStateId() {
                return stateId;
            }

            public void setStateId(String stateId) {
                this.stateId = stateId;
            }

            public String getCityId() {
                return cityId;
            }

            public void setCityId(String cityId) {
                this.cityId = cityId;
            }

            public String getCategoryId() {
                return categoryId;
            }

            public void setCategoryId(String categoryId) {
                this.categoryId = categoryId;
            }

            public String getSubCategoryId() {
                return subCategoryId;
            }

            public void setSubCategoryId(String subCategoryId) {
                this.subCategoryId = subCategoryId;
            }

            public String getServiceId() {
                return serviceId;
            }

            public void setServiceId(String serviceId) {
                this.serviceId = serviceId;
            }

            public String getProfilePic() {
                return profilePic;
            }

            public void setProfilePic(String profilePic) {
                this.profilePic = profilePic;
            }

            public String getRole() {
                return role;
            }

            public void setRole(String role) {
                this.role = role;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public String getPincode() {
                return pincode;
            }

            public void setPincode(String pincode) {
                this.pincode = pincode;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getFirstName() {
                return firstName;
            }

            public void setFirstName(String firstName) {
                this.firstName = firstName;
            }

            public String getLastName() {
                return lastName;
            }

            public void setLastName(String lastName) {
                this.lastName = lastName;
            }

            public String getBusinessType() {
                return businessType;
            }

            public void setBusinessType(String businessType) {
                this.businessType = businessType;
            }

            public String getWebsite() {
                return website;
            }

            public void setWebsite(String website) {
                this.website = website;
            }

            public String getReferralId() {
                return referralId;
            }

            public void setReferralId(String referralId) {
                this.referralId = referralId;
            }

            public String getFullAddress() {
                return fullAddress;
            }

            public void setFullAddress(String fullAddress) {
                this.fullAddress = fullAddress;
            }

            public String getPanCardNumber() {
                return panCardNumber;
            }

            public void setPanCardNumber(String panCardNumber) {
                this.panCardNumber = panCardNumber;
            }

            public String getAadhaarCardNumber() {
                return aadhaarCardNumber;
            }

            public void setAadhaarCardNumber(String aadhaarCardNumber) {
                this.aadhaarCardNumber = aadhaarCardNumber;
            }

            public String getBusinessProofNumber() {
                return businessProofNumber;
            }

            public void setBusinessProofNumber(String businessProofNumber) {
                this.businessProofNumber = businessProofNumber;
            }

            public String getBusinessAddress() {
                return businessAddress;
            }

            public void setBusinessAddress(String businessAddress) {
                this.businessAddress = businessAddress;
            }

            public String getAccountType() {
                return accountType;
            }

            public void setAccountType(String accountType) {
                this.accountType = accountType;
            }

            public String getAccountName() {
                return accountName;
            }

            public void setAccountName(String accountName) {
                this.accountName = accountName;
            }

            public String getAccountNumber() {
                return accountNumber;
            }

            public void setAccountNumber(String accountNumber) {
                this.accountNumber = accountNumber;
            }

            public String getIfscCode() {
                return ifscCode;
            }

            public void setIfscCode(String ifscCode) {
                this.ifscCode = ifscCode;
            }

            public String getBankName() {
                return bankName;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }

            public String getBankBranch() {
                return bankBranch;
            }

            public void setBankBranch(String bankBranch) {
                this.bankBranch = bankBranch;
            }

            public String getPanCardDocument() {
                return panCardDocument;
            }

            public void setPanCardDocument(String panCardDocument) {
                this.panCardDocument = panCardDocument;
            }

            public String getAadhaarCardFront() {
                return aadhaarCardFront;
            }

            public void setAadhaarCardFront(String aadhaarCardFront) {
                this.aadhaarCardFront = aadhaarCardFront;
            }

            public String getAadhaarCardBack() {
                return aadhaarCardBack;
            }

            public void setAadhaarCardBack(String aadhaarCardBack) {
                this.aadhaarCardBack = aadhaarCardBack;
            }

            public String getBusinessProofDocument() {
                return businessProofDocument;
            }

            public void setBusinessProofDocument(String businessProofDocument) {
                this.businessProofDocument = businessProofDocument;
            }

            public String getCancelledChequeImg() {
                return cancelledChequeImg;
            }

            public void setCancelledChequeImg(String cancelledChequeImg) {
                this.cancelledChequeImg = cancelledChequeImg;
            }

            public String getPhotographs() {
                return photographs;
            }

            public void setPhotographs(String photographs) {
                this.photographs = photographs;
            }

            public String getOtherDocuments() {
                return otherDocuments;
            }

            public void setOtherDocuments(String otherDocuments) {
                this.otherDocuments = otherDocuments;
            }

            public String getCountryId() {
                return countryId;
            }

            public void setCountryId(String countryId) {
                this.countryId = countryId;
            }

            public String getLandmark() {
                return landmark;
            }

            public void setLandmark(String landmark) {
                this.landmark = landmark;
            }

            public Integer getStatus() {
                return status;
            }

            public void setStatus(Integer status) {
                this.status = status;
            }

            public String getEmailVerifiedAt() {
                return emailVerifiedAt;
            }

            public void setEmailVerifiedAt(String emailVerifiedAt) {
                this.emailVerifiedAt = emailVerifiedAt;
            }

            public String getCurrentTeamId() {
                return currentTeamId;
            }

            public void setCurrentTeamId(String currentTeamId) {
                this.currentTeamId = currentTeamId;
            }

            public String getProfilePhotoPath() {
                return profilePhotoPath;
            }

            public void setProfilePhotoPath(String profilePhotoPath) {
                this.profilePhotoPath = profilePhotoPath;
            }

            public String getWalletAmount() {
                return walletAmount;
            }

            public void setWalletAmount(String walletAmount) {
                this.walletAmount = walletAmount;
            }

            public String getUniqueId() {
                return uniqueId;
            }

            public void setUniqueId(String uniqueId) {
                this.uniqueId = uniqueId;
            }

            public Integer getTotalLoan() {
                return totalLoan;
            }

            public void setTotalLoan(Integer totalLoan) {
                this.totalLoan = totalLoan;
            }

            public Integer getRemainingLoan() {
                return remainingLoan;
            }

            public void setRemainingLoan(Integer remainingLoan) {
                this.remainingLoan = remainingLoan;
            }

            public String getDob() {
                return dob;
            }

            public void setDob(String dob) {
                this.dob = dob;
            }

            public String getFatherName() {
                return fatherName;
            }

            public void setFatherName(String fatherName) {
                this.fatherName = fatherName;
            }

            public Integer getSenderAmount() {
                return senderAmount;
            }

            public void setSenderAmount(Integer senderAmount) {
                this.senderAmount = senderAmount;
            }

            public Integer getReceiverAmount() {
                return receiverAmount;
            }

            public void setReceiverAmount(Integer receiverAmount) {
                this.receiverAmount = receiverAmount;
            }

            public String getSenderReferralStatus() {
                return senderReferralStatus;
            }

            public void setSenderReferralStatus(String senderReferralStatus) {
                this.senderReferralStatus = senderReferralStatus;
            }

            public String getReceiverReferralStatus() {
                return receiverReferralStatus;
            }

            public void setReceiverReferralStatus(String receiverReferralStatus) {
                this.receiverReferralStatus = receiverReferralStatus;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getDeletedAt() {
                return deletedAt;
            }

            public void setDeletedAt(String deletedAt) {
                this.deletedAt = deletedAt;
            }

            public String getProfilePhotoUrl() {
                return profilePhotoUrl;
            }

            public void setProfilePhotoUrl(String profilePhotoUrl) {
                this.profilePhotoUrl = profilePhotoUrl;
            }

        }
    }
}