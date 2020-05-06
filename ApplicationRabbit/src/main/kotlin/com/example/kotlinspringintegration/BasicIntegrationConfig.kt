package com.example.kotlinspringintegration

import com.example.unmarsh.UnmarshallinXML
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.annotation.ServiceActivator
import org.springframework.integration.channel.DirectChannel
import org.springframework.integration.core.MessageSource
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlows
import org.springframework.integration.dsl.Pollers
import org.springframework.integration.file.FileReadingMessageSource
import org.springframework.integration.file.FileWritingMessageHandler
import org.springframework.integration.file.filters.CompositeFileListFilter
import org.springframework.integration.file.filters.LastModifiedFileListFilter
import org.springframework.integration.file.filters.SimplePatternFileListFilter
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.MessageHandler
import java.io.File
import java.util.function.Consumer

const val INPUT_DIR : String = "C:\\Users\\nata\\Desktop\\a"        //Корневая директория
const val OUTPUT_DIR : String = "C:\\Users\\nata\\Desktop\\b"       //Целевая директория
const val ERROR_DIR : String = "C:\\Users\\nata\\Desktop\\e"        //Директория с некорректными файлами (Архив)

@Configuration
class BasicIntegrationConfig(
        val un : UnmarshallinXML
) {
    @Bean
    fun outPutChannel(): MessageChannel = DirectChannel()       //Канал для работы с целевой директорией
    @Bean
    fun errorChannel(): MessageChannel = DirectChannel()        //Канал для работы с архивом

    @Bean
    fun sourceDirectory(): MessageSource<File> {                //Обработчик целевой директории
        val filters = CompositeFileListFilter<File>()
        filters.addFilter(SimplePatternFileListFilter("*.xml"))
        filters.addFilter(LastModifiedFileListFilter())

        val messageSource = FileReadingMessageSource()
        messageSource.setDirectory(File(INPUT_DIR))
        messageSource.setFilter(filters)
        return messageSource
    }

    @Bean
    @ServiceActivator(inputChannel = "outPutChannel")
    fun targetDirectory(): MessageHandler {                         //Обработчик целевой директории
        val handler = FileWritingMessageHandler(File(OUTPUT_DIR))
        handler.setExpectReply(false)
        handler.setDeleteSourceFiles(true)
        return handler
    }

    @Bean
    fun fileMover(): IntegrationFlow {                      //Обработчик потока сообщений корневой директории
        return IntegrationFlows
                .from(sourceDirectory(), Consumer{e -> e.poller(Pollers.fixedDelay(4000))})
                .route<Message<File>,Boolean>({m -> un.unmarshalling(m.payload.path)}, {t -> t
                        .channelMapping(true,"outPutChannel")
                        .channelMapping(false,"errorChannel")
                })
                .get()
    }

    @Bean
    @ServiceActivator(inputChannel = "errorChannel")
    fun anotherDirectory(): MessageHandler {                            //Обработчик архива
        val handler = FileWritingMessageHandler(File(ERROR_DIR))
        handler.setDeleteSourceFiles(true)
        handler.setExpectReply(false)
        return handler
    }
}