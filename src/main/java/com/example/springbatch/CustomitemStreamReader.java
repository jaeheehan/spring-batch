package com.example.springbatch;

import org.springframework.batch.item.*;

import java.util.List;

public class CustomitemStreamReader implements ItemStreamReader<String> {

    private final List<String> items;
    private int index = -1;
    private boolean restart = false;

    public CustomitemStreamReader(List<String> items) {
        this.items = items;
        this.index = 0;
    }


    @Override
    public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        String item = null;

        if(this.index < this.items.size()){
            item = this.items.get(index);
            index++;
        }

        if(this.index == 6 && !restart){
            throw new RuntimeException("Restart is required");
        }

        return item;
    }

    @Override
    public void open(final ExecutionContext executionContext) throws ItemStreamException {
        if(executionContext.containsKey("index")){
            System.out.println("open1");
            this.index = executionContext.getInt("index");
            this.restart = true;
        }else{
            System.out.println("open2");
            index = 0;
            executionContext.put("index", this.index);
        }
    }

    @Override
    public void update(final ExecutionContext executionContext) throws ItemStreamException {
        executionContext.put("index", this.index);
    }

    @Override
    public void close() throws ItemStreamException {
        System.out.println("close");
    }
}
