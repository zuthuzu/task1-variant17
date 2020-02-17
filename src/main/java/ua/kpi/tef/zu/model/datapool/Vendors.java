package ua.kpi.tef.zu.model.datapool;

/**
 * Created by Anton Domin on 2020-02-18
 */

@SuppressWarnings("SpellCheckingInspection")
public enum Vendors {
	H_M("vendor.hm"),
	MASSIMO_DUTTI("vendor.massimodutti"),
	BENETTON("vendor.benetton"),
	GUCCI("vendor.gucci"),
	LOUIS_VUITTON("vendor.lv"),
	CHANNEL("vendor.channel");

	private String value;

	Vendors(String value) {
		this.value = value;
	}

	@Override
	public String toString() { return value; }
}
