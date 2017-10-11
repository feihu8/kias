package com.kias.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Userinfo implements Serializable {
    private Integer id;

    private String loginname;

    private String preloginname;

    private String realname;

    private String sex;

    private String nation;

    private Date birthday;

    private String cardno;

    private String nativeplace;

    private String photo;

    private String maritalstatus;

    private String status;

    private Date createtime;

    private String telephone;

    private String wechat;

    private String email;

    private String address;

    private String postcode;

    private String emergentcontact;

    private String emergenttelephone;

    private String politicalaffiliatio;

    private String firsteducationcode;

    private String firstgraduate;

    private String secondeducationcode;

    private String secondgraduate;

    private String topeducationcode;

    private String topgraduate;

    private String readeducationcode;

    private String readgraduate;

    private String finaleducationcode;

    private Date finalgraduatedate;

    private String degree;

    private String foreignlanguage;

    private Date joinworkdate;

    private Date enrolldate;

    private Date entrydate;

    private Date regulardate;

    private Integer filenum;

    private String personnelnature;

    private String worktype;

    private String identitytype;

    private String jobtype;

    private String hospitalcode;

    private String departcode;

    private String logincodes;

    private String positioncode;

    private Date positiondate;

    private String protitlecode;

    private Date protitledate;

    private String workstate;

    private String experience;

    private String nursetier;

    private String nursecertificate;

    private BigDecimal nurseagesubsidyshould;

    private BigDecimal nurseagesubsidyreal;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname == null ? null : loginname.trim();
    }

    public String getPreloginname() {
        return preloginname;
    }

    public void setPreloginname(String preloginname) {
        this.preloginname = preloginname == null ? null : preloginname.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation == null ? null : nation.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCardno() {
        return cardno;
    }

    public void setCardno(String cardno) {
        this.cardno = cardno == null ? null : cardno.trim();
    }

    public String getNativeplace() {
        return nativeplace;
    }

    public void setNativeplace(String nativeplace) {
        this.nativeplace = nativeplace == null ? null : nativeplace.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getMaritalstatus() {
        return maritalstatus;
    }

    public void setMaritalstatus(String maritalstatus) {
        this.maritalstatus = maritalstatus == null ? null : maritalstatus.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? null : wechat.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getEmergentcontact() {
        return emergentcontact;
    }

    public void setEmergentcontact(String emergentcontact) {
        this.emergentcontact = emergentcontact == null ? null : emergentcontact.trim();
    }

    public String getEmergenttelephone() {
        return emergenttelephone;
    }

    public void setEmergenttelephone(String emergenttelephone) {
        this.emergenttelephone = emergenttelephone == null ? null : emergenttelephone.trim();
    }

    public String getPoliticalaffiliatio() {
        return politicalaffiliatio;
    }

    public void setPoliticalaffiliatio(String politicalaffiliatio) {
        this.politicalaffiliatio = politicalaffiliatio == null ? null : politicalaffiliatio.trim();
    }

    public String getFirsteducationcode() {
        return firsteducationcode;
    }

    public void setFirsteducationcode(String firsteducationcode) {
        this.firsteducationcode = firsteducationcode == null ? null : firsteducationcode.trim();
    }

    public String getFirstgraduate() {
        return firstgraduate;
    }

    public void setFirstgraduate(String firstgraduate) {
        this.firstgraduate = firstgraduate == null ? null : firstgraduate.trim();
    }

    public String getSecondeducationcode() {
        return secondeducationcode;
    }

    public void setSecondeducationcode(String secondeducationcode) {
        this.secondeducationcode = secondeducationcode == null ? null : secondeducationcode.trim();
    }

    public String getSecondgraduate() {
        return secondgraduate;
    }

    public void setSecondgraduate(String secondgraduate) {
        this.secondgraduate = secondgraduate == null ? null : secondgraduate.trim();
    }

    public String getTopeducationcode() {
        return topeducationcode;
    }

    public void setTopeducationcode(String topeducationcode) {
        this.topeducationcode = topeducationcode == null ? null : topeducationcode.trim();
    }

    public String getTopgraduate() {
        return topgraduate;
    }

    public void setTopgraduate(String topgraduate) {
        this.topgraduate = topgraduate == null ? null : topgraduate.trim();
    }

    public String getReadeducationcode() {
        return readeducationcode;
    }

    public void setReadeducationcode(String readeducationcode) {
        this.readeducationcode = readeducationcode == null ? null : readeducationcode.trim();
    }

    public String getReadgraduate() {
        return readgraduate;
    }

    public void setReadgraduate(String readgraduate) {
        this.readgraduate = readgraduate == null ? null : readgraduate.trim();
    }

    public String getFinaleducationcode() {
        return finaleducationcode;
    }

    public void setFinaleducationcode(String finaleducationcode) {
        this.finaleducationcode = finaleducationcode == null ? null : finaleducationcode.trim();
    }

    public Date getFinalgraduatedate() {
        return finalgraduatedate;
    }

    public void setFinalgraduatedate(Date finalgraduatedate) {
        this.finalgraduatedate = finalgraduatedate;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree == null ? null : degree.trim();
    }

    public String getForeignlanguage() {
        return foreignlanguage;
    }

    public void setForeignlanguage(String foreignlanguage) {
        this.foreignlanguage = foreignlanguage == null ? null : foreignlanguage.trim();
    }

    public Date getJoinworkdate() {
        return joinworkdate;
    }

    public void setJoinworkdate(Date joinworkdate) {
        this.joinworkdate = joinworkdate;
    }

    public Date getEnrolldate() {
        return enrolldate;
    }

    public void setEnrolldate(Date enrolldate) {
        this.enrolldate = enrolldate;
    }

    public Date getEntrydate() {
        return entrydate;
    }

    public void setEntrydate(Date entrydate) {
        this.entrydate = entrydate;
    }

    public Date getRegulardate() {
        return regulardate;
    }

    public void setRegulardate(Date regulardate) {
        this.regulardate = regulardate;
    }

    public Integer getFilenum() {
        return filenum;
    }

    public void setFilenum(Integer filenum) {
        this.filenum = filenum;
    }

    public String getPersonnelnature() {
        return personnelnature;
    }

    public void setPersonnelnature(String personnelnature) {
        this.personnelnature = personnelnature == null ? null : personnelnature.trim();
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype == null ? null : worktype.trim();
    }

    public String getIdentitytype() {
        return identitytype;
    }

    public void setIdentitytype(String identitytype) {
        this.identitytype = identitytype == null ? null : identitytype.trim();
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype == null ? null : jobtype.trim();
    }

    public String getHospitalcode() {
        return hospitalcode;
    }

    public void setHospitalcode(String hospitalcode) {
        this.hospitalcode = hospitalcode == null ? null : hospitalcode.trim();
    }

    public String getDepartcode() {
        return departcode;
    }

    public void setDepartcode(String departcode) {
        this.departcode = departcode == null ? null : departcode.trim();
    }

    public String getLogincodes() {
        return logincodes;
    }

    public void setLogincodes(String logincodes) {
        this.logincodes = logincodes == null ? null : logincodes.trim();
    }

    public String getPositioncode() {
        return positioncode;
    }

    public void setPositioncode(String positioncode) {
        this.positioncode = positioncode == null ? null : positioncode.trim();
    }

    public Date getPositiondate() {
        return positiondate;
    }

    public void setPositiondate(Date positiondate) {
        this.positiondate = positiondate;
    }

    public String getProtitlecode() {
        return protitlecode;
    }

    public void setProtitlecode(String protitlecode) {
        this.protitlecode = protitlecode == null ? null : protitlecode.trim();
    }

    public Date getProtitledate() {
        return protitledate;
    }

    public void setProtitledate(Date protitledate) {
        this.protitledate = protitledate;
    }

    public String getWorkstate() {
        return workstate;
    }

    public void setWorkstate(String workstate) {
        this.workstate = workstate == null ? null : workstate.trim();
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience == null ? null : experience.trim();
    }

    public String getNursetier() {
        return nursetier;
    }

    public void setNursetier(String nursetier) {
        this.nursetier = nursetier == null ? null : nursetier.trim();
    }

    public String getNursecertificate() {
        return nursecertificate;
    }

    public void setNursecertificate(String nursecertificate) {
        this.nursecertificate = nursecertificate == null ? null : nursecertificate.trim();
    }

    public BigDecimal getNurseagesubsidyshould() {
        return nurseagesubsidyshould;
    }

    public void setNurseagesubsidyshould(BigDecimal nurseagesubsidyshould) {
        this.nurseagesubsidyshould = nurseagesubsidyshould;
    }

    public BigDecimal getNurseagesubsidyreal() {
        return nurseagesubsidyreal;
    }

    public void setNurseagesubsidyreal(BigDecimal nurseagesubsidyreal) {
        this.nurseagesubsidyreal = nurseagesubsidyreal;
    }
}