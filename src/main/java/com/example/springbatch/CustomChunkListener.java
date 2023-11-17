package com.example.springbatch;

import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.AfterChunkError;
import org.springframework.batch.core.annotation.BeforeChunk;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

@Component
public class CustomChunkListener {

    @BeforeChunk
    public void beforeChunk(ChunkContext chunkContext) {
        System.out.println("beforeChunk");
    }

    @AfterChunk
    public void after(ChunkContext chunkContext) {
        System.out.println("afterChunk");
    }

    @AfterChunkError
    public void error(ChunkContext chunkContext) {
        System.out.println("errorChunk");
    }
}
