package com.example.ragdemo.config;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Configuration
public class AppConfig {

    @Value("classpath:newproducts.txt")
    private Resource newProductsResource;

    @Bean
    public SimpleVectorStore simpleVectorStore(EmbeddingModel embeddingModel) throws IOException {
        SimpleVectorStore simpleVectorStore = new SimpleVectorStore(embeddingModel);
        File file = new File("src/main/resources/newproducts.json");
        if (file.exists()) {
            System.out.println("Loading new products from file: " + file.getAbsolutePath());
            simpleVectorStore.load(file);
            System.out.println("Loaded " + newProductsResource.getFilename());
        } else {
            TextReader textReader = new TextReader(newProductsResource);
            textReader.getCustomMetadata().put("fileName", newProductsResource.getFilename());
            List<Document> documents = textReader.get();
            TextSplitter textSplitter = new TokenTextSplitter();
            List<Document> splitDocuments = textSplitter.apply(documents);
            simpleVectorStore.add(splitDocuments);
            simpleVectorStore.save(file);
        }
        return simpleVectorStore;
    }
}
