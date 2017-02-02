package spittr.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.UriComponentsBuilder;

import spittr.data.Spittle;
import spittr.data.repository.SpittleRepositoryJPA;
import spittr.exception.SpittleError;
import spittr.exception.SpittleNotFoundException;


@Controller
@RequestMapping("/spittles")
public class SpittleController {

    public static final String DEFAULT_LONG_AS_STRING = "1000";

    private SpittleRepositoryJPA spittleRepository;

    @Autowired
    public SpittleController(SpittleRepositoryJPA spittleRepository) {

        this.spittleRepository = spittleRepository;
    }

    //    @RequestMapping(method = RequestMethod.GET)
    //    public String spittles(Model model) {
    //        //        model.addAttribute(spittleRepository.findSpittles(40, 20));
    //        model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20)); //equal to line before
    //        return "spittles";
    //    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Spittle> spittles(@RequestParam(value = "count", defaultValue = "20") int count) {
        return spittleRepository.findLatestSpittles(new PageRequest(0, count));
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Spittle> saveSpittle(@RequestBody Spittle spittleToFind, UriComponentsBuilder ucb) {
        Spittle spittle = spittleRepository.save(spittleToFind);
        HttpHeaders headers = new HttpHeaders();
        URI locationUri = ucb.path("/spittles/").path(String.valueOf(spittle.getId())).build().toUri();
        headers.setLocation(locationUri);
        ResponseEntity<Spittle> responseEntity = new ResponseEntity<Spittle>(spittle, headers, HttpStatus.CREATED);
        return responseEntity;
    }

    //    @RequestMapping(value = "/{spittleId}", method = RequestMethod.GET)
    //    public String spittle(@PathVariable("spittleId") long spittleId, Model model) {
    //        //        public String spittle(@PathVariable long spittleId, Model model) { // alternative signature
    //        model.addAttribute(spittleRepository.findOne(spittleId));
    //        return "spittle";
    //    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String spittleById(@PathVariable Long id, Model model) {
        Spittle spittle = spittleRepository.findOne(id);
        if (spittle == null) {
            throw new SpittleNotFoundException(id);
        }
        model.addAttribute(spittle);
        return "spittle";
    }

    @ExceptionHandler(SpittleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public SpittleError spittleNotFound(SpittleNotFoundException e) {
        long spittleId = e.getSpittleId();
        return new SpittleError(4, "Spittle [" + spittleId + "] not found");
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String sendSpittle() {
        return "sendSpittle";
    }
}