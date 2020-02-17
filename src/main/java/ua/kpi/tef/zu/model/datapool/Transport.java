package ua.kpi.tef.zu.model.datapool;

/**
 * Created by Anton Domin on 2020-02-15
 */

public enum Transport {
	TRAIN("transport.train"),
	BUS("transport.bus"),
	PLANE("transport.plane"),
	NONE("transport.none");

	private String value;

	Transport(String value) {
		this.value = value;
	}

	@Override
	public String toString() { return value; }
}
