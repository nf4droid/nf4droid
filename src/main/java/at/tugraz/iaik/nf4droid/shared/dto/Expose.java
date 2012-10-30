package at.tugraz.iaik.nf4droid.shared.dto;

import java.io.Serializable;
import java.util.Date;

import at.tugraz.iaik.nf4droid.shared.constant.ExposeObscuring;
import at.tugraz.iaik.nf4droid.shared.constant.ExposePoint;
import at.tugraz.iaik.nf4droid.shared.constant.ExposeType;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Expose implements Serializable, IsSerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Date startTime;
	private Long startTotalTrafficAmount;
	private Long httpReqId;
	private ExposeType exposeType;
	private ExposeObscuring exposeObscuring;
	private ExposePoint exposePoint;
	private String exposedData;

	public Expose() {
		super();
	}

	public Expose(Date startTime, Long httpReqId,
			ExposeType exposeType, ExposeObscuring exposeObscuring,
			ExposePoint exposePoint, String exposedData) {
		super();
		this.startTime = startTime;
		this.httpReqId = httpReqId;
		this.exposeType = exposeType;
		this.exposeObscuring = exposeObscuring;
		this.exposePoint = exposePoint;
		this.exposedData = exposedData;
	}

	/**
	 * @return the startTotalTrafficAmount
	 */
	public Long getStartTotalTrafficAmount() {
		return startTotalTrafficAmount;
	}

	/**
	 * @param startTotalTrafficAmount
	 *            the startTotalTrafficAmount to set
	 */
	public void setStartTotalTrafficAmount(Long startTotalTrafficAmount) {
		this.startTotalTrafficAmount = startTotalTrafficAmount;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @return the httpReqId
	 */
	public Long getHttpReqId() {
		return httpReqId;
	}

	/**
	 * @return the exposeType
	 */
	public ExposeType getExposeType() {
		return exposeType;
	}

	/**
	 * @return the exposeObscuring
	 */
	public ExposeObscuring getExposeObscuring() {
		return exposeObscuring;
	}

	/**
	 * @return the exposedData
	 */
	public String getExposedData() {
		return exposedData;
	}

	/**
	 * @return the exposePoint
	 */
	public ExposePoint getExposePoint() {
		return exposePoint;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Expose ["
				+ (startTime != null ? "startTime=" + startTime + ", " : "")
				+ (startTotalTrafficAmount != null ? "startTotalTrafficAmount="
						+ startTotalTrafficAmount + ", " : "")
				+ (httpReqId != null ? "httpReqId=" + httpReqId + ", " : "")
				+ (exposeType != null ? "exposeType=" + exposeType + ", " : "")
				+ (exposeObscuring != null ? "exposeObscuring="
						+ exposeObscuring + ", " : "")
				+ (exposePoint != null ? "exposePoint=" + exposePoint + ", "
						: "")
				+ (exposedData != null ? "exposedData=" + exposedData : "")
				+ "]";
	}

}