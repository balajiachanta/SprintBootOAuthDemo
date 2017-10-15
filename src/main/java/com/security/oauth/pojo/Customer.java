
package com.security.oauth.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"company",
"confirm-password",
"consent",
"country",
"e-mail",
"firstName",
"lastName",
"password"
})
public class Customer {

/**
* 
* (Required)
* 
*/
@JsonProperty("company")
private String company;
/**
* 
* (Required)
* 
*/
@JsonProperty("confirm-password")
private String confirmPassword;
/**
* 
* (Required)
* 
*/
@JsonProperty("consent")
private Boolean consent;
/**
* 
* (Required)
* 
*/
@JsonProperty("country")
private String country;
/**
* 
* (Required)
* 
*/
@JsonProperty("e-mail")
private String eMail;
/**
* 
* (Required)
* 
*/
@JsonProperty("firstName")
private String firstName;
/**
* 
* (Required)
* 
*/
@JsonProperty("lastName")
private String lastName;
/**
* 
* (Required)
* 
*/
@JsonProperty("password")
private String password;

/**
* 
* (Required)
* 
*/
@JsonProperty("company")
public String getCompany() {
return company;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("company")
public void setCompany(String company) {
this.company = company;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("confirm-password")
public String getConfirmPassword() {
return confirmPassword;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("confirm-password")
public void setConfirmPassword(String confirmPassword) {
this.confirmPassword = confirmPassword;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("consent")
public Boolean getConsent() {
return consent;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("consent")
public void setConsent(Boolean consent) {
this.consent = consent;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("country")
public String getCountry() {
return country;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("country")
public void setCountry(String country) {
this.country = country;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("e-mail")
public String getEMail() {
return eMail;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("e-mail")
public void setEMail(String eMail) {
this.eMail = eMail;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("firstName")
public String getFirstName() {
return firstName;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("firstName")
public void setFirstName(String firstName) {
this.firstName = firstName;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("lastName")
public String getLastName() {
return lastName;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("lastName")
public void setLastName(String lastName) {
this.lastName = lastName;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("password")
public String getPassword() {
return password;
}

/**
* 
* (Required)
* 
*/
@JsonProperty("password")
public void setPassword(String password) {
this.password = password;
}

}