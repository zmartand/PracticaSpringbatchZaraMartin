package io.bootify.practica_springbatch.config;

import io.bootify.practica_springbatch.domain.Transacciones;
import org.springframework.batch.item.ItemProcessor;

public class TransaccionesItemProcessor implements ItemProcessor<Transacciones, Transacciones> {

    @Override
    public Transacciones process(Transacciones item) throws Exception {
        return item;
    }

}
