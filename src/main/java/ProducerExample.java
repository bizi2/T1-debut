import org.apache.kafka.clients.producer.*;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ProducerExample {
    private static final String TOPIC = "weather-topic";
    private static final String[] CITIES = {"Москва", "Санкт-Петербург", "Новосибирск"};

    public static void main(String[] args) throws InterruptedException {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");


        Producer<String, String> producer = new KafkaProducer<>(props);


        while (true) {
            String city = CITIES[(int) (Math.random() * CITIES.length)];
            String weather = generateWeather();

            ProducerRecord<String, String> record =
                    new ProducerRecord<>(TOPIC, city, weather);

            producer.send(record, (metadata, e) -> {
                if (e == null) {
                    System.out.printf("Отправлено: city=%s, weather=%s\n", city, weather);
                } else {
                    System.err.println("Ошибка: " + e.getMessage());
                }
            });

            TimeUnit.SECONDS.sleep(3);
        }
    }

    private static String generateWeather() {
        String[] conditions = {"☀️ солнечно", "☁️ облачно", "🌧 дождь"};
        int temp = (int) (Math.random() * 35); // 0-35°C
        return conditions[(int) (Math.random() * conditions.length)] + ", " + temp + "°C";
    }
}