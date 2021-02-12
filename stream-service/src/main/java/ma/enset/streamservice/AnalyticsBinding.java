package ma.enset.streamservice;


import org.apache.kafka.streams.kstream.KStream;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
public interface AnalyticsBinding{
    String PAGE_VIEWS_OUT="clienttotal";
    String PAGE_VIEWS_IN="FACTURATION";
    @Output(PAGE_VIEWS_OUT)
    KStream<String, Double> pageViewsOut();
    @Input(PAGE_VIEWS_IN)
    KStream<Long, String> pageViewsIn();
}
