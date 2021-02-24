package intercorp.com.dto;

import intercorp.com.domain.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Calendar;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

public class ClientUtil {

    private static Logger log = LoggerFactory.getLogger(ClientUtil.class);

    public static double calculateAverageAge(List<ClientDto> listClientDto) {
        double prom = 0.0;
        for (int i = 0; i < listClientDto.size(); i++)
            prom += listClientDto.get(i).getAge();
        return prom / (double) listClientDto.size();
    }

    public static double calculateDeviationAge(List<ClientDto> listClientDto) {
        double prom, sum = 0;
        int i, n = listClientDto.size();
        prom = calculateAverageAge(listClientDto);
        for (i = 0; i < n; i++)
            sum += Math.pow(listClientDto.get(i).getAge() - prom, 2);
        return Math.sqrt(sum / (double) n);
    }

    public static LocalDate calculateDeadthAge(Client clientDto) {
        LocalDate date = clientDto.getBirthDate();
        Calendar calendar = Calendar.getInstance();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        int deadthYear = ((day * 2) + month) + month + sumNumbers(String.valueOf(year));
        return date.plusYears(deadthYear);
    }

    private static int sumNumbers(String number) {
        int sum = 0;
        if (Integer.parseInt(number) > 0 && Integer.parseInt(number) < Math.pow(10, 9)) {
            for (int i = 0; i < number.length(); i++) {
                sum += Character.getNumericValue(number.charAt(i));
            }
        }
        return sum;
    }

    public static int size(Iterable data) {

        if (data instanceof Collection) {
            return ((Collection<?>) data).size();
        }
        int counter = 0;
        for (Object i : data) {
            counter++;
        }
        return counter;
    }
}
