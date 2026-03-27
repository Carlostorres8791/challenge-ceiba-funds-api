package com.carlostorresdevjava.challenge.ceiba.funds.api.loader;

import com.carlostorresdevjava.challenge.ceiba.funds.api.model.Fund;
import com.carlostorresdevjava.challenge.ceiba.funds.api.repository.FundRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    private final FundRepository fundRepository;

    public DataLoader(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    public void run(String... args) {
        if (this.fundRepository.count() == 0L) {
            List<Fund> fondos = List.of(Fund.builder()
                    .nombre("FPV_BTG_PACTUAL_RECAUDADORA")
                    .montoMinimo((double)75000.0F)
                    .categoria("FPV")
                    .build(), Fund.builder()
                    .nombre("FPV_BTG_PACTUAL_ECOPETROL")
                    .montoMinimo((double)125000.0F)
                    .categoria("FPV")
                    .build(), Fund.builder()
                    .nombre("DEUDAPRIVADA")
                    .montoMinimo((double)50000.0F)
                    .categoria("FIC")
                    .build(), Fund.builder()
                    .nombre("FDO-ACCIONES")
                    .montoMinimo((double)250000.0F)
                    .categoria("FIC")
                    .build(), Fund.builder()
                    .nombre("FPV_BTG_PACTUAL_DINAMICA")
                    .montoMinimo((double)100000.0F)
                    .categoria("FPV").build());
            this.fundRepository.saveAll(fondos);
        }

    }
}
