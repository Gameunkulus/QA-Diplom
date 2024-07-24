package screens.filter.blocks;

import io.qameta.allure.kotlin.Allure;

public class ReadyNews {
        int newsCategoryId = 5;
        String title = "Праздник";
        String description = "Задорно отметим";
        int createDate = 1668925809;
        int publishDate = 1668936600;
        boolean publishEnabled = true;
        String creatorName = "Иванов Данил Данилович";


        public int getNewsCategoryId() {
                Allure.step("Обращение к Id категории новости." );
                return newsCategoryId;
        }

        public String getTitle() {
                Allure.step("Обращение к тайтлу новости." );
                return title;
        }

        public String getDescription() {
                Allure.step("Обращение к описанию новости." );
                return description;
        }

        public int getCreateDate() {
                Allure.step("Обращение к дате создания." );
                return createDate;
        }

        public int getPublishDate() {
                Allure.step("Обращение к дате публикации." );
                return publishDate;
        }

        public boolean isPublishEnabled() {
                Allure.step("Обращение к статусу публикации." );
                return publishEnabled;
        }

        public String getCreatorName() {
                Allure.step("Обращение к имени автора новости." );
                return creatorName;
        }

}