package org.example.blog.service;

import org.example.blog.mapper.CommandMapper;
import org.example.blog.model.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.example.blog.util.TimestampUidGenerator.generateUid;

@Service
public class CommandService {
    private final CommandMapper commandMapper;

    @Autowired
    public CommandService(CommandMapper commandMapper) {
        this.commandMapper = commandMapper;
    }


    public void submitCommand(Command command) {
        command.setC_id(generateUid());
        commandMapper.insert(command);
    }
}
