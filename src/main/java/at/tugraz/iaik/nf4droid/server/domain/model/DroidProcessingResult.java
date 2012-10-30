package at.tugraz.iaik.nf4droid.server.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Version;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.transaction.annotation.Transactional;

import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;

@Configurable
@Entity
public class DroidProcessingResult {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "DroidProcResTcpCons", joinColumns = @JoinColumn(name = "DROID_PROC_RES_ID", nullable = false, insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "TCP_CON_ID", nullable = false, insertable = false, updatable = false))
	private List<DroidTcpConnection> tcpConnections;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "DroidProcResHttpRequests", joinColumns = @JoinColumn(name = "DROID_PROC_RES_ID", nullable = false, insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "HTTP_REQUEST_ID", nullable = false, insertable = false, updatable = false))
	private List<DroidHttpRequest> httpRequests;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "DroidProcResHttpResponses", joinColumns = @JoinColumn(name = "DROID_PROC_RES_ID", nullable = false, insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "HTTP_REQUEST_ID", nullable = false, insertable = false, updatable = false))
	private List<DroidHttpResponse> httpResponses;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "DroidProcResArpPackets", joinColumns = @JoinColumn(name = "DROID_PROC_RES_ID", nullable = false, insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "ARP_PACKET_ID", nullable = false, insertable = false, updatable = false))
	private List<DroidArpPacket> arpPackets;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "DroidProcResEthernetFrames", joinColumns = @JoinColumn(name = "DROID_PROC_RES_ID", nullable = false, insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "ETH_FRAME_ID", nullable = false, insertable = false, updatable = false))
	private List<DroidEthernetFrame> ethernetFrames;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "DroidProcResIcmpPackets", joinColumns = @JoinColumn(name = "DROID_PROC_RES_ID", nullable = false, insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "ICMP_PACKET_ID", nullable = false, insertable = false, updatable = false))
	private List<DroidIcmpPacket> icmpPackets;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "DroidProcResIcmpv6Packets", joinColumns = @JoinColumn(name = "DROID_PROC_RES_ID", nullable = false, insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "ICMPV6_PACKET_ID", nullable = false, insertable = false, updatable = false))
	private List<DroidIcmpv6Packet> icmpv6Packets;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "DroidProcResIpv4Packets", joinColumns = @JoinColumn(name = "DROID_PROC_RES_ID", nullable = false, insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "IPV4_PACKET_ID", nullable = false, insertable = false, updatable = false))
	private List<DroidIpv4Packet> ipv4Packets;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "DroidProcResIpv6Packets", joinColumns = @JoinColumn(name = "DROID_PROC_RES_ID", nullable = false, insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "IPV6_PACKET_ID", nullable = false, insertable = false, updatable = false))
	private List<DroidIpv6Packet> ipv6Packets;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "DroidProcResPcapPackets", joinColumns = @JoinColumn(name = "DROID_PROC_RES_ID", nullable = false, insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "PCAP_PACKET_ID", nullable = false, insertable = false, updatable = false))
	private List<DroidPcapPacket> pcapPackets;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "DroidProcResTcpSegmnets", joinColumns = @JoinColumn(name = "DROID_PROC_RES_ID", nullable = false, insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "TCP_SEGM_ID", nullable = false, insertable = false, updatable = false))
	private List<DroidTcpSegment> tcpSegments;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "DroidProcResUdpPackets", joinColumns = @JoinColumn(name = "DROID_PROC_RES_ID", nullable = false, insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "UDP_PACKET_ID", nullable = false, insertable = false, updatable = false))
	private List<DroidUdpPacket> udpPackets;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "DroidProcResDataExposes", joinColumns = @JoinColumn(name = "DROID_PROC_RES_ID", nullable = false, insertable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "DATA_EXPOSE_ID", nullable = false, insertable = false, updatable = false))
	private List<DataExpose> dataExposes;

	public void addEthernetFrame(DroidEthernetFrame ethernetFrame) {
		if (ethernetFrames == null) {
			ethernetFrames = new ArrayList<DroidEthernetFrame>();
		}
		ethernetFrames.add(ethernetFrame);
	}

	public void addIpv4Packet(DroidIpv4Packet ipv4Packet) {
		if (ipv4Packets == null) {
			ipv4Packets = new ArrayList<DroidIpv4Packet>();
		}
		ipv4Packets.add(ipv4Packet);
	}

	public void addTcpSegment(DroidTcpSegment tcpSegment) {
		if (tcpSegments == null) {
			tcpSegments = new ArrayList<DroidTcpSegment>();
		}
		tcpSegments.add(tcpSegment);
	}

	public void addHttpRequest(DroidHttpRequest request) {
		if (httpRequests == null) {
			httpRequests = new ArrayList<DroidHttpRequest>();
		}
		httpRequests.add(request);
	}

	public void addHttpResponse(DroidHttpResponse response) {
		if (httpResponses == null) {
			httpResponses = new ArrayList<DroidHttpResponse>();
		}
		httpResponses.add(response);
	}

	public void addIpv6Packet(DroidIpv6Packet packet) {
		if (ipv6Packets == null) {
			ipv6Packets = new ArrayList<DroidIpv6Packet>();
		}
		ipv6Packets.add(packet);
	}

	public void addArpPacket(DroidArpPacket packet) {
		if (arpPackets == null) {
			arpPackets = new ArrayList<DroidArpPacket>();
		}
		arpPackets.add(packet);
	}

	public void addIcmpPacket(DroidIcmpPacket packet) {
		if (icmpPackets == null) {
			icmpPackets = new ArrayList<DroidIcmpPacket>();
		}
		icmpPackets.add(packet);
	}

	public void addIcmpv6Packet(DroidIcmpv6Packet packet) {
		if (icmpv6Packets == null) {
			icmpv6Packets = new ArrayList<DroidIcmpv6Packet>();
		}
		icmpv6Packets.add(packet);
	}

	public void addPcapPacket(DroidPcapPacket packet) {
		if (pcapPackets == null) {
			pcapPackets = new ArrayList<DroidPcapPacket>();
		}
		pcapPackets.add(packet);
	}

	public void addUdpPacket(DroidUdpPacket packet) {
		if (udpPackets == null) {
			udpPackets = new ArrayList<DroidUdpPacket>();
		}
		udpPackets.add(packet);
	}

	public void addTcpConnection(DroidTcpConnection connection) {
		if (tcpConnections == null) {
			tcpConnections = new ArrayList<DroidTcpConnection>();
		}
		tcpConnections.add(connection);
	}
	
	public void addDataExpose(DataExpose dataExpose) {
		if (dataExposes == null) {
			dataExposes = new ArrayList<DataExpose>();
		}
		dataExposes.add(dataExpose);
	}

	protected static JPAQuery from(EntityPath<?> o) {
		return new JPAQuery(DroidProcessingResult.entityManager()).from(o);
	}

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@PersistenceContext
    transient EntityManager entityManager;

	public static final EntityManager entityManager() {
        EntityManager em = new DroidProcessingResult().entityManager;
        if (em == null) throw new IllegalStateException("Entity manager has not been injected (is the Spring Aspects JAR configured as an AJC/AJDT aspects library?)");
        return em;
    }

	public static long countDroidProcessingResults() {
        return entityManager().createQuery("SELECT COUNT(o) FROM DroidProcessingResult o", Long.class).getSingleResult();
    }

	public static List<DroidProcessingResult> findAllDroidProcessingResults() {
        return entityManager().createQuery("SELECT o FROM DroidProcessingResult o", DroidProcessingResult.class).getResultList();
    }

	public static DroidProcessingResult findDroidProcessingResult(Long id) {
        if (id == null) return null;
        return entityManager().find(DroidProcessingResult.class, id);
    }

	public static List<DroidProcessingResult> findDroidProcessingResultEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM DroidProcessingResult o", DroidProcessingResult.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

	@Transactional
    public void persist() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.persist(this);
    }

	@Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            DroidProcessingResult attached = DroidProcessingResult.findDroidProcessingResult(this.id);
            this.entityManager.remove(attached);
        }
    }

	@Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.flush();
    }

	@Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

	@Transactional
    public DroidProcessingResult merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        DroidProcessingResult merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	public List<DroidTcpConnection> getTcpConnections() {
        return this.tcpConnections;
    }

	public void setTcpConnections(List<DroidTcpConnection> tcpConnections) {
        this.tcpConnections = tcpConnections;
    }

	public List<DroidHttpRequest> getHttpRequests() {
        return this.httpRequests;
    }

	public void setHttpRequests(List<DroidHttpRequest> httpRequests) {
        this.httpRequests = httpRequests;
    }

	public List<DroidHttpResponse> getHttpResponses() {
        return this.httpResponses;
    }

	public void setHttpResponses(List<DroidHttpResponse> httpResponses) {
        this.httpResponses = httpResponses;
    }

	public List<DroidArpPacket> getArpPackets() {
        return this.arpPackets;
    }

	public void setArpPackets(List<DroidArpPacket> arpPackets) {
        this.arpPackets = arpPackets;
    }

	public List<DroidEthernetFrame> getEthernetFrames() {
        return this.ethernetFrames;
    }

	public void setEthernetFrames(List<DroidEthernetFrame> ethernetFrames) {
        this.ethernetFrames = ethernetFrames;
    }

	public List<DroidIcmpPacket> getIcmpPackets() {
        return this.icmpPackets;
    }

	public void setIcmpPackets(List<DroidIcmpPacket> icmpPackets) {
        this.icmpPackets = icmpPackets;
    }

	public List<DroidIcmpv6Packet> getIcmpv6Packets() {
        return this.icmpv6Packets;
    }

	public void setIcmpv6Packets(List<DroidIcmpv6Packet> icmpv6Packets) {
        this.icmpv6Packets = icmpv6Packets;
    }

	public List<DroidIpv4Packet> getIpv4Packets() {
        return this.ipv4Packets;
    }

	public void setIpv4Packets(List<DroidIpv4Packet> ipv4Packets) {
        this.ipv4Packets = ipv4Packets;
    }

	public List<DroidIpv6Packet> getIpv6Packets() {
        return this.ipv6Packets;
    }

	public void setIpv6Packets(List<DroidIpv6Packet> ipv6Packets) {
        this.ipv6Packets = ipv6Packets;
    }

	public List<DroidPcapPacket> getPcapPackets() {
        return this.pcapPackets;
    }

	public void setPcapPackets(List<DroidPcapPacket> pcapPackets) {
        this.pcapPackets = pcapPackets;
    }

	public List<DroidTcpSegment> getTcpSegments() {
        return this.tcpSegments;
    }

	public void setTcpSegments(List<DroidTcpSegment> tcpSegments) {
        this.tcpSegments = tcpSegments;
    }

	public List<DroidUdpPacket> getUdpPackets() {
        return this.udpPackets;
    }

	public void setUdpPackets(List<DroidUdpPacket> udpPackets) {
        this.udpPackets = udpPackets;
    }

	public List<DataExpose> getDataExposes() {
        return this.dataExposes;
    }

	public void setDataExposes(List<DataExpose> dataExposes) {
        this.dataExposes = dataExposes;
    }

	@Version
    @Column(name = "version")
    private Integer version;

	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }
}
