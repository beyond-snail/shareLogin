package myyl.com.myyl.enums;


import myyl.com.myyl.R;

public class EnumConsts {





	public enum MenuType {
		MENU_1(1, R.mipmap.ic_launcher_round,"会诊"),
		MENU_2(2, R.mipmap.ic_launcher_round,"患者管理"),
		MENU_3(3, R.mipmap.ic_launcher_round,"找药"),
		MENU_4(4, R.mipmap.ic_launcher_round,"新药临床"),
		MENU_5(5, R.mipmap.ic_launcher_round,"FM"),
		MENU_6(6, R.mipmap.ic_launcher_round,"知问"),

		;

		private int code;
		private int bg;
		private String name;

		MenuType(int code, int bg, String name) {
			this.code = code;
			this.bg = bg;
			this.name = name;
		}

		public int getCode() {
			return code;
		}

		public int getBg() {
			return bg;
		}

		public String getName() {
			return name;
		}

		public static MenuType getByCode(int code) {
			MenuType[] timeZoneTypes = MenuType.values();
			for (MenuType timeZoneType : timeZoneTypes) {
				if (timeZoneType.getCode() == code) {
					return timeZoneType;
				}
			}
			return null;
		}

		public static int getCodeByName(String name) {
			MenuType[] timeZoneTypes = MenuType.values();
			for (MenuType timeZoneType : timeZoneTypes) {
				if (timeZoneType.getName() == name) {
					return timeZoneType.getCode();
				}
			}
			return -1;
		}
	}


//	public enum MineMenuType {
//		MENU_1(1, R.drawable.mywallet,"我的钱包"),
//		MENU_2(2, R.drawable.mygroup,"我的团队"),
//		MENU_3(3, R.drawable.myqrcode,"推广二维码"),
////		MENU_4(4, R.drawable.myvouchar,"抵用券"),
//		MENU_4(4, R.drawable.icon_scanqr,"扫码收钱"),
//
//		;
//
//		private int code;
//		private int bg;
//		private String name;
//
//		MineMenuType(int code, int bg, String name) {
//			this.code = code;
//			this.bg = bg;
//			this.name = name;
//		}
//
//		public int getCode() {
//			return code;
//		}
//
//		public int getBg() {
//			return bg;
//		}
//
//		public String getName() {
//			return name;
//		}
//
//		public static MineMenuType getByCode(int code) {
//			MineMenuType[] timeZoneTypes = MineMenuType.values();
//			for (MineMenuType timeZoneType : timeZoneTypes) {
//				if (timeZoneType.getCode() == code) {
//					return timeZoneType;
//				}
//			}
//			return null;
//		}
//
//		public static int getCodeByName(String name) {
//			if (StringUtils.isBlank(name)){
//				return -1;
//			}
//			MineMenuType[] timeZoneTypes = MineMenuType.values();
//			for (MineMenuType timeZoneType : timeZoneTypes) {
//				if (timeZoneType.getName() == name) {
//					return timeZoneType.getCode();
//				}
//			}
//			return -1;
//		}
//	}


}
