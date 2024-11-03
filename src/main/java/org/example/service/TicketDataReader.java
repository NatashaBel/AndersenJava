package org.example.service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.ticket.BusTicket;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketDataReader {

    private final ResourceLoader resourceLoader;

    public TicketDataReader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public List<BusTicket> loadTickets() {
        List<BusTicket> busTickets = new ArrayList<>();
        Resource resource = resourceLoader.getResource("classpath:ticketData.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(resource.getFile()))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    BusTicket busTicket = new ObjectMapper().readValue(line, BusTicket.class);
                    busTickets.add(busTicket);
                } catch (JsonParseException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return busTickets;
    }
}