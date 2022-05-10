package bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResultBean {

    @SerializedName("intent")
    private Intent intent;
    @SerializedName("results")
    private List<Results> results;

    public Intent getIntent() {
        return intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public static class Intent {
        @SerializedName("code")
        private int code;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    public static class Results {
        @SerializedName("groupType")
        private int groupType;
        @SerializedName("resultType")
        private String resultType;
        @SerializedName("values")
        private Values values;

        public int getGroupType() {
            return groupType;
        }

        public void setGroupType(int groupType) {
            this.groupType = groupType;
        }

        public String getResultType() {
            return resultType;
        }

        public void setResultType(String resultType) {
            this.resultType = resultType;
        }

        public Values getValues() {
            return values;
        }

        public void setValues(Values values) {
            this.values = values;
        }

        public static class Values {
            @SerializedName("text")
            private String text;
            private String url;
            private String voice;
            private String video;
            private String image;
            private String news;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getVoice() {
                return voice;
            }

            public void setVoice(String voice) {
                this.voice = voice;
            }

            public String getVideo() {
                return video;
            }

            public void setVideo(String video) {
                this.video = video;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public String getNews() {
                return news;
            }

            public void setNews(String news) {
                this.news = news;
            }

            public String getText() {
                text = text.replaceAll("[图][灵][工][程][师]", "李昊岩");

                return text;
            }

            public void setText(String text) {
                this.text = text;
            }
        }
    }
}