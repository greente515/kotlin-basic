package com.example.helloworld.common.config

import com.zaxxer.hikari.HikariDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import org.springframework.transaction.support.TransactionSynchronizationManager
import javax.sql.DataSource

@Configuration
class DataSourceConfig {

    /**
     * application.properties 에서 db 연결을 위한 설정값으로 만든다.
     * 한글입력처리를 위해 SET NAME utf8 추가 - 여러 db 를 연결할 때 설정, 하나만 사용한다면 굳이 dataSource 만들 필요 없음
     */
    @Bean("maserDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master.hikari")
    fun masterDataSource(): DataSource {
        val dataSource = DataSourceBuilder.create().type(HikariDataSource::class.java).build()
        //UTF-8
        dataSource.connectionInitSql = "SET NAMES utf8mb4"

        return dataSource
    }

    @Bean("slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave.hikari")
    fun slaveDataSource(): DataSource {
        val dataSource = DataSourceBuilder.create().type(HikariDataSource::class.java).build()
        //UTF-8
        dataSource.connectionInitSql = "SET NAMES utf8mb4"

        return dataSource
    }

    /**
     * routingDatsSource 를 바로 전달하는 경우 transaction 이 readonly 일때도 write 로 붙음
     * 이때 LazyConnectionDatsSourceProxy 로 감싸주면 해결 됨
     */
    @Bean("routingDataSource")
    fun routingDataSource(): DataSource {
        val dataSources = mapOf<Any, Any>(
            DataSourceType.READ to slaveDataSource(),
            DataSourceType.WRITE to masterDataSource()
        )

        val routingDataSource = ReplicationRoutingDataSource()
        with(routingDataSource) {
            setTargetDataSources(dataSources)
            setDefaultTargetDataSource(slaveDataSource())
        }

        return routingDataSource
    }

    @Primary
    @Bean("defaultDataSource")
    fun dataSource(): DataSource {
        return LazyConnectionDataSourceProxy(routingDataSource())
    }

    class ReplicationRoutingDataSource : AbstractRoutingDataSource() {

        override fun determineCurrentLookupKey(): DataSourceType {
            val dataSourceType =
                if (TransactionSynchronizationManager.isActualTransactionActive() && TransactionSynchronizationManager.isCurrentTransactionReadOnly()) {
                    DataSourceType.READ
                } else {
                    DataSourceType.WRITE
                }

            logger.info("datermineCurrentLookupKey() getCurrentTransactionName: ${TransactionSynchronizationManager.getCurrentTransactionName()}")
            logger.info("datermineCurrentLookupKey() isActualTransactionActive: ${TransactionSynchronizationManager.isActualTransactionActive()}")
            logger.info("datermineCurrentLookupKey() isCurrentTransactionReadonly: ${TransactionSynchronizationManager.isCurrentTransactionReadOnly()}")
            logger.info("> current dataSourceType: $dataSourceType")

            return dataSourceType
        }
    }
}

enum class DataSourceType {
    READ, WRITE
}
