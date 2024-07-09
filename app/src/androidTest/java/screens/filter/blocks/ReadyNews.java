package screens.filter.blocks;

public class ReadyNews {
        int newsCategoryId = 5;
        String title = "Праздник";
        String description = "Задорно отметим";
        int createDate = 1668925809;
        int publishDate = 1668936600;
        boolean publishEnabled = true;
        String creatorName = "Иванов Данил Данилович";


        public int getNewsCategoryId() {
                return newsCategoryId;
        }

        public String getTitle() {
                return title;
        }

        public String getDescription() {
                return description;
        }

        public int getCreateDate() {
                return createDate;
        }

        public int getPublishDate() {
                return publishDate;
        }

        public boolean isPublishEnabled() {
                return publishEnabled;
        }

        public String getCreatorName() {
                return creatorName;
        }

}