package database;

public class UserRowData {
//	const column_spec users_columns[] =
//		{{"Id",             CT_INT},
//		 {"Reputation",     CT_INT},
//		 {"CreationDate",   CT_DATE},
//		 {"DisplayName",    CT_VCHR64}, // 32 would also be enough
//		 {"EmailHash",      CT_TEXT},
//		 {"LastAccessDate", CT_DATE},
//		 {"WebsiteUrl",     CT_TEXT},
//		 {"Location",       CT_TEXT},
//		 {"AboutMe",        CT_TEXT},
//		 {"Views",          CT_INT},
//		 {"UpVotes",        CT_INT},
//		 {"DownVotes",      CT_INT},
// 		 {"ProfileImageUrl",        CT_TEXT},
// 		 {"Age",            CT_INT},		 
//		 {"AccountId",            CT_INT},		 
//		 {0}};
	
	public long id;
	public long reputation;
	public String creationDate;
	public String displayName;
	public String emailHash;
	public String lastAccessDate;
	public String websiteUrl;
	public String location;
	public String aboutMe;
	public long views;
	public long upVotes;
	public long downVotes;
	public String profileImageUrl;
	public long age;
	public long accountId;
	public UserRowData(long id, long reputation, String creationDate,
			String displayName, String emailHash, String lastAccessDate,
			String websiteUrl, String location, String aboutMe, long views,
			long upVotes, long downVotes, String profileImageUrl, long age,
			long accountId) {
		this.id = id;
		this.reputation = reputation;
		this.creationDate = creationDate;
		this.displayName = displayName;
		this.emailHash = emailHash;
		this.lastAccessDate = lastAccessDate;
		this.websiteUrl = websiteUrl;
		this.location = location;
		this.aboutMe = aboutMe;
		this.views = views;
		this.upVotes = upVotes;
		this.downVotes = downVotes;
		this.profileImageUrl = profileImageUrl;
		this.age = age;
		this.accountId = accountId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserRowData [id=");
		builder.append(id);
		builder.append(", reputation=");
		builder.append(reputation);
		builder.append(", creationDate=");
		builder.append(creationDate);
		builder.append(", displayName=");
		builder.append(displayName);
		builder.append(", emailHash=");
		builder.append(emailHash);
		builder.append(", lastAccessDate=");
		builder.append(lastAccessDate);
		builder.append(", websiteUrl=");
		builder.append(websiteUrl);
		builder.append(", location=");
		builder.append(location);
		builder.append(", aboutMe=");
		builder.append(aboutMe);
		builder.append(", views=");
		builder.append(views);
		builder.append(", upVotes=");
		builder.append(upVotes);
		builder.append(", downVotes=");
		builder.append(downVotes);
		builder.append(", profileImageUrl=");
		builder.append(profileImageUrl);
		builder.append(", age=");
		builder.append(age);
		builder.append(", accountId=");
		builder.append(accountId);
		builder.append("]");
		return builder.toString();
	}

}
