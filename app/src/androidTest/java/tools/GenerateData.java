package tools;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateData {

    private GenerateData() {
    }


    public static class AuthInfo {
        private final String login;
        private final String pass;

        public AuthInfo(String login, String pass) {
            this.login = login;
            this.pass = pass;
        }

        public String getLogin() {
            return login;
        }

        public String getPass() {
            return pass;
        }
    }

    public static AuthInfo authInfo() {
        String login = "login2";
        String pass = "password2";
        return new AuthInfo(login, pass);
    }

    public static AuthInfo invalidAuthInfo() {
        String login = "invalid";
        String pass = "invalid";
        return new AuthInfo(login, pass);
    }

    public static String generateTitleId() {
        String titleId = UUID.randomUUID().toString();
        return titleId;
    }

    public static LocalDateTime generateDate(int plusDays) {
        return LocalDateTime.now().plusDays(plusDays);
    }

    public static LocalDateTime getValidDate() {
        int randomNumber = ThreadLocalRandom.current().nextInt(1, 365);
        LocalDateTime date = generateDate(randomNumber);
        return date;
    }
    public static String getTitle() {
        return "Заголовок" + " " + generateTitleId();
    }

    public static String getDescription() {
        return "Описание" + " " + generateTitleId();
    }

    public static String getExecutorIvanov() {
        return "Иванов Иван Петрович";
    }

    public static String getExecutorSmirnov() {
        return "Смирнов Петр Иванович";
    }

    public static String getCategoryAnnouncement() {
        return "Объявление";
    }

    public static String getCategoryBirthday() {
        return "День рождения";
    }

    public static String getCategorySalary() {
        return "Зарплата";
    }

    public static String getCategoryTradeUnion() {
        return "Профсоюз";
    }

    public static String getCategoryHoliday() {
        return "Праздник";
    }

    public static String getCategoryGratitude() {
        return "Благодарность";
    }

    public static String getCategoryMassage() {
        return "Массаж";
    }
    public static String getCategoryNeedHelp() {
        return "Нужна помощь";
    }


    public static String getComment() {
        return "Комментарий" + " " + GenerateData.generateTitleId();
    }

    public static class CreateNews {
        public String category;
        public LocalDateTime dueDate;
        public String name;
        public String description;

        public CreateNews(Builder builder) {
            category = builder.category;
            dueDate = builder.dueDate;
            name = builder.name;
            description = builder.description;
        }


        public static class Builder {

            private String name;
            private String description;
            private LocalDateTime dueDate = LocalDateTime.now();
            private String category = GenerateData.getCategoryAnnouncement();


            public Builder(String newsTitle, String newsDescription) {
                this.name = newsTitle;
                this.description = newsDescription;
            }

            public Builder() {

            }

            public Builder withCategory(String val) {
                this.category = val;
                return this;
            }

            public Builder withDueDate(LocalDateTime val) {
                this.dueDate = val;
                return this;
            }

            public Builder withName(String name) {
                this.name = name;
                return this;
            }

            public Builder withDescription(String description) {
                this.description = description;
                return this;
            }

            public Builder(String category, LocalDateTime dueDate, String name, String description) {
                this.category = category;
                this.dueDate = dueDate;
                this.name = name;
                this.description = description;
            }

            public CreateNews build() {
                return new CreateNews(this);
            }
        }

        public String getNewsCategory() {
            return category;
        }

        public LocalDateTime getDueDate() {
            return dueDate;
        }

        public String getNewsName() {
            return name;
        }

        public String getNewsDescription() {
            return description;
        }

    }

    public static CreateNews.Builder newsWithRandomNameAndDescription() {
        CreateNews.Builder createNews = new CreateNews.Builder(getTitle(), getDescription());
        return createNews;
    }


}