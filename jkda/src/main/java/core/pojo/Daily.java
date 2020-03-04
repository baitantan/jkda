package core.pojo;



import java.util.Date;

public class Daily {
    private Long id;

    private Date createTime;

    private Date updateTime;

    private Byte bloodPressureHigh;

    private Byte bloodPressureLower;

    private Byte heartRate;

    private String pressure;

    private String sleep;

    private Byte weight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Byte getBloodPressureHigh() {
        return bloodPressureHigh;
    }

    public void setBloodPressureHigh(Byte bloodPressureHigh) {
        this.bloodPressureHigh = bloodPressureHigh;
    }

    public Byte getBloodPressureLower() {
        return bloodPressureLower;
    }

    public void setBloodPressureLower(Byte bloodPressureLower) {
        this.bloodPressureLower = bloodPressureLower;
    }

    public Byte getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(Byte heartRate) {
        this.heartRate = heartRate;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure == null ? null : pressure.trim();
    }

    public String getSleep() {
        return sleep;
    }

    public void setSleep(String sleep) {
        this.sleep = sleep == null ? null : sleep.trim();
    }

    public Byte getWeight() {
        return weight;
    }

    public void setWeight(Byte weight) {
        this.weight = weight;
    }
}