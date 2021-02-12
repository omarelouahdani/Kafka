package ma.enset.streamservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PageViewEventProcessor {
    ObjectMapper objectMapper=new ObjectMapper();
    @StreamListener
    @SendTo({AnalyticsBinding.PAGE_VIEWS_OUT})
    public KStream<String, Double> process(@Input(AnalyticsBinding.PAGE_VIEWS_IN) KStream<Long, String> events){
        return events
                .map((k,v)->new KeyValue<>(k,facture(v)))
                .map((k,v)->new KeyValue<>(v.getNomClient(),v.getMontant()))
                .groupByKey()
                .windowedBy(TimeWindows.of(5000))
                .reduce(Double::sum).toStream()
                .map((k,v)->new KeyValue<>(k.key(),v));

//        sum.to("clienttotal",Produced.with(Serdes.String(),Serdes.Double()));

    }
    public Facture facture(String strObject){
        Facture facture=new Facture();
        try {
            facture=objectMapper.readValue(strObject,Facture.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return facture;
    }
}