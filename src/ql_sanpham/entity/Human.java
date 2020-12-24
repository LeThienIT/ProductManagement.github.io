
package ql_sanpham.entity;

/*
 *@Author: AnthonyLe
 * *Vjp pRo
 */

public class Human {
    private int idHuman;
//    private int idEventHuman;
    private String name;
    private String cmnd;
    private String address;
    private String telephone;
    private String email;
    private String country;
    private String job;
    private String workplace;

    public Human(int idHuman, String name, String cmnd, String address, String telephone, String email, String country, String job, String workplace) {
        this.idHuman = idHuman;
        this.name = name;
        this.cmnd = cmnd;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.country = country;
        this.job = job;
        this.workplace = workplace;
    }

    public Human() {
    }

    public int getIdHuman() {
        return idHuman;
    }

    public void setIdHuman(int idHuman) {
        this.idHuman = idHuman;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    @Override
    public String toString() {
        return "Human{" + "idHuman=" + idHuman + ", name=" + name + ", cmnd=" + cmnd + ", address=" + address + ", telephone=" + telephone + ", email=" + email + ", country=" + country + ", job=" + job + ", workplace=" + workplace + '}';
    }

    
    
}
