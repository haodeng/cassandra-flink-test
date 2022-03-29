import com.datastax.driver.mapping.annotations.Column;
import com.datastax.driver.mapping.annotations.Table;

import java.util.Objects;

@Table(keyspace = "usage", name="data_usage")
public class DataUsageByCompanySimCard {
    public DataUsageByCompanySimCard() {
    }
    private Integer year;
    private Integer month;

    @Column(name = "bytes_in")
    private Long bytesIn;

    @Column(name = "bytes_out")
    private Long bytesOut;

    @Column(name = "session_id")
    private String sessionId;

    private String imei;
    private String icc;

    @Column(name = "country_code")
    private String countryCode;

    public Long getBytesIn() {
        return bytesIn;
    }

    public void setBytesIn(Long bytesIn) {
        this.bytesIn = bytesIn;
    }

    public Long getBytesOut() {
        return bytesOut;
    }

    public void setBytesOut(Long bytesOut) {
        this.bytesOut = bytesOut;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getIcc() {
        return icc;
    }

    public void setIcc(String icc) {
        this.icc = icc;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataUsageByCompanySimCard that = (DataUsageByCompanySimCard) o;
        return Objects.equals(year, that.year) && Objects.equals(month, that.month) && Objects.equals(bytesIn, that.bytesIn) && Objects.equals(bytesOut, that.bytesOut) && Objects.equals(sessionId, that.sessionId) && Objects.equals(imei, that.imei) && Objects.equals(icc, that.icc) && Objects.equals(countryCode, that.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, bytesIn, bytesOut, sessionId, imei, icc, countryCode);
    }

    @Override
    public String toString() {
        return "DataUsageByCompanySimCard{" +
                "year=" + year +
                ", month=" + month +
                ", bytesIn=" + bytesIn +
                ", bytesOut=" + bytesOut +
                ", sessionId='" + sessionId + '\'' +
                ", imei='" + imei + '\'' +
                ", icc='" + icc + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }
}
