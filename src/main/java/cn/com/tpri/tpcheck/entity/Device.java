package cn.com.tpri.tpcheck.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_device")
public class Device {
	@Id
	@GeneratedValue(generator = "device_generator")
	@GenericGenerator(name = "device_generator", strategy = "increment")
	private long id;

	private String name;
	private String description;
	private int supOrSub;

	@ManyToOne
	private SuperDevice superDevice;

	@ManyToOne
	private DeviceType deviceType;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id")
	private Set<DeviceInfo> deviceInfos;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "device_id")
	private Set<DeviceCheckRecord> deviceCheckRecords;

	private String param1;
	private String param2;
	private String param3;
	private String param4;
	private String param5;
	private String param6;
	private String param7;
	private String param8;
	private String param9;
	private String param10;
	private String param11;
	private String param12;
	private String param13;
	private String param14;
	private String param15;
	private String param16;
	private String param17;
	private String param18;
	private String param19;
	private String param20;
	private String param21;
	private String param22;
	private String param23;
	private String param24;
	private String param25;
	private String param26;
	private String param27;
	private String param28;
	private String param29;
	private String param30;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public DeviceType getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(DeviceType deviceType) {
		this.deviceType = deviceType;
	}

	public Set<DeviceInfo> getDeviceInfos() {
		return deviceInfos;
	}

	public void setDeviceInfos(Set<DeviceInfo> deviceInfos) {
		this.deviceInfos = deviceInfos;
	}

	public Set<DeviceCheckRecord> getDeviceCheckRecords() {
		return deviceCheckRecords;
	}

	public void setDeviceCheckRecords(Set<DeviceCheckRecord> deviceCheckRecords) {
		this.deviceCheckRecords = deviceCheckRecords;
	}

	public SuperDevice getSuperDevice() {
		return superDevice;
	}

	public void setSuperDevice(SuperDevice superDevice) {
		this.superDevice = superDevice;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 = param4;
	}

	public String getParam5() {
		return param5;
	}

	public void setParam5(String param5) {
		this.param5 = param5;
	}

	public String getParam6() {
		return param6;
	}

	public void setParam6(String param6) {
		this.param6 = param6;
	}

	public String getParam7() {
		return param7;
	}

	public void setParam7(String param7) {
		this.param7 = param7;
	}

	public String getParam8() {
		return param8;
	}

	public void setParam8(String param8) {
		this.param8 = param8;
	}

	public String getParam9() {
		return param9;
	}

	public void setParam9(String param9) {
		this.param9 = param9;
	}

	public String getParam10() {
		return param10;
	}

	public void setParam10(String param10) {
		this.param10 = param10;
	}

	public String getParam11() {
		return param11;
	}

	public void setParam11(String param11) {
		this.param11 = param11;
	}

	public String getParam12() {
		return param12;
	}

	public void setParam12(String param12) {
		this.param12 = param12;
	}

	public String getParam13() {
		return param13;
	}

	public void setParam13(String param13) {
		this.param13 = param13;
	}

	public String getParam14() {
		return param14;
	}

	public void setParam14(String param14) {
		this.param14 = param14;
	}

	public String getParam15() {
		return param15;
	}

	public void setParam15(String param15) {
		this.param15 = param15;
	}

	public String getParam16() {
		return param16;
	}

	public void setParam16(String param16) {
		this.param16 = param16;
	}

	public String getParam17() {
		return param17;
	}

	public void setParam17(String param17) {
		this.param17 = param17;
	}

	public String getParam18() {
		return param18;
	}

	public void setParam18(String param18) {
		this.param18 = param18;
	}

	public String getParam19() {
		return param19;
	}

	public void setParam19(String param19) {
		this.param19 = param19;
	}

	public String getParam20() {
		return param20;
	}

	public void setParam20(String param20) {
		this.param20 = param20;
	}

	public String getParam21() {
		return param21;
	}

	public void setParam21(String param21) {
		this.param21 = param21;
	}

	public String getParam22() {
		return param22;
	}

	public void setParam22(String param22) {
		this.param22 = param22;
	}

	public String getParam23() {
		return param23;
	}

	public void setParam23(String param23) {
		this.param23 = param23;
	}

	public String getParam24() {
		return param24;
	}

	public void setParam24(String param24) {
		this.param24 = param24;
	}

	public String getParam25() {
		return param25;
	}

	public void setParam25(String param25) {
		this.param25 = param25;
	}

	public String getParam26() {
		return param26;
	}

	public void setParam26(String param26) {
		this.param26 = param26;
	}

	public String getParam27() {
		return param27;
	}

	public void setParam27(String param27) {
		this.param27 = param27;
	}

	public String getParam28() {
		return param28;
	}

	public void setParam28(String param28) {
		this.param28 = param28;
	}

	public String getParam29() {
		return param29;
	}

	public void setParam29(String param29) {
		this.param29 = param29;
	}

	public String getParam30() {
		return param30;
	}

	public void setParam30(String param30) {
		this.param30 = param30;
	}

	public String[] getParams() {
		String[] p = { param1, param2, param3, param4, param5, param6, param7, param8, param9, param10, param11,
				param12, param13, param14, param15, param16, param17, param18, param19, param20, param21, param22,
				param23, param24, param25, param26, param27, param28, param29, param30 };
		return p;
	}

	public void setParam(int i, String v) {
		switch (i) {
		case 1:
			param1 = v;
			break;
		case 2:
			param2 = v;
			break;
		case 3:
			param3 = v;
			break;
		case 4:
			param4 = v;
			break;
		case 5:
			param5 = v;
			break;
		case 6:
			param6 = v;
			break;
		case 7:
			param7 = v;
			break;
		case 8:
			param8 = v;
			break;
		case 9:
			param9 = v;
			break;
		case 10:
			param10 = v;
			break;
		case 11:
			param11 = v;
			break;
		case 12:
			param12 = v;
			break;
		case 13:
			param13 = v;
			break;
		case 14:
			param14 = v;
			break;
		case 15:
			param15 = v;
			break;
		case 16:
			param16 = v;
			break;
		case 17:
			param17 = v;
			break;
		case 18:
			param18 = v;
			break;
		case 19:
			param19 = v;
			break;
		case 20:
			param20 = v;
			break;
		case 21:
			param21 = v;
			break;
		case 22:
			param22 = v;
			break;
		case 23:
			param23 = v;
			break;
		case 24:
			param24 = v;
			break;
		case 25:
			param25 = v;
			break;
		case 26:
			param26 = v;
			break;
		case 27:
			param27 = v;
			break;
		case 28:
			param28 = v;
			break;
		case 29:
			param29 = v;
			break;
		case 30:
			param30 = v;
			break;
		}
	}

	public int getSupOrSub() {
		return supOrSub;
	}

	public void setSupOrSub(int supOrSub) {
		this.supOrSub = supOrSub;
	}
}
