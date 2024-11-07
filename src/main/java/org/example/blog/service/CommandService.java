package org.example.blog.service;

import org.example.blog.mapper.CommandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommandService {
    private final CommandMapper commandMapper;

    @Autowired
    public CommandService(CommandMapper commandMapper) {
        this.commandMapper = commandMapper;
    }


}
