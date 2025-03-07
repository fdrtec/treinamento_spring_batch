package com.fdrtec.treinomigracaodados.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@EnableBatchProcessing
@Configuration
@RequiredArgsConstructor
public class MigracaoDadosJobConfig {
    
    private final JobBuilderFactory jobBuiderFactory;   

    @Bean
    public Job migracaoDadosJob(
            @Qualifier("migrarPessoaStep") Step migrarPessoaStep,
            @Qualifier("migrarDadosBancariosStep") Step migrarDadosBancariosStep) {
        return jobBuiderFactory.get("migracaoDadosJob")
                .start(migrarPessoaStep)
                .next(migrarDadosBancariosStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
