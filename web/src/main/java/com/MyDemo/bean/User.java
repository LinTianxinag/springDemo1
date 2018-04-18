package com.MyDemo.bean;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Long id;

    private String mobile;

    private String email;

    private Byte state;

    private Long agent;

    private String idcard;

    private String realname;

    private Date regdate;

    private Long refer;

    private Integer route;

    private Byte degree;

    private Date degreeExpire;

    private String channel;

    private String subchannel;

    private String com;

    private Byte virtual;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Long getAgent() {
        return agent;
    }

    public void setAgent(Long agent) {
        this.agent = agent;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Date getRegdate() {
        return regdate;
    }

    public void setRegdate(Date regdate) {
        this.regdate = regdate;
    }

    public Long getRefer() {
        return refer;
    }

    public void setRefer(Long refer) {
        this.refer = refer;
    }

    public Integer getRoute() {
        return route;
    }

    public void setRoute(Integer route) {
        this.route = route;
    }

    public Byte getDegree() {
        return degree;
    }

    public void setDegree(Byte degree) {
        this.degree = degree;
    }

    public Date getDegreeExpire() {
        return degreeExpire;
    }

    public void setDegreeExpire(Date degreeExpire) {
        this.degreeExpire = degreeExpire;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel == null ? null : channel.trim();
    }

    public String getSubchannel() {
        return subchannel;
    }

    public void setSubchannel(String subchannel) {
        this.subchannel = subchannel == null ? null : subchannel.trim();
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com == null ? null : com.trim();
    }

    public Byte getVirtual() {
        return virtual;
    }

    public void setVirtual(Byte virtual) {
        this.virtual = virtual;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getAgent() == null ? other.getAgent() == null : this.getAgent().equals(other.getAgent()))
            && (this.getIdcard() == null ? other.getIdcard() == null : this.getIdcard().equals(other.getIdcard()))
            && (this.getRealname() == null ? other.getRealname() == null : this.getRealname().equals(other.getRealname()))
            && (this.getRegdate() == null ? other.getRegdate() == null : this.getRegdate().equals(other.getRegdate()))
            && (this.getRefer() == null ? other.getRefer() == null : this.getRefer().equals(other.getRefer()))
            && (this.getRoute() == null ? other.getRoute() == null : this.getRoute().equals(other.getRoute()))
            && (this.getDegree() == null ? other.getDegree() == null : this.getDegree().equals(other.getDegree()))
            && (this.getDegreeExpire() == null ? other.getDegreeExpire() == null : this.getDegreeExpire().equals(other.getDegreeExpire()))
            && (this.getChannel() == null ? other.getChannel() == null : this.getChannel().equals(other.getChannel()))
            && (this.getSubchannel() == null ? other.getSubchannel() == null : this.getSubchannel().equals(other.getSubchannel()))
            && (this.getCom() == null ? other.getCom() == null : this.getCom().equals(other.getCom()))
            && (this.getVirtual() == null ? other.getVirtual() == null : this.getVirtual().equals(other.getVirtual()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getAgent() == null) ? 0 : getAgent().hashCode());
        result = prime * result + ((getIdcard() == null) ? 0 : getIdcard().hashCode());
        result = prime * result + ((getRealname() == null) ? 0 : getRealname().hashCode());
        result = prime * result + ((getRegdate() == null) ? 0 : getRegdate().hashCode());
        result = prime * result + ((getRefer() == null) ? 0 : getRefer().hashCode());
        result = prime * result + ((getRoute() == null) ? 0 : getRoute().hashCode());
        result = prime * result + ((getDegree() == null) ? 0 : getDegree().hashCode());
        result = prime * result + ((getDegreeExpire() == null) ? 0 : getDegreeExpire().hashCode());
        result = prime * result + ((getChannel() == null) ? 0 : getChannel().hashCode());
        result = prime * result + ((getSubchannel() == null) ? 0 : getSubchannel().hashCode());
        result = prime * result + ((getCom() == null) ? 0 : getCom().hashCode());
        result = prime * result + ((getVirtual() == null) ? 0 : getVirtual().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mobile=").append(mobile);
        sb.append(", email=").append(email);
        sb.append(", state=").append(state);
        sb.append(", agent=").append(agent);
        sb.append(", idcard=").append(idcard);
        sb.append(", realname=").append(realname);
        sb.append(", regdate=").append(regdate);
        sb.append(", refer=").append(refer);
        sb.append(", route=").append(route);
        sb.append(", degree=").append(degree);
        sb.append(", degreeExpire=").append(degreeExpire);
        sb.append(", channel=").append(channel);
        sb.append(", subchannel=").append(subchannel);
        sb.append(", com=").append(com);
        sb.append(", virtual=").append(virtual);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}