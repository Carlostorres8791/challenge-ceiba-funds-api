package com.carlostorresdevjava.challenge.ceiba.funds.api.loader;

import com.carlostorresdevjava.challenge.ceiba.funds.api.model.Fund;
import com.carlostorresdevjava.challenge.ceiba.funds.api.repository.FundRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final FundRepository fundRepository;

    public DataLoader(FundRepository fundRepository) {
        this.fundRepository = fundRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(" Ejecutando DataLoader...");

        if (fundRepository.count() == 0) {

            List<Fund> fondos = List.of(
                    Fund.builder()
                            .nombre("FPV_BTG_PACTUAL_RECAUDADORA")
                            .montoMinimo(75000.0)
                            .categoria("FPV")
                            .build(),

                    Fund.builder()
                            .nombre("FPV_BTG_PACTUAL_ECOPETROL")
                            .montoMinimo(125000.0)
                            .categoria("FPV")
                            .build(),

                    Fund.builder()
                            .nombre("DEUDAPRIVADA")
                            .montoMinimo(50000.0)
                            .categoria("FIC")
                            .build(),

                    Fund.builder()
                            .nombre("FDO-ACCIONES")
                            .montoMinimo(250000.0)
                            .categoria("FIC")
                            .build(),

                    Fund.builder()
                            .nombre("FPV_BTG_PACTUAL_DINAMICA")
                            .montoMinimo(100000.0)
                            .categoria("FPV")
                            .build()
            );

            fundRepository.saveAll(fondos);

            System.out.println("✅ Fondos cargados en la base de datos");
        } else {
            System.out.println("ℹ️ Los fondos ya existen, no se cargaron nuevamente");
        }
    }
}