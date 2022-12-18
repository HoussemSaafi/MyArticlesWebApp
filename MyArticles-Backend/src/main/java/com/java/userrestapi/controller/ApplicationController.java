package com.java.userrestapi.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.java.userrestapi.model.Stories;
import com.java.userrestapi.repository.StoriesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController("restapi")
@RequestMapping("restapi")
@CrossOrigin(origins = "http://localhost:3000")
public class ApplicationController {

    //attributes
    @Autowired
    StoriesRepository storiesRepository;

    //methods
    //add a new story
    @PostMapping("story")
    public ResponseEntity<Stories> create(@RequestBody Stories newStory) {

        newStory.setCreatedAt(LocalDateTime.now());
        newStory.setViewCount(1l);
        newStory.setDownloadCount(0l);
        newStory.setLastUpdate(newStory.getCreatedAt());
        newStory.setLastUpdateDownload(newStory.getCreatedAt());
        ArrayList<Long> ar = new ArrayList<Long>();
        ar.add(1l);
        newStory.setViewCountTime(ar);
        ArrayList<Long> ar1 = new ArrayList<Long>();
        ar1.add(0l);
        newStory.setDownloadCountTime(ar1);
        Stories story = storiesRepository.save(newStory);

        if (story == null) {
            System.out.println("story is null!");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(story, HttpStatus.CREATED);
        }
    }

    //Show all stories
    @GetMapping("/stories")
    public ResponseEntity<List<Stories>> showStories() throws JsonProcessingException {
        List<Stories> stories = storiesRepository.findAll();
        return new ResponseEntity<>(stories, HttpStatus.OK);
    }

    //Delete story by id
    @DeleteMapping("/stories/{id}")
    public ResponseEntity<Optional<Stories>> deleteStory(@PathVariable(value = "id") Long id) {
        Optional<Stories> deletedStory = storiesRepository.findById(id);
        if (deletedStory.isPresent()) {
            System.out.println(deletedStory.get());
            storiesRepository.deleteById(id);
            return new ResponseEntity<>(deletedStory, HttpStatus.OK);
        } else {
            System.out.printf("No story found with id %d%n", id);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //Show story by id
    @GetMapping("/stories/{id}")
    public ResponseEntity<Stories> showStoryById(@PathVariable(value = "id") Long id) {
        Optional<Stories> story = storiesRepository.findById(id);
        if (story.isPresent()) {
            System.out.println(story.get());
            Stories storyA = story.get();
           // int diff = LocalDate.now().getDayOfYear ()-storyA.getLastUpdate().getDayOfYear();
            int diff = LocalDateTime.now().getMinute()-storyA.getLastUpdate().getMinute();
            if(diff>0){
                storyA.setLastUpdate(LocalDateTime.now());
                if(diff==1) {
                    ArrayList<Long> newArr = new ArrayList<Long>();
                    newArr = (ArrayList<Long>) storyA.getViewCountTime();
                    newArr.add(storyA.getViewCount()+1l);
                    storyA.setViewCountTime(newArr);
                }
                else{
                    for(int i=0;i<diff-1;i++){
                        ArrayList<Long> newArr = new ArrayList<Long>();
                        newArr = (ArrayList<Long>) storyA.getViewCountTime();
                        newArr.add(storyA.getViewCount());
                        storyA.setViewCountTime(newArr);
                    }
                    ArrayList<Long> newArr = new ArrayList<Long>();
                    newArr = (ArrayList<Long>) storyA.getViewCountTime();
                    newArr.add(storyA.getViewCount() + 1l);
                    storyA.setViewCountTime(newArr);
                }
            }
            else{
                ArrayList<Long> newArr = new ArrayList<Long>();
                newArr = (ArrayList<Long>) storyA.getViewCountTime();
                newArr.set(newArr.size()-1, newArr.get(newArr.size()-1)+1l);
                storyA.setViewCountTime(newArr);
            }
            storyA.setViewCount(storyA.getViewCount()+1l);
            storiesRepository.save(storyA);
            return new ResponseEntity<>(storyA, HttpStatus.OK);
        } else {
            System.out.printf("No story found with id %d%n", id);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/stories/download/{id}")
    public ResponseEntity<Stories> incrementDownload(@PathVariable(value = "id") Long id) {
        Optional<Stories> story = storiesRepository.findById(id);
        if (story.isPresent()) {
            System.out.println(story.get());
            Stories storyA = story.get();
            // int diff = LocalDate.now().getDayOfYear ()-storyA.getLastUpdate().getDayOfYear();
            int diff = LocalDateTime.now().getMinute()-storyA.getLastUpdateDownload().getMinute();
            if(diff>0){
                storyA.setLastUpdateDownload(LocalDateTime.now());
                if(diff==1) {
                    ArrayList<Long> newArr = new ArrayList<Long>();
                    newArr = (ArrayList<Long>) storyA.getDownloadCountTime();
                    newArr.add(storyA.getDownloadCount()+1l);
                    storyA.setDownloadCountTime(newArr);
                }
                else{
                    for(int i=0;i<diff-1;i++){
                        ArrayList<Long> newArr = new ArrayList<Long>();
                        newArr = (ArrayList<Long>) storyA.getDownloadCountTime();
                        newArr.add(storyA.getDownloadCount());
                        storyA.setDownloadCountTime(newArr);
                    }
                    ArrayList<Long> newArr = new ArrayList<Long>();
                    newArr = (ArrayList<Long>) storyA.getDownloadCountTime();
                    newArr.add(storyA.getDownloadCount() + 1l);
                    storyA.setDownloadCountTime(newArr);
                }
            }
            else{
                ArrayList<Long> newArr = new ArrayList<Long>();
                newArr = (ArrayList<Long>) storyA.getDownloadCountTime();
                newArr.set(newArr.size()-1, newArr.get(newArr.size()-1)+1l);
                storyA.setDownloadCountTime(newArr);
            }
            storyA.setDownloadCount(storyA.getDownloadCount()+1l);
            storiesRepository.save(storyA);
            return new ResponseEntity<>(storyA, HttpStatus.OK);
        } else {
            System.out.printf("No story found with id %d%n", id);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

        //All other routes:
    @RequestMapping(method = RequestMethod.GET, value = {"/**"})
    public String notFoundError(){
        return "404 NOT FOUND, PLEASE MAKE SURE TO USE THE RIGHT ROUTE";
        }
    }