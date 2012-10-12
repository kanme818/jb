package ky.jokebox.waterfall;

public class WaterFallItem {
	private String itemId;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	private String imgUrl;
	private String title;
	private String commentSegment;

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCommentSegment() {
		return commentSegment;
	}

	public void setCommentSegment(String commentSegment) {
		this.commentSegment = commentSegment;
	}

}
