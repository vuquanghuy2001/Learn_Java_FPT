package Hieu_iceTea.FoodMate_Spring.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends BaseModel implements Serializable {

    //region - Define Fields -
    //private int restaurantId; //Foreign key - Relationship


    // - - - - -
    @NotNull
    @Size(min = 2, max = 64)
    private String username;

    @NotNull
    @Email
    @Size(min = 2, max = 64)
    private String email;

    //@NotNull
    //@Size(min = 2, max = 128)
    private String password;


    // - - - - -
    private Date emailVerifiedAt;
    private String verificationCode;
    private String resetPasswordCode;
    private String rememberToken;


    // - - - - -
    @Size(max = 128)
    private String image;

    private Boolean gender;

    @Size(min = 2, max = 64)
    private String firstName;

    @Size(min = 2, max = 64)
    private String lastName;

    @Size(min = 2, max = 16)
    private String phone;

    @Size(min = 2, max = 128)
    private String address;


    // - - - - -
    @NotNull
    private Boolean enabled;
    //endregion


    //region - Relationship -
    @OneToMany(mappedBy = "user")
    private List<Authority> authorities;

    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "restaurantId") //updatable = false, insertable = false
    private Restaurant restaurant;
    //endregion


    //region - Getter, Setter -
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getEmailVerifiedAt() {
        return emailVerifiedAt;
    }

    public void setEmailVerifiedAt(Date emailVerifiedAt) {
        this.emailVerifiedAt = emailVerifiedAt;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getResetPasswordCode() {
        return resetPasswordCode;
    }

    public void setResetPasswordCode(String resetPasswordCode) {
        this.resetPasswordCode = resetPasswordCode;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public void setRememberToken(String rememberToken) {
        this.rememberToken = rememberToken;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    //endregion


    //region - Method Extend -
    public String getAuthoritiesString() {
        if (authorities.isEmpty()) {
            return "";
        }

        List<String> arrAuthorities = new ArrayList<>();

        for (Authority authority : authorities) {
            arrAuthorities.add(authority.getAuthority().replace("ROLE_", ""));
        }

        return arrAuthorities.toString().replace("[", "").replace("]", "");
    }

    public List<String> getAuthoritiesListString() {
        List<String> arrAuthorities = new ArrayList<>();

        for (Authority authority : authorities) {
            arrAuthorities.add(authority.getAuthority());
        }

        return arrAuthorities;
    }
    //endregion

}