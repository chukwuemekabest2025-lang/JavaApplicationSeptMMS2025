import java.time.LocalDateTime;

public class UsingLocalDateTime{
	public static void main(String[] args){
		
		LocalDateTime todaysDateTime = LocalDateTime.now();
		LocalDateTime meetingDateTime = LocalDateTime.of(2021,05,15,06,30,45);
		LocalDateTime parsedDateTime = LocalDateTime.parse("2025-02-06T14:30:00");

		
		
		System.out.printf("The current date and time is %s%n",todaysDateTime);
		System.out.printf("The meeting date and time is %s%n",meetingDateTime);
		System.out.printf("THe date and time is %s%n",parsedDateTime);
		System.out.printf("The meeting year is %s%n",meetingDateTime.getYear());
		System.out.printf("The meeting month is %s%n",meetingDateTime.getMonth());
		System.out.printf("The meeting day of month is %s%n",meetingDateTime.getDayOfMonth());
		System.out.printf("The meeting hour is %s%n",meetingDateTime.getHour);
		System.out.printf("The meeting date will be %s%n",meetingDateTime.plusDays(10));
		System.out.printf("The meeting in hours will be %s%n",todaysDateTime.newDateTime); 
	}
}