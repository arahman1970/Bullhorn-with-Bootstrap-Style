package com.example.demo;

import org.springframework.data.repository.CrudRepository;

public interface MsgRepository extends CrudRepository<Message,Long>{}
