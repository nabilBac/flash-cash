package com.uno.flashcash.service;

import com.uno.flashcash.model.Link;
import com.uno.flashcash.model.User;
import com.uno.flashcash.repository.LinkRepository;
import com.uno.flashcash.repository.UserRepository;
import com.uno.flashcash.service.form.AddContactForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinkService {
    private final SessionService sessionService;
    private final LinkRepository linkRepository;
    private final UserRepository userRepository;

    public LinkService(SessionService sessionService, LinkRepository linkRepository, UserRepository userRepository) {
        this.sessionService = sessionService;
        this.linkRepository = linkRepository;
        this.userRepository = userRepository;
    }

   public void addLink(final AddContactForm form){
        User user = userRepository
                .findUserByMail(form.getEmail())
                .orElseThrow(()-> new RuntimeException("user with email" + form.getEmail() +"not found"));
        User connectedUser = userRepository.findUserByMail(sessionService.sessionUser().getEmail())
                .orElseThrow(()-> new RuntimeException("user with email not found"));
        Link link = new Link();
        link.setUser1(connectedUser);
        link.setUser2(user);
        linkRepository.save(link);

   }

    public List<String> findLinksEmail(){
        return linkRepository.findLinkByUser1Email(sessionService.sessionUser().getEmail()).stream().map(Link::getUser2).map(User::getEmail).collect(Collectors.toList());
    }
}
