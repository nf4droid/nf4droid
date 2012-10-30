package at.tugraz.iaik.nf4droid.shared.constant;

public enum DroidEthernetType {

	IPV4(0x0800), IPV6(0x86DD), ARP(0x0806), RARP(0x8035);

	private final Integer value;

	DroidEthernetType(Integer value) {
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

}
