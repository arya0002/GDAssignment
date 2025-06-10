// utils/generic/YamlDataProvider.java
package utils.generic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.testng.annotations.DataProvider;
import pojo.AccountData;
import pojo.AccountDataWrapper;

import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class YamlDataProvider {

    @DataProvider(name = "yamlDataProvider")
    public static Object[][] yamlDataProvider(Method testMethod) throws Exception {
        YamlDataSource source = testMethod.getAnnotation(YamlDataSource.class);
        if (source == null) {
            throw new RuntimeException("Missing @YamlDataSource annotation.");
        }

        String yamlPath = source.value();
        InputStream inputStream = new FileInputStream(yamlPath);
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        AccountDataWrapper wrapper = mapper.readValue(inputStream, AccountDataWrapper.class);

        List<AccountData> accountList = wrapper.getTestData();

        for (AccountData acc : accountList) {
            if (acc.getEmail() != null && acc.getEmail().contains("__RANDOM_EMAIL__")) {
                acc.setEmail(generateRandomEmail());
            }
            if (acc.getPhone() != null && acc.getPhone().contains("__RANDOM_PHONE__")) {
                acc.setPhone(generateRandomPhone());
            }
            if (acc.getLicenseNumber() != null && acc.getLicenseNumber().contains("__RANDOM_LICENSE__")) {
                acc.setLicenseNumber(generateRandomLicenseNumber());
            }
            if (acc.getParticipantId() != null && acc.getParticipantId().contains("__RANDOM_PARTICIPANT_ID__")) {
                acc.setParticipantId(generateRandomParticipantId());
            }
            if (acc.getDob() != null && acc.getDob().contains("__TODAY_MINUS_YEARS_")) {
                acc.setDob(generateDateRelativeToToday(acc.getDob()));
            }
            if (acc.getLicenseExp() != null && acc.getLicenseExp().contains("__TODAY_PLUS_YEARS_")) {
                acc.setLicenseExp(generateDateRelativeToToday(acc.getLicenseExp()));
            }
            if (acc.getJoinDate() != null && acc.getJoinDate().contains("__TODAY_PLUS_DAYS_")) {
                acc.setJoinDate(generateDateRelativeToToday(acc.getJoinDate()));
            }
        }

        Object[][] result = new Object[accountList.size()][1];
        for (int i = 0; i < accountList.size(); i++) {
            result[i][0] = accountList.get(i);
        }
        return result;
    }

    private static String generateRandomEmail() {
        return "user" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";
    }

    private static String generateRandomPhone() {
        int num = ThreadLocalRandom.current().nextInt(1000000000, 1999999999);
        return String.valueOf(num);
    }

    private static String generateRandomLicenseNumber() {
        return "LIC" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    private static String generateRandomParticipantId() {
        return String.format("%09d", ThreadLocalRandom.current().nextInt(100000000, 1000000000));
    }

    private static String generateDateRelativeToToday(String placeholder) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMMM-yyyy");
        LocalDate date = LocalDate.now();

        if (placeholder.matches("__TODAY_MINUS_YEARS_\\d+__")) {
            int years = Integer.parseInt(placeholder.replaceAll("\\D+", ""));
            date = date.minusYears(years);
        } else if (placeholder.matches("__TODAY_PLUS_YEARS_\\d+__")) {
            int years = Integer.parseInt(placeholder.replaceAll("\\D+", ""));
            date = date.plusYears(years);
        } else if (placeholder.matches("__TODAY_PLUS_DAYS_\\d+__")) {
            int days = Integer.parseInt(placeholder.replaceAll("\\D+", ""));
            date = date.plusDays(days);
        } else {
            throw new IllegalArgumentException("Unsupported date placeholder: " + placeholder);
        }

        return date.format(formatter); // 👈 Ensures format like 10-June-2006
    }
}
