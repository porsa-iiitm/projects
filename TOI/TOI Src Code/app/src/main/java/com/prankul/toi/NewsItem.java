package com.prankul.toi;

/**
 * Created by prankul on 14/6/15.
 */
public class NewsItem {

    private String NewsItemId;
    private String HeadLine;
    private String ByLine;
    private String Agency;
    private String DateLine;
    private Image image;

    public String getHeadLine() {
        return HeadLine;
    }

    public void setHeadLine(String headLine) {
        HeadLine = headLine;
    }

    public String getByLine() {
        return ByLine;
    }

    public void setByLine(String byLine) {
        ByLine = byLine;
    }

    public String getAgency() {
        return Agency;
    }

    public void setAgency(String agency) {
        Agency = agency;
    }

    public String getDateLine() {
        return DateLine;
    }

    public void setDateLine(String dateLine) {
        DateLine = dateLine;
    }

    public String getNewsItemId() {
        return NewsItemId;
    }

    public void setNewsItemId(String newsItemId) {
        NewsItemId = newsItemId;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
