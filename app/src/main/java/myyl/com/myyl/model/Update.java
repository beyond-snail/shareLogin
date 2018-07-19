package myyl.com.myyl.model;

/**
 * 应用程序更新实体类
 * 
 * @author morton
 * 
 */
public class Update {


	private int id;
	private int types;
	private int versions;
	private int mustUpdate;
	private String message;
	private String down_url;



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTypes() {
		return types;
	}

	public void setTypes(int types) {
		this.types = types;
	}

	public int getVersions() {
		return versions;
	}

	public void setVersions(int versions) {
		this.versions = versions;
	}

	public int getMustUpdate() {
		return mustUpdate;
	}

	public void setMustUpdate(int mustUpdate) {
		this.mustUpdate = mustUpdate;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDown_url() {
		return down_url;
	}

	public void setDown_url(String down_url) {
		this.down_url = down_url;
	}
}
