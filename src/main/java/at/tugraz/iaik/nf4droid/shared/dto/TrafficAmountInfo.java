package at.tugraz.iaik.nf4droid.shared.dto;

import java.io.Serializable;

import com.google.gwt.user.client.rpc.IsSerializable;
import com.mysema.query.annotations.QueryProjection;

public class TrafficAmountInfo implements Serializable, IsSerializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long trafficAmount;
	private Long packetCount;
	private String description;
	
	public TrafficAmountInfo() {
		super();
	}
	
	@QueryProjection
	public TrafficAmountInfo(Long trafficAmount, Long packetCount) {
		super();
		this.trafficAmount = trafficAmount;
		this.packetCount = packetCount;
	}

	@QueryProjection
	public TrafficAmountInfo(Long trafficAmount, Long packetCount,
			String description) {
		super();
		this.trafficAmount = trafficAmount;
		this.packetCount = packetCount;
		this.description = description;
	}

	/**
	 * @return the trafficAmount
	 */
	public Long getTrafficAmount() {
		return trafficAmount;
	}

	/**
	 * @param trafficAmount the trafficAmount to set
	 */
	public void setTrafficAmount(Long trafficAmount) {
		this.trafficAmount = trafficAmount;
	}

	/**
	 * @return the packetCount
	 */
	public Long getPacketCount() {
		return packetCount;
	}

	/**
	 * @param packetCount the packetCount to set
	 */
	public void setPacketCount(Long packetCount) {
		this.packetCount = packetCount;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TrafficAmountInfo ["
				+ (trafficAmount != null ? "trafficAmount=" + trafficAmount
						+ ", " : "")
				+ (packetCount != null ? "packetCount=" + packetCount + ", "
						: "")
				+ (description != null ? "description=" + description : "")
				+ "]";
	}
	
}
